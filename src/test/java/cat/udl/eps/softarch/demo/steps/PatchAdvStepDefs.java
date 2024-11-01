package cat.udl.eps.softarch.demo.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;



import java.util.HashMap;
import java.util.Map;

public class PatchAdvStepDefs {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private StepDefs stepDefs;

    @When("I patch the apartment advertisement with title {string} and new description {string}")
    public void iPatchTheApartmentAdvertisement(String title, String newDescription) throws Exception {
        Advertisement ad = advertisementRepository.findByTitle(title).stream().findFirst().orElse(null);

        Map<String, String> updatedFields = new HashMap<>();
        updatedFields.put("description", newDescription);
        String jsonContent = new ObjectMapper().writeValueAsString(updatedFields);

        if (ad == null) {
            stepDefs.result = stepDefs.mockMvc.perform(
                    patch("/advertisements/{id}", 9999)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonContent)
                            .with(AuthenticationStepDefs.authenticate())
            ).andDo(print());
        } else {
            stepDefs.result = stepDefs.mockMvc.perform(
                    patch("/advertisements/{id}", ad.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonContent)
                            .with(AuthenticationStepDefs.authenticate())
            ).andDo(print());
        }
    }
}
