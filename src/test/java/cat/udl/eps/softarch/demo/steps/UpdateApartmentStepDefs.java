package cat.udl.eps.softarch.demo.steps;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UpdateApartmentStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Given("Exists an apartment registered as {string}")
    public void existsAnApartmentRegisteredWithTheName(String name) throws Exception {
        List<Apartment> apartments = apartmentRepository.findByName(name);
        if (apartments.isEmpty()) {
            Apartment apartment = new Apartment();
            apartment.setName(name);
            apartment.setRegistrationDate(ZonedDateTime.now());

            Optional<Owner> ownerOptional = ownerRepository.findById(AuthenticationStepDefs.currentUsername);

            ownerOptional.ifPresent(apartment::setOwner);

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

    @Transactional
    @When("I update it from name {string} to {string}")
    public void iUpdateTheApartmentsName(String name, String new_name) throws Exception {
        List<Apartment> apartments = apartmentRepository.findByName(name);
        assertFalse("Apartment with name \"" + name + "\" should exist", apartments.isEmpty());

        Apartment apartment = apartments.get(0);
        apartment.setName(new_name);

        stepDefs.result = stepDefs.mockMvc.perform(
                        put("/apartments/" + apartment.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(apartment))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Then("The apartment {string} name is {string}")
    public void theApartmentHasUpdatedNameTo(String name, String new_name) {
        List<Apartment> oldApartments = apartmentRepository.findByName(name);
        assertTrue("Apartment with name \"" + name + "\" should not exist", oldApartments.isEmpty());

        List<Apartment> newApartments = apartmentRepository.findByName(new_name);
        assertFalse("Apartment with name \"" + new_name + "\" should exist", newApartments.isEmpty());
    }
}