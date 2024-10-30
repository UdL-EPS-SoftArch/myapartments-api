package cat.udl.eps.softarch.demo.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.http.MediaType;
import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.AdvertisementStatusRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvertisementStepDefs {

    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private AdvertisementStatusRepository advertisementStatusRepository;
    private AdvertisementStatus status;
    private ResponseEntity<String> response;
    @Autowired
    private StepDefs stepDefs;

    @Given("There is an existing apartment with id {string} named {string}")
    public void thereIsAnExistingApartmentWithIdNamed(String id, String name) {
        // Crea un objeto Owner (propietario)
        Owner owner = new Owner();
        owner.setName("John Doe");
        owner.setPhoneNumber("123456789");
        owner.setAddress("456 Another St");

        // Crea una lista de habitaciones (rooms)
        Room room1 = new Room();
        room1.setId(1L);
        room1.setSurface(20);
        room1.setOccupied(false);
        room1.setHasWindow(true);
        room1.setHasDesk(true);
        room1.setHasBed(true);

        Room room2 = new Room();
        room2.setId(2L);
        room2.setSurface(15);
        room2.setOccupied(true);
        room2.setHasWindow(false);
        room2.setHasDesk(false);
        room2.setHasBed(true);

        List<Room> rooms = List.of(room1, room2);

        Apartment apartment = new Apartment();
        apartment.setId(Long.parseLong(id));
        apartment.setName(name);
        apartment.setFloor(5);
        apartment.setAddress("123 Example St");
        apartment.setPostalCode("08001");
        apartment.setCity("Barcelona");
        apartment.setCountry("Spain");
        apartment.setDescription("A beautiful luxury apartment in the city center.");
        apartment.setRegistrationDate(ZonedDateTime.now());
        apartment.setOwner(owner);
        apartment.setRooms(rooms);

    }

    @Given("There is an existing advertisement status {string}")
    public void thereIsAnExistingAdvertisementStatusWithIdAndStatus(String status) {
        AdvertisementStatus advertisementStatus = new AdvertisementStatus();
        advertisementStatus.setStatus(status);
        advertisementStatusRepository.save(advertisementStatus);

    }

    @When("^I delete the advertisement with id/name nosek \"([^\"]*)\"$")
    public void iDeleteTheApartmentAdvertisement(String a) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/advertisements/{id}", advertisementId)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
    }

    @And("^The apartment with name \"([^\"]*)\" no longer exists$")
    public void theApartmentWithNameNoLongerExists(String name) {
        List<Apartment> apartments = apartmentRepository.findByName(name);
        assertTrue("Apartment with name \"" + name + "\" should no longer exist", apartments.isEmpty());
    }



}
