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

    // Logger de SLF4J sin necesidad de casting
    final org.slf4j.Logger logger = LoggerFactory.getLogger(Room.class);
    final ApartmentRepository apartmentRepository;

    // Constructor con inyección de dependencias
    public RoomEventHandler(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @HandleBeforeCreate
    public void handleBeforeCreate(Room room) {
        // Obtenemos el owner desde el contexto de seguridad
        Owner owner = (Owner) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        room.setOwner(owner);

        // Buscamos apartamentos asociados al owner
        List<Apartment> apartmentList = apartmentRepository.findByOwner(owner);
        if (!apartmentList.isEmpty()) {
            room.setApart(apartmentList.get(0));
        }

        // Log del evento
        logger.info("New room created: {}", room);
    }
}

