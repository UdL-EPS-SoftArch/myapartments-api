package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Integer> {

    List<Room> findById(@Param("long") Long id);
    List<Room> findByCreatedBy(@Param("owner") Owner owner);
    List<Room> findByApart(@Param("apart") Apartment apart);
    List<Room> findByCreatedByAndApart(@Param("owner") Owner owner, @Param("apart") Apartment apart);
}
