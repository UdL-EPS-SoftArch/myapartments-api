package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.domain.AdvertisementStatus;
import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Visit;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.AdvertisementStatusRepository;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import cat.udl.eps.softarch.demo.repository.VisitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RequestVisitStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private AdvertisementStatusRepository advertisementStatusRepository;

    private MvcResult result;
    private Advertisement advertisement;


    public void createAdvertisementStatus(String status) {
        AdvertisementStatus advertisementStatus = new AdvertisementStatus();
        advertisementStatus.setStatus(status);
        advertisementStatusRepository.save(advertisementStatus);

    }

    @Given("There is an advertisement with title {string} and address {string}")
    public void thereIsAnAdvertisementWithTitleAndAddress(String title, String address) {
        advertisement = new Advertisement();
        advertisement.setTitle(title);
        advertisement.setAddress(address);
        advertisement.setDescription("A cozy loft in the center of Barcelona");
        advertisement.setPrice(new BigDecimal("1000.00"));
        advertisement.setZipCode("08001");
        advertisement.setCountry("Spain");
        advertisement.setCreationDate(ZonedDateTime.now());
        String visitDateTime = ZonedDateTime.now().plusDays(30).toString();
        Apartment apartment = ApartmentUtils.buildApartment("Apartment", "2", "Apartment", "25182", "Lleida", "Spain", "Apartment", visitDateTime);
        apartmentRepository.save(apartment);
        advertisement.setApartment(apartment);
        createAdvertisementStatus("Available");
        AdvertisementStatus cur_status = advertisementStatusRepository.findByStatus("Available").stream().findFirst().orElse(null);
        advertisement.setAdStatus(cur_status);

        advertisement = advertisementRepository.save(advertisement);
    }

    @When("I request a visit to the advertisement with title {string}")
    public void iRequestAVisitToTheAdvertisementWithTitle(String title) throws Exception {

        Advertisement advertisement;

        if (title.equals("Invalid Advertisement"))
            advertisement = null;
        else {
            advertisement = advertisementRepository.findByTitle(title).get(0);
        }

        String visitDateTime = ZonedDateTime.now().plusDays(30).toString();
        Visit visit = new Visit();
        visit.setVisitDateTime(ZonedDateTime.parse(visitDateTime));
        visit.setAdvertisement(advertisement);


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());


        String jsonContent = objectMapper.writeValueAsString(visit);

        if (advertisement != null) {
            result = stepDefs.mockMvc.perform(post("/visits")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonContent)
                            .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn();
        } else if (advertisement == null) {

            result = stepDefs.mockMvc.perform(post("/visits")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonContent)
                            .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andReturn();
        }


    }

    @Then("The visit is successfully requested")
    public void theVisitIsSuccessfullyRequested() {
        assertNotNull(result, "Result should not be null after visit request");
        Assertions.assertEquals(201, result.getResponse().getStatus(), "Expected HTTP status 201 Created");
    }


    @Then("The visit is not successfully requested")
    public void theVisitIsNotSuccessfullyRequested() {
        assertNotNull(result, "Result should not be null after visit request");
        Assertions.assertEquals(400, result.getResponse().getStatus(), "Expected HTTP status 400 Bad Request");
    }


}
