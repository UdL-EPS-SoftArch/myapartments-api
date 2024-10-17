package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.domain.Visit;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import cat.udl.eps.softarch.demo.repository.VisitRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RequestVisitStepDefs {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    private MvcResult result;

    @Given("There is an advertisement with id {int}")
    public void thereIsAnAdvertisementWithId(long advertisementId) {
        Assert.assertFalse("Advertisement \""
                        +  advertisementId + "\"shouldn't exist",
                advertisementRepository.existsById(advertisementId));
    }

    @When("I request a visit to the advertisement with id {int}")
    public void iRequestAVisitToTheAdvertisementWithId(long advertisementId) throws Exception {
        Visit visit = new Visit();
        Advertisement advertisement = new Advertisement();
        advertisement.setId(advertisementId);
        visit.setAdvertisement(advertisement);

        result = mockMvc.perform(post("/visits")
                        .contentType("application/json")
                        .content("{\"advertisement\":{\"id\":" + advertisementId + "}}"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Then("The visit is successfully requested")
    public void theVisitIsSuccessfullyRequested() {
        assertNotNull(result);
    }
}