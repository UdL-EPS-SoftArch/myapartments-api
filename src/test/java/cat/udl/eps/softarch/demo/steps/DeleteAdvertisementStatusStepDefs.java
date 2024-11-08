package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.AdvertisementStatus;
import cat.udl.eps.softarch.demo.repository.AdvertisementStatusRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteAdvertisementStatusStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    AdvertisementStatusRepository advertisementStatusRepository;


    @When("I delete the advertisement status with status {string}")
    public void iDeleteTheAdvertisementStatusWithStatus(String status) throws Exception {
        AdvertisementStatus adStatus = advertisementStatusRepository.findByStatus(status).stream().findFirst().orElse(null);


        if (adStatus == null) {
            stepDefs.result = stepDefs.mockMvc.perform(
                    delete("/advertisementStatuses/{id}", -1).accept(MediaType.APPLICATION_JSON)
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