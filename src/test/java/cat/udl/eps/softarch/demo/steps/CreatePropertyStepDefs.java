package cat.udl.eps.softarch.demo.steps;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.softarch.demo.domain.Property;
import cat.udl.eps.softarch.demo.repository.OwnerRepository;
import cat.udl.eps.softarch.demo.repository.PropertyRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.nio.charset.StandardCharsets;


public class CreatePropertyStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;



    @When("^I create a property with a description \"([^\"]*)\"$")
    public void createPropertyNullDescription(String desciption) throws Throwable  {
        Property propTest = new Property();
        if(desciption.equals("null")){
            propTest.setDescription(null);
        }
        else {
            propTest.setDescription(desciption);
        }

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/properties")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(propTest))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());


    }


    @Then("There is {int} Property created")
    public void thereIsPropertyCreated(int num){
        long propertyCount = propertyRepository.count();
        assertEquals("Expected " + num + " properties, but found " + propertyCount, propertyCount, num);


    }



}

