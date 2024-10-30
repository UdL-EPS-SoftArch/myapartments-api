package cat.udl.eps.softarch.demo.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

public class DeleteAdvStepsDefs {

    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private AdvertisementStatusRepository advertisementStatusRepository;
    private AdvertisementStatus status;
    private ResponseEntity<String> response;
    @Autowired
    private StepDefs stepDefs;





    @When("I delete the apartment advertisement with title {string}")
    public void iDeleteTheApartmentAdvertisement(String advertisementId) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/advertisements/{id}", advertisementId).accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
    }

    @And("There is an advertisement with title {string}, description {string}, price {string}, zipCode {string}, address {string}, country {string}, status {string}")
    public void iCreateANewAdvertisement(String title, String description, String price, String zipCode, String adress, String country, String status)  {
        Advertisement ad = new Advertisement();
        ad.setTitle(title);
        ad.setDescription(description);
        ad.setPrice(new BigDecimal(price));
        ad.setZipCode(zipCode);
        ad.setAddress(adress);
        ad.setCountry(country);
        AdvertisementStatus cur_status = advertisementStatusRepository.findByStatus(status).stream().findFirst().orElse(null);
        ad.setAdStatus(cur_status);
        advertisementRepository.save(ad);


    }



}
