package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Property;
import cat.udl.eps.softarch.demo.repository.PropertyRepository;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ModifyPropertyStepDefs {
    @Autowired
    StepDefs stepDefs;

    @Autowired
    PropertyRepository propertyRepository;

    private Property property;

    private String previousDesc;

    private long propertyId;

    @Given("^There is a property already created with description \"([^\"]*)\"$")
    public void there_is_a_property_already_created_with_description(String description) {
        this.property = new Property();
        this.property.setDescription(description);
        this.propertyRepository.save(this.property);
    }

    @When("^I modify a property with a description \"([^\"]*)\" and change it to \"([^\"]*)\"$")
    public void modifyProperty(String previousDescription, String newDescription)throws Throwable {
        this.previousDesc = previousDescription;
        this.propertyId = property.getId();

        assertEquals(property.getDescription(), previousDescription);

        if(newDescription.equals("null")){
            property.setDescription(null);
        }
        else{
            property.setDescription(newDescription);
        }

        this.stepDefs.result = this.stepDefs.mockMvc.perform(put("/properties/"+this.propertyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(this.property))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^The property has not been modified to \"([^\"]*)\"$")
    public void the_property_has_not_been_modified(String descToCheck) throws Throwable {
        /*
        long propertyPrevDescCount = propertyRepository.findByDescription(this.previousDesc).size();
        long propertyNewDescCount = propertyRepository.findByDescription(descToCheck).size();

        assertEquals(propertyPrevDescCount, 1);
        assertEquals(propertyNewDescCount, 0);
        */

        Property propertyToCheck = this.propertyRepository.findById(this.propertyId).get();

        assertNotEquals(propertyToCheck.getDescription(), descToCheck);
        assertEquals(propertyToCheck.getDescription(), previousDesc);
    }

    @And("^The property has been modified with the description \"([^\"]*)\"$")
    public void the_property_has_been_modified_with_description(String newDescription) throws Throwable {
        /*
        long propertyPrevDescCount = propertyRepository.findByDescription(this.previousDesc).size();
        long propertyNewDescCount = propertyRepository.findByDescription(newDescription).size();

        assertEquals(propertyPrevDescCount, 0);
        assertEquals(propertyNewDescCount, 1);
        */

        Property propertyToCheck = this.propertyRepository.findById(this.propertyId).get();

        assertEquals(propertyToCheck.getDescription(), newDescription);
        assertNotEquals(propertyToCheck.getDescription(), previousDesc);
    }
}
