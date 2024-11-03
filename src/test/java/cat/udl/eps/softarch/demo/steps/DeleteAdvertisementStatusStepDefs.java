package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.AdvertisementStatus;
import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.repository.AdvertisementStatusRepository;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import cat.udl.eps.softarch.demo.repository.OwnerRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteAdvertisementStatusStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    AdvertisementStatusRepository advertisementStatusRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @And("^The advertisement status with name \"([^\"]*)\" no longer exists$")
    public void theApartmentWithNameNoLongerExists(String name) {
        List<AdvertisementStatus> apartments = advertisementStatusRepository.findByStatus(name);
        assertTrue("Apartment with name \"" + name + "\" should no longer exist", apartments.isEmpty());
    }

    @When("I delete the advertisement status with status {string}")
    public void iDeleteTheAdvertisementStatusWithStatus(String status) throws Exception {
        AdvertisementStatus adStatus = advertisementStatusRepository.findByStatus(status).stream().findFirst().orElse(null);


        if (adStatus == null) {
            stepDefs.result = stepDefs.mockMvc.perform(
                    delete("/advertisements/{id}", 9999).accept(MediaType.APPLICATION_JSON)
                            .characterEncoding(StandardCharsets.UTF_8)
                            .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print());
        } else {
            stepDefs.result = stepDefs.mockMvc.perform(
                            delete("/advertisementStatuses/" + adStatus.getId())
                                    .characterEncoding(StandardCharsets.UTF_8)
                                    .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print());
        }

    }
}