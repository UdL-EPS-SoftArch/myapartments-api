package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.domain.Visit;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.VisitRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ModifyVisitSteps {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    private MvcResult result;

    @When("I modify the visit request to the advertisement with title {string} with new date {string}")
    public void iModifyTheVisitRequestToTheAdvertisementWithTitleWithNewDate(String title, String newDate) throws Exception {
        Advertisement advertisement = advertisementRepository.findByTitle(title).get(0);
        Visit visit = visitRepository.findByAdvertisement(advertisement).get(0);
        visit.setVisitDateTime(ZonedDateTime.parse(newDate));

        result = stepDefs.mockMvc.perform(put("/visits/" + visit.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"when\": \"" + newDate + "\"}")
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Then("The visit is successfully modified")
    public void theVisitIsSuccessfullyModified() {
        assertNotNull(result, "Result should not be null after visit modification");
        Assertions.assertEquals(204, result.getResponse().getStatus(), "Expected HTTP status 200 OK");
    }
}