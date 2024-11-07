package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.AdvertisementStatusRepository;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UpdateAdvertisementStepDefs {

    @Autowired
    private AdvertisementStatusRepository advertisementStatusRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private StepDefs stepDefs;


    @When("I update an advertisement with title {string} to have name {string} and address {string}")
    public void iUpdateAdvertisement(String title, String newTitle, String newAddress) throws Exception {
        List<Advertisement> ads = advertisementRepository.findByTitle(title);

        if (ads.isEmpty()) {
            throw new IllegalArgumentException("Advertisement with title " + title + " not found");
        }

        Advertisement advertisement = ads.get(0);
        advertisement.setTitle(newTitle);
        advertisement.setAddress(newAddress);
        advertisementRepository.save(advertisement);

        stepDefs.result = stepDefs.mockMvc.perform(put("/advertisements/" + advertisement.getId())
                        .with(AuthenticationStepDefs.authenticate())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(advertisement))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());
    }



    @Then("The advertisement with title {string} should have name {string} and address {string}")
    public void theAdvertisementWithTitleShouldHaveNameAndAddress(String title, String expectedName, String expectedAddress) throws Exception {
        Advertisement updatedAdvertisement = advertisementRepository.findByTitle(title).stream().findFirst().orElse(null);

        if (updatedAdvertisement == null) {
            throw new IllegalArgumentException("Advertisement with title " + title + " not found");
        }

        assertEquals(expectedName, updatedAdvertisement.getTitle());
        assertEquals(expectedAddress, updatedAdvertisement.getAddress());
    }

    @When("I update the advertisement with title {string} to have price {string}")
    public void iUpdateAdvertisementPrice(String title, String newPrice) throws Exception {
        List<Advertisement> ads = advertisementRepository.findByTitle(title);

        if (ads.isEmpty()) {
            throw new IllegalArgumentException("Advertisement with title " + title + " not found");
        }

        Advertisement advertisement = ads.get(0);
        advertisement.setPrice(new BigDecimal(newPrice));

        stepDefs.result = stepDefs.mockMvc.perform(put("/advertisements/" + advertisement.getId())
                        .with(AuthenticationStepDefs.authenticate())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(advertisement))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());
    }

    @Then("The advertisement with title {string} should have price {string}")
    public void theAdvertisementWithTitleShouldHavePrice(String title, String expectedPrice) throws Exception {
        Advertisement updatedAdvertisement = advertisementRepository.findByTitle(title).stream().findFirst().orElse(null);

        if (updatedAdvertisement == null) {
            throw new IllegalArgumentException("Advertisement with title " + title + " not found");
        }

        assertEquals(new BigDecimal(expectedPrice), updatedAdvertisement.getPrice());
    }

    @When("I update the advertisement with title {string} to have zipCode {string}")
    public void iUpdateAdvertisementZipCode(String title, String newZipCode) throws Exception {
        List<Advertisement> ads = advertisementRepository.findByTitle(title);

        if (ads.isEmpty()) {
            throw new IllegalArgumentException("Advertisement with title " + title + " not found");
        }

        Advertisement advertisement = ads.get(0);
        advertisement.setZipCode(newZipCode);
        advertisementRepository.save(advertisement);

        stepDefs.result = stepDefs.mockMvc.perform(put("/advertisements/" + advertisement.getId())
                        .with(AuthenticationStepDefs.authenticate())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(advertisement))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());
    }

    @Then("The advertisement with title {string} should have zipCode {string}")
    public void theAdvertisementWithTitleShouldHaveZipCode(String title, String expectedZipCode) throws Exception {
        Advertisement updatedAdvertisement = advertisementRepository.findByTitle(title).stream().findFirst().orElse(null);

        if (updatedAdvertisement == null) {
            throw new IllegalArgumentException("Advertisement with title " + title + " not found");
        }

        assertEquals(expectedZipCode, updatedAdvertisement.getZipCode());
    }

    @When("I update the advertisement with title {string} to have country {string}")
    public void iUpdateAdvertisementCountry(String title, String newCountry) throws Exception {
        List<Advertisement> ads = advertisementRepository.findByTitle(title);

        if (ads.isEmpty()) {
            throw new IllegalArgumentException("Advertisement with title " + title + " not found");
        }

        Advertisement advertisement = ads.get(0);
        advertisement.setCountry(newCountry);
        advertisementRepository.save(advertisement);

        stepDefs.result = stepDefs.mockMvc.perform(put("/advertisements/" + advertisement.getId())
                        .with(AuthenticationStepDefs.authenticate())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(advertisement))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());
    }

    @Then("The advertisement with title {string} should have country {string}")
    public void theAdvertisementWithTitleShouldHaveCountry(String title, String expectedCountry) throws Exception {
        Advertisement updatedAdvertisement = advertisementRepository.findByTitle(title).stream().findFirst().orElse(null);

        if (updatedAdvertisement == null) {
            throw new IllegalArgumentException("Advertisement with title " + title + " not found");
        }

        assertEquals(expectedCountry, updatedAdvertisement.getCountry());
    }

    @When("I update the advertisement with title {string} to have description {string}")
    public void iUpdateAdvertisementDescription(String title, String newDescription) throws Exception {
        List<Advertisement> ads = advertisementRepository.findByTitle(title);

        if (ads.isEmpty()) {
            throw new IllegalArgumentException("Advertisement with title " + title + " not found");
        }

        Advertisement advertisement = ads.get(0);
        advertisement.setDescription(newDescription);
        advertisementRepository.save(advertisement);

        stepDefs.result = stepDefs.mockMvc.perform(put("/advertisements/" + advertisement.getId())
                        .with(AuthenticationStepDefs.authenticate())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(advertisement))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());
    }

    @Then("The advertisement with title {string} should have description {string}")
    public void theAdvertisementWithTitleShouldHaveDescription(String title, String expectedDescription) throws Exception {
        Advertisement updatedAdvertisement = advertisementRepository.findByTitle(title).stream().findFirst().orElse(null);

        if (updatedAdvertisement == null) {
            throw new IllegalArgumentException("Advertisement with title " + title + " not found");
        }

        assertEquals(expectedDescription, updatedAdvertisement.getDescription());
    }


}
