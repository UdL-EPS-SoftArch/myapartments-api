package cat.udl.eps.softarch.demo.handler;


import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.domain.Room;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import cat.udl.eps.softarch.demo.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
@RepositoryEventHandler
public class RoomEventHandler {

    final Logger logger = (Logger) LoggerFactory.getLogger(Room.class);
    final ApartmentRepository apartmentRepository;

    public RoomEventHandler(ApartmentRepository ApartmentRepository) {
        this.apartmentRepository = ApartmentRepository;
    }


    @HandleBeforeCreate
    public void handleBeforeCreate(Room room) {


        Owner owner = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        room.setCreatedBy(owner);
        List<Apartment> apartmentList = apartmentRepository.findByOwner(owner);
        if(!apartmentList.isEmpty())
            room.setApart(apartmentList.get(0));
        logger.info("New room created: {}" + room);

    }



}
