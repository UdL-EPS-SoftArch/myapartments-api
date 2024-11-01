package cat.udl.eps.softarch.demo.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import io.cucumber.java.en.And;
import org.springframework.http.MediaType;
import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.AdvertisementStatusRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAdvertismentStepDefs {

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

    @When("I get the apartment advertisement with title {string}")
    public void iGetTheApartmentAdvertisement(String title) throws Exception {
        Advertisement ad = advertisementRepository.findByTitle(title).stream().findFirst().orElse(null);

        if (ad == null) {
            stepDefs.result = stepDefs.mockMvc.perform(
                    get("/advertisements/{id}", 9999)
                            .accept(MediaType.APPLICATION_JSON)
            ).andDo(print());
        } else {
            stepDefs.result = stepDefs.mockMvc.perform(
                    get("/advertisements/{id}", ad.getId())
                            .accept(MediaType.APPLICATION_JSON)
            ).andDo(print());
        }
    }

}
