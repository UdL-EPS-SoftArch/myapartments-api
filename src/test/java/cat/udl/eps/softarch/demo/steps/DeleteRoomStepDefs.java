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

    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private RoomRepository roomRepository;

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

    @When("I try to delete Room with of the apartment {string}")
    public void iTryToDeleteRoomWithOwnerAndApartment(String apartmentName) throws Throwable {
        Apartment apartment = apartmentRepository.findByName(apartmentName).get(0);
        Room room = RoomUtils.getRoom(roomRepository, apartment);
        stepDefs.result = stepDefs.mockMvc.perform(
                        delete((room == null?"":room.getUri()))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
