package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.domain.Room;
import cat.udl.eps.softarch.demo.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

public class RoomUtils {
    public static Room getRoom(RoomRepository roomRepository, Long roomId) {
        Optional<Room> roomList = roomRepository.findById(roomId);
        return roomList.get();
    }

    public static Room getRoom(RoomRepository roomRepository,Apartment apartment) {
        List<Room> roomList = roomRepository.findByApart(apartment);
        if (roomList.isEmpty()) {
            return new Room();
        }
        return roomList.get(0);
    }

    public static Room buildRoom(String surface, String IsOccupied, String HasWindow, String HasDesk, String HasBed) {
        int parsed_surface = Integer.parseInt(surface);
        boolean isOccupied = Boolean.parseBoolean(IsOccupied);
        boolean hasWindow = Boolean.parseBoolean(HasWindow);
        boolean hasDesk = Boolean.parseBoolean(HasDesk);
        boolean hasBed = Boolean.parseBoolean(HasBed);
        Room room = new Room();
        room.setSurface(parsed_surface);
        room.setOccupied(isOccupied);
        room.setHasWindow(hasWindow);
        room.setHasDesk(hasDesk);
        room.setHasBed(hasBed);
        return room;
    }
}
