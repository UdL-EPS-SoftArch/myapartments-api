package cat.udl.eps.softarch.demo.steps;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import java.util.List;
import java.util.Optional;

public class DeleteApartmentStepDefs {

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

    @Given("^There is an apartment registered with the name \"([^\"]*)\"$")
    public void thereIsAnApartmentRegisteredWithTheName(String name) throws Exception {
        List<Apartment> apartments = apartmentRepository.findByName(name);
        if(apartments.isEmpty()) {
            Apartment apartment = new Apartment();
            apartment.setName(name);
            apartment.setRegistrationDate(ZonedDateTime.now());

            Optional<Owner> ownerOptional = ownerRepository.findById(AuthenticationStepDefs.currentUsername);

            if (ownerOptional.isPresent()) {
                apartment.setOwner(ownerOptional.get());
            }

            stepDefs.result = stepDefs.mockMvc.perform(
                            post("/apartments")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(apartment))
                                    .characterEncoding(StandardCharsets.UTF_8)
                                    .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print())
                    .andExpect(status().isCreated());
        }
        apartments = apartmentRepository.findByName(name);
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
/*
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
    }*/
}