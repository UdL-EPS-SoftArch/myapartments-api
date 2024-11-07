package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Property;
import cat.udl.eps.softarch.demo.repository.PropertyRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeletePropertyStepDefs {
    @Autowired
    StepDefs stepDefs;
    @Autowired
    PropertyRepository propertyRepository;

    @When("^I delete a property with a description \"([^\"]*)\"$")
    public void modifyProperty(String previousDescription)throws Throwable {
        Property property = propertyRepository.findByDescription(previousDescription).get(0);
        Long propertyId = property.getId();
        this.stepDefs.result = this.stepDefs.mockMvc.perform(delete("/properties/"+propertyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(property))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
