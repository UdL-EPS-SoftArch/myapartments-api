package cat.udl.eps.softarch.demo.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import cat.udl.eps.softarch.demo.repository.*;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.http.MediaType;
import cat.udl.eps.softarch.demo.domain.*;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvertisementStepDefs {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private AdvertisementStatusRepository advertisementStatusRepository;
    private AdvertisementStatus status;
    private ResponseEntity<String> response;
    @Autowired
    private StepDefs stepDefs;

    @Given("There is an existing apartment with id {string} named {string}")
    public void thereIsAnExistingApartmentWithIdNamed(String id, String name) {
        // Crea un objeto Owner (propietario)
        Optional<Owner> ownerOptional = ownerRepository.findById("owner");
        Owner owner;
        if (ownerOptional.isPresent()) {
            owner = ownerOptional.get();
        } else {
            owner = new Owner();
            owner.setEmail("owner@sample.app");
            owner.setId("owner");
            owner.setName("Ramon");
            owner.setPhoneNumber("639826878");
            owner.setPassword("password");
            owner.encodePassword();
            ownerRepository.save(owner);
        }

        Room room1 = new Room();
        room1.setSurface(20);
        room1.setOccupied(false);
        room1.setHasWindow(true);
        room1.setHasDesk(true);
        room1.setHasBed(true);

        Room room2 = new Room();
        room2.setSurface(15);
        room2.setOccupied(true);
        room2.setHasWindow(false);
        room2.setHasDesk(false);
        room2.setHasBed(true);

// Asignar el apartamento a cada habitación
        Apartment apartment = new Apartment();
        apartment.setName(name);
        apartment.setFloor(5);
        apartment.setAddress("123 Example St");
        apartment.setPostalCode("08001");
        apartment.setCity("Barcelona");
        apartment.setCountry("Spain");
        apartment.setDescription("A beautiful luxury apartment in the city center.");
        apartment.setRegistrationDate(ZonedDateTime.now());
        apartment.setOwner(owner);

        List<Room> rooms = List.of(room1, room2);
        for (Room room : rooms) {
            room.setApartment(apartment); // Establecer la relación bidireccional
        }

        apartment.setRooms(rooms);
        apartmentRepository.save(apartment); // Guardar el apartamento con las habitaciones


    }

    @Given("There is an existing advertisement status {string}")
    public void thereIsAnExistingAdvertisementStatusWithIdAndStatus(String status) {
        AdvertisementStatus advertisementStatus = new AdvertisementStatus();
        advertisementStatus.setStatus(status);
        advertisementStatusRepository.save(advertisementStatus);

    }

    @When("I create a new advertisement with title {string}, description {string}, price {string}, zipCode {string}, address {string}, country {string}, status {string}, apartment title {string}")
    public void iCreateANewAdvertisement(String title, String description, String price, String zipCode, String adress, String country, String status_state, String apartment) throws Exception {
        Advertisement ad = new Advertisement();
        ad.setTitle(title);
        ad.setDescription(description);
        ad.setPrice(new BigDecimal(price));
        ad.setZipCode(zipCode);
        ad.setAddress(adress);
        ad.setCountry(country);
        AdvertisementStatus cur_status = advertisementStatusRepository.findByStatus(status_state).stream().findFirst().orElse(null);
        Apartment cur_apartment = apartmentRepository.findByName(apartment).stream().findFirst().orElse(null);
        ad.setAdStatus(cur_status);
        ad.setApartment(cur_apartment);


        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/advertisements")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(ad))
                                .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());

    }


    @Then("The advertisement has been created with title {string}")
    public void theAdvertisementHasBeenCreatedWithTitle(String title) {
        Advertisement createdAd = advertisementRepository.findByTitle(title).get(0);
        assertEquals(title, createdAd.getTitle());
    }

    @Then("It has not been created an advertisement")
    public void it_has_not_been_created_an_advertisement() {
        assertEquals(0, advertisementRepository.count());
    }
}
