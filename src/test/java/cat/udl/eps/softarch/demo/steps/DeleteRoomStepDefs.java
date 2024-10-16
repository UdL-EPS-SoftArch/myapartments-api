package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.domain.Room;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import cat.udl.eps.softarch.demo.repository.OwnerRepository;
import cat.udl.eps.softarch.demo.repository.RoomRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteRoomStepDefs {
    private StepDefs stepDefs;
    private ApartmentRepository apartmentRepository;
    private OwnerRepository ownerRepository;
    private RoomRepository roomRepository;

    @When("I delete Room with a surface {int}, is occuped {boolean}, has window {boolean}, has desk {boolean} and have bed {boolean}")
    public void iDeleteRoomWithSurfaceIsOccupedHasWindowHasDeskAndHaveBed(Integer int1, Boolean bool1, Boolean bool2, Boolean bool3, Boolean bool4) {

    }

    @When("I try to delete Room with id {long}")
    public void iTryToDeleteRoomWithUserAndRoomName(Long roomId) throws Throwable {
        Room room = RoomUtils.getRoom(roomRepository, roomId);
        stepDefs.result = stepDefs.mockMvc.perform(
                delete(room.getUri())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Then("I should have {int} Room")
    public void iShouldHaveRoom(int nRooms) {

    }

    @When("I try to delete Room with user {string} and apartment {string}")
    public void iTryToDeleteRoomWithUserAndApartment(String userId, String apartmentName) throws Throwable {
        Owner owner = ownerRepository.findById(userId).get();
        Apartment apartment = ApartmentUtils.getRoomByTitle(roomRepository, apartmentName);
        Room room = RoomUtils.getRoom(roomRepository, apartment, owner);
        stepDefs.result = stepDefs.mockMvc.perform(
                delete(room.getUri())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
