package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.domain.Room;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long>, PagingAndSortingRepository<Room, Long> {

    @NotNull Optional<Room> findById(@Param("long")@NotNull Long id);
    List<Room> findByApart(@Param("apart") Apartment apart);
}
