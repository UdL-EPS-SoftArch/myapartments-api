package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.domain.Room;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import cat.udl.eps.softarch.demo.repository.OwnerRepository;
import cat.udl.eps.softarch.demo.repository.RoomRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UpdateRoomStepsDefs {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @When("I update a room called {string}")
    public void iUpdateAnApartmentCalled(String apartmentName) throws Exception {
        Apartment apartment = apartmentRepository.findByName(apartmentName).get(0);
        Room room = RoomUtils.getRoom(roomRepository, apartment);

        JSONObject json = new JSONObject();
        json.put("surface", 20);
        json.put("isOccupied", true);
        json.put("hasWindow", true);
        json.put("hasDesk", true);
        json.put("hasBed", true);

        stepDefs.result = stepDefs.mockMvc.perform(patch(room.getUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString())
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

        stepDefs.result.andReturn().getResponse().getHeader("Location");
    }
}


