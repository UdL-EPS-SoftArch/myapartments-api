package cat.udl.eps.softarch.demo.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import cat.udl.eps.softarch.demo.domain.AdvertisementStatus;
import cat.udl.eps.softarch.demo.repository.AdvertisementStatusRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;

public class CreateAdvertisementStatusStepDefs {

    @Autowired
    private AdvertisementStatusRepository advertisementStatusRepository;

    @Autowired
    private StepDefs stepDefs;

    private ResponseEntity<String> response;

    @Given("There is an existing advertisement status with status {string}")
    public void thereIsAnExistingAdvertisementStatusWithStatus(String status) {
        AdvertisementStatus advertisementStatus = new AdvertisementStatus();
        advertisementStatus.setStatus(status);
        advertisementStatusRepository.save(advertisementStatus);
    }

    @When("I create a new advertisement status with status {string}")
    public void iCreateANewAdvertisementStatusWithStatus(String status) throws Exception {
        AdvertisementStatus adStatus = new AdvertisementStatus();
        adStatus.setStatus(status);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/advertisementStatuses")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(adStatus))
                                .with(AuthenticationStepDefs.authenticate())
                                .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());

    }

    @Then("The advertisement status has been created with status {string}")
    public void theAdvertisementStatusHasBeenCreatedWithStatus(String status) {
        AdvertisementStatus createdStatus = advertisementStatusRepository.findByStatus(status).stream().findFirst().orElse(null);
        assert createdStatus != null;
        assertEquals(status, createdStatus.getStatus());
    }

    @Then("No advertisement status has been created")
    public void noAdvertisementStatusHasBeenCreated() {
        assertEquals(0, advertisementStatusRepository.count());
    }


}
