package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.AdvertisementStatus;
import cat.udl.eps.softarch.demo.repository.AdvertisementStatusRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ModifyAdvertisementStatusStepDefs {
    @Autowired
    StepDefs stepDefs;

    @Autowired
    AdvertisementStatusRepository adStatusRepository;

    private AdvertisementStatus adStatus;

    private String previousStatus;

    private long adStatusId;

    @Given("^There is an advertisementStatus already created with status \"([^\"]*)\"$")
    public void there_is_an_advertisement_status_already_created_with_description(String status) {
        this.adStatus = new AdvertisementStatus();
        this.adStatus.setStatus(status);
        this.adStatusRepository.save(this.adStatus);
    }

    @When("^I modify an advertisementStatus with status \"([^\"]*)\" and change it to \"([^\"]*)\"$")
    public void modify_advertisement_status(String status, String newStatus) throws Throwable {
        AdvertisementStatus adStatus = adStatusRepository.findByStatus(status).stream().findFirst().orElse(null);
        assertNotNull("Apartment with name \"" + status + "\" should exist", adStatus);
        this.previousStatus = this.adStatus.getStatus();
        this.adStatusId = adStatus.getId();
        if (newStatus.equals("null")) {
            adStatus.setStatus(null);
        } else {
            adStatus.setStatus(newStatus);
        }

        this.stepDefs.result = this.stepDefs.mockMvc.perform(put("/advertisementStatuses/" + this.adStatusId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(adStatus))  // Enviar el objeto actualizado
                        .characterEncoding(StandardCharsets.UTF_8)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }

    @And("The advertisementStatus has not been modified to \"([^\"]*)\"$")
    public void the_advertisement_status_has_not_been_modified(String descToCheck) throws Throwable {

        AdvertisementStatus adStatusToCheck = this.adStatusRepository.findById(this.adStatusId).get();

        assertNotEquals(adStatusToCheck.getStatus(), descToCheck);
        assertEquals(adStatusToCheck.getStatus(), previousStatus);
    }

    @And("The advertisementStatus has been modified to \"([^\"]*)\"$")
    public void the_advertisement_status_has_been_modified(String status) {

        AdvertisementStatus adStatusToCheck = this.adStatusRepository.findById(this.adStatusId).get();

        assertEquals(status, adStatusToCheck.getStatus());
    }

    @And("^The advertisementStatus has been modified with the description \"([^\"]*)\"$")
    public void the_advertisement_status_has_been_modified_with_description(String newDescription) throws Throwable {

        AdvertisementStatus adStatusToCheck = this.adStatusRepository.findById(this.adStatusId).get();

        assertEquals(adStatusToCheck.getStatus(), newDescription);
        assertNotEquals(adStatusToCheck.getStatus(), previousStatus);
    }
}
