package cat.udl.eps.softarch.demo.handler;


import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.domain.Room;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import cat.udl.eps.softarch.demo.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;


@Component
@RepositoryEventHandler
public class RoomEventHandler {

    final Logger logger = LoggerFactory.getLogger(Room.class);
    final ApartmentRepository apartmentRepository;

    public RoomEventHandler(ApartmentRepository apartmentRepository, RoomRepository roomRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @HandleBeforeSave
    public void handleBeforeSave(Room room) {
        Owner owner = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Apartment apart = room.getApart();
        Owner roomOwner = apart.getOwner();
        assert roomOwner.getId() != null;
        if(!roomOwner.getId().equals(owner.getId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized owner.");        }
        logger.info("New room updated: {}", room);
    }

    @HandleBeforeCreate
    public void handleBeforeCreate(Room room) {
        Owner owner;
        try{
            owner = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not owner type.");
        }
        Apartment apart = room.getApart();
        Owner roomOwner = apart.getOwner();
        assert roomOwner.getId() != null;
        if(!roomOwner.getId().equals(owner.getId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized owner.");        }
        logger.info("New room updated: {}", room);
    }


}