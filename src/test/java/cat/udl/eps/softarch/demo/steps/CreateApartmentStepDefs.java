package cat.udl.eps.softarch.demo.steps;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
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
import java.util.Optional;

public class CreateApartmentStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Given("^There is no apartment registered with the name \"([^\"]*)\"$")
    public void thereIsNoApartmentRegisteredWithTheName(String name) {
        assertTrue("Apartment with name \"" + name + "\" shouldn't exist", apartmentRepository.findByName(name).isEmpty());
    }

    @When("^I create an apartment with name \"([^\"]*)\", floor \"([^\"]*)\", address \"([^\"]*)\", postal code \"([^\"]*)\", city \"([^\"]*)\", country \"([^\"]*)\", description \"([^\"]*)\"$")
    public void iCreateAnApartmentWithDetails(String name, String floor, String address, String postalCode, String city, String country, String description) throws Exception {
        Apartment apartment = new Apartment();
        apartment.setName(name);
        apartment.setFloor(Integer.parseInt(floor));
        apartment.setAddress(address);
        apartment.setPostalCode(postalCode);
        apartment.setCity(city);
        apartment.setCountry(country);
        apartment.setDescription(description);
        apartment.setRegistrationDate(ZonedDateTime.now());

        Optional<Owner> ownerOptional = ownerRepository.findById(AuthenticationStepDefs.currentUsername);

        if (ownerOptional.isPresent()) {
            apartment.setOwner(ownerOptional.get());
        }

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/apartments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(apartment))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @And("^The apartment has been created with name \"([^\"]*)\", floor \"([^\"]*)\", address \"([^\"]*)\", postal code \"([^\"]*)\", city \"([^\"]*)\", country \"([^\"]*)\", description \"([^\"]*)\"$")
    public void theApartmentHasBeenCreated(String name, String floor, String address, String postalCode, String city, String country, String description) throws Exception {
        String responseContent = stepDefs.result.andReturn().getResponse().getContentAsString();

        assertNotNull("Response content should not be null", responseContent);

        Apartment createdApartment = objectMapper.readValue(responseContent, Apartment.class);

        assertNotNull("The apartment should not be null", createdApartment);
        assertEquals(name, createdApartment.getName());
        assertEquals(Integer.parseInt(floor), createdApartment.getFloor());
        assertEquals(address, createdApartment.getAddress());
        assertEquals(postalCode, createdApartment.getPostalCode());
        assertEquals(city, createdApartment.getCity());
        assertEquals(country, createdApartment.getCountry());
        assertEquals(description, createdApartment.getDescription());
    }

    @Given("^There is a registered owner with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredOwnerWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        if (!userRepository.existsById(username)) {
            Owner owner = new Owner();
            owner.setId(username);
            owner.setPassword(password);
            owner.setEmail(email);
            owner.setName("Owner Name");
            owner.setPhoneNumber("123456789");
            owner.encodePassword();
            ownerRepository.save(owner);
        }
    }
}
