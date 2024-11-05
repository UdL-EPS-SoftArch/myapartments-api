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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteVisitSteps {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    private MvcResult result;



    @When("I delete the visit request to the advertisement with title {string}")
    public void iDeleteTheVisitRequestToTheAdvertisementWithTitle(String title) throws Exception {

        Advertisement advertisement = advertisementRepository.findByTitle(title).get(0);
        Visit visit = visitRepository.findByAdvertisement(advertisement).get(0);

        result = stepDefs.mockMvc.perform(delete( "/visits/" + visit.getId() )
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();



    }

    @Then("The visit is successfully deleted")
    public void theVisitIsSuccessfullyDeleted() {
        assertNotNull(result, "Result should not be null after visit deletion");
        Assertions.assertEquals(204, result.getResponse().getStatus(), "Expected HTTP status 200 OK");
    }
}
