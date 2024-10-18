package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.domain.Room;
import cat.udl.eps.softarch.demo.repository.RoomRepository;

import java.util.List;

public class RoomUtils {
    public static Room getRoom(RoomRepository roomRepository, Long roomId) {
        List<Room> roomList = roomRepository.findById(roomId);
        return roomList.get(0);
    }

    public static Room getRoom(RoomRepository roomRepository, Apartment apartment, Owner owner) {
        List<Room> roomList = roomRepository.findByCreatedByAndApart(owner, apartment);
        if (roomList.isEmpty()) {
            return new Room();
        }
        return roomList.get(0);
    }

    public static Room buildRoom(String surface, String IsOccupied, String HasWindow, String HasDesk, String HasBed, Owner owner, Apartment apart) {
        int parsed_surface = Integer.parseInt(surface);
        boolean isOccupied = Boolean.parseBoolean(IsOccupied);
        boolean hasWindow = Boolean.parseBoolean(HasWindow);
        boolean hasDesk = Boolean.parseBoolean(HasDesk);
        boolean hasBed = Boolean.parseBoolean(HasBed);
        return Room.builder().surface(parsed_surface).isOccupied(isOccupied).hasDesk(hasDesk).hasBed(hasBed).hasWindow(hasWindow).apart(apart).createdBy(owner).build();
    }
}
