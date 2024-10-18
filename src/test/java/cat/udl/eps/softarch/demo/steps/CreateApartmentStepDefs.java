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
        stepDefs.result.andExpect(status().isCreated());

        String location = stepDefs.result.andReturn().getResponse().getHeader("Location");
        assertNotNull("Location header should not be null", location);
    }


    @Given("There is a apartment with the name {string}, floor {string}, address {string}, postal code {string}, city {string}, country {string}, description {string} and a creation date {string} by owner username {string}")
    public void thereIsAApartmentWithTheNameFloorAddressPostalCodeCityCountryDescriptionAndACreationDateByOwnerUsername(String name, String floor, String address, String postal_code, String city, String country, String description, String creation_date, String owner_user) {

        Optional<Owner> owners_list = ownerRepository.findById(owner_user);
        if(owners_list.isPresent()) {
            Owner apart_owner = owners_list.get();
            Apartment apartment = ApartmentUtils.buildApartment(name,floor,address,postal_code,city,country,description,creation_date,apart_owner);
            apartmentRepository.save(apartment);
        }

    }
}



