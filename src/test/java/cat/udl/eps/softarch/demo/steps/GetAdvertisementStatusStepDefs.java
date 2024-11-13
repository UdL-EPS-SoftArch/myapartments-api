package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.domain.AdvertisementStatus;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.AdvertisementStatusRepository;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class GetAdvertisementStatusStepDefs {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private AdvertisementStatusRepository advertisementStatusRepository;
    private AdvertisementStatus status;
    private ResponseEntity<String> response;
    @Autowired
    private StepDefs stepDefs;

    @When("I get the advertisementStatus with status {string}")
    public void iGetTheApartmentAdvertisement(String status) throws Exception {
        AdvertisementStatus adStatus = advertisementStatusRepository.findByStatus(status).stream().findFirst().orElse(null);

        if (adStatus != null) {
        stepDefs.result = stepDefs.mockMvc.perform(get("/advertisementStatuses/{id}", adStatus.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
        }else {
            stepDefs.result = stepDefs.mockMvc.perform(get("/advertisementStatuses/{id}", -1)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print());
        }

    }

}
