package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.domain.Room;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import cat.udl.eps.softarch.demo.repository.OwnerRepository;
import cat.udl.eps.softarch.demo.repository.RoomRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

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

    @When("I create a Room with name {string}, occupied {string}, window {string}, desk {string} and bed {string}")
    public void iCreateARoomWithDetails(String name, String occupied, String window, String desk, String bed) throws Exception {
        Room room = new Room();
        room.setOccupied(Boolean.parseBoolean(occupied));
        room.setHasWindow(Boolean.parseBoolean(window));
        room.setHasDesk(Boolean.parseBoolean(desk));
        room.setHasBed(Boolean.parseBoolean(bed));
    }

    @And("I don't have any Room")
    public void iDontHaveAnyRoomCreate() {
        roomRepository.deleteAll();
    }
}
