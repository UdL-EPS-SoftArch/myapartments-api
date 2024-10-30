package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.domain.AdvertisementStatus;
import cat.udl.eps.softarch.demo.domain.Visit;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.AdvertisementStatusRepository;
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
        createAdvertisementStatus("Available");
        AdvertisementStatus cur_status = advertisementStatusRepository.findByStatus("Available").stream().findFirst().orElse(null);
        advertisement.setAdStatus(cur_status);

        advertisement = advertisementRepository.save(advertisement);
    }

    @When("I request a visit to the advertisement with title {string}")
    public void iRequestAVisitToTheAdvertisementWithTitle(String title) throws Exception {
        // Ensure that the advertisement exists
        Advertisement advertisement = advertisementRepository.findByTitle(title).get(0);
        assertNotNull(advertisement, "Advertisement should exist");

        // Set the visit date and time
        String visitDateTime = "2024-10-30T10:00:00+01:00[Europe/Madrid]";
        Visit visit = new Visit();
        visit.setVisitDateTime(ZonedDateTime.parse(visitDateTime));
        visit.setAdvertisement(advertisement);

        visit = visitRepository.save(visit);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());


        String jsonContent = objectMapper.writeValueAsString(visit);


        result = stepDefs.mockMvc.perform(post("/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();



    }

    @Then("The visit is successfully requested")
    public void theVisitIsSuccessfullyRequested() {
        assertNotNull(result, "Result should not be null after visit request");
        Assertions.assertEquals(201, result.getResponse().getStatus(), "Expected HTTP status 201 Created");
    }
}
