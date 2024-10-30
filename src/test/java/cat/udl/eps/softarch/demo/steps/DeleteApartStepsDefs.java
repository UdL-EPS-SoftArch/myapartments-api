package cat.udl.eps.softarch.demo.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import io.cucumber.java.en.And;
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

    @When("^I delete the advertisement with id {long}")
    public void iDeleteTheApartmentAdvertisement(String advertisementId) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/advertisements/{id}", advertisementId).accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
    }

    @And("^The adveritsment  with name {string}")
    public void theApartmentWithNameNoLongerExists(String name) {
        List<Advertisement> advertisements = advertisementRepository.findAdvertisementBy("name");
        assertTrue("Advertisment with name associated \"" + name + "\" should no longer exist", advertisements.isEmpty());
    }



}
