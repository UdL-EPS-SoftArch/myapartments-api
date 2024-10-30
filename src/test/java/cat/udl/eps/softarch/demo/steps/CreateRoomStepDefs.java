package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.domain.Room;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import cat.udl.eps.softarch.demo.repository.OwnerRepository;
import cat.udl.eps.softarch.demo.repository.RoomRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.RouteMatcher;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateRoomStepDefs {


    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private RoomRepository roomRepository;


    @Given("There is a room with surface {string}, is occupied {string}, has window {string}, has desk {string} and have bed {string}, by owner username {string} and the apartment_name {string}")
    public void thereIsARoomWithSurfaceIsOccupiedHasWindowHasDeskAndHaveBedByOwnerUsername(String surface, String IsOccupied, String HasWindow, String HasDesk, String HasBed, String UserName, String ApartmentName) {
        Optional<Owner> owner_list_by_userid = ownerRepository.findById(UserName);
        Apartment apart = apartmentRepository.findByName(ApartmentName).get(0);
        if(owner_list_by_userid.isPresent()) {
            Owner owner = owner_list_by_userid.get();
            Room room = RoomUtils.buildRoom(surface,IsOccupied,HasWindow,HasDesk,HasBed);
            room.setApart(apart);
            room.setOwner(owner);
            roomRepository.save(room);
        }
    }

    @When("I create a Room with the surface {string}, occupied {string}, window {string}, desk {string} and bed {string}, by owner username {string} and the apartment_name {string}")
    public void iCreateARoomWithDetails(String surface, String occupied, String window, String desk, String bed, String UserName, String ApartmentName) throws Exception {
        Optional<Owner> owner_list_by_userid = ownerRepository.findById(UserName);
        Apartment apart = apartmentRepository.findByName(ApartmentName).get(0);

        Owner owner = owner_list_by_userid.get();
        Room room = RoomUtils.buildRoom(surface,occupied,window,desk,bed);
        room.setApart(apart);
        room.setOwner(owner);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/rooms")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(room))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }

    @And("I don't have any Room")
    public void iDontHaveAnyRoomCreate() {
        roomRepository.deleteAll();
    }

    @When("I create a Room with the surface {string}, occupied {string}, window {string}, desk {string} and bed {string}")
    public void iCreateARoomWithTheSurfaceOccupiedWindowDeskAndBed(String surface, String occupied, String window, String desk, String bed) throws Exception {
        Room room = RoomUtils.buildRoom(surface,occupied,window,desk,bed);
        Iterable<Apartment> apartmentList = apartmentRepository.findAll();
        Iterable<Owner> usersList = ownerRepository.findAll();
        Apartment apartment = apartmentList.iterator().next();
        Owner owner = usersList.iterator().next();
        room.setApart(apartment);
        room.setOwner(owner);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/rooms")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(room))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }
}
