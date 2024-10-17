package cat.udl.eps.softarch.demo.steps;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import cat.udl.eps.softarch.demo.repository.OwnerRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class DeleteApartmentStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    @Given("^There is an apartment registered with the name \"([^\"]*)\"$")
    public void thereIsAnApartmentRegisteredWithTheName(String name) {
        List<Apartment> apartments = apartmentRepository.findByName(name);
        assertFalse("Apartment with name \"" + name + "\" should exist", apartments.isEmpty());
    }

    @When("^I delete the apartment with name \"([^\"]*)\"$")
    public void iDeleteTheApartmentWithName(String name) throws Exception {
        List<Apartment> apartments = apartmentRepository.findByName(name);
        assertFalse("Apartment with name \"" + name + "\" should exist", apartments.isEmpty());
        Apartment apartment = apartments.get(0);

        stepDefs.result = stepDefs.mockMvc.perform(
                        delete("/apartments/" + apartment.getId())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @And("^The apartment with name \"([^\"]*)\" no longer exists$")
    public void theApartmentWithNameNoLongerExists(String name) {
        List<Apartment> apartments = apartmentRepository.findByName(name);
        assertTrue("Apartment with name \"" + name + "\" should no longer exist", apartments.isEmpty());
    }

    @Given("^There is an apartment registered with the address \"([^\"]*)\"$")
    public void thereIsAnApartmentRegisteredWithTheAddress(String address) {
        List<Apartment> apartments = apartmentRepository.findByAddress(address);
        assertFalse("Apartment with address \"" + address + "\" should exist", apartments.isEmpty());
    }

    @When("^I delete the apartment with address \"([^\"]*)\"$")
    public void iDeleteTheApartmentWithAddress(String address) throws Exception {
        List<Apartment> apartments = apartmentRepository.findByAddress(address);
        assertFalse("Apartment with address \"" + address + "\" should exist", apartments.isEmpty());
        Apartment apartment = apartments.get(0);

        stepDefs.result = stepDefs.mockMvc.perform(
                        delete("/apartments/" + apartment.getId())
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @And("^The apartment with address \"([^\"]*)\" no longer exists$")
    public void theApartmentWithAddressNoLongerExists(String address) {
        List<Apartment> apartments = apartmentRepository.findByAddress(address);
        assertTrue("Apartment with address \"" + address + "\" should no longer exist", apartments.isEmpty());
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