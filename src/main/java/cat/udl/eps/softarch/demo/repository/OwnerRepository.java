package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, String>, PagingAndSortingRepository<Owner, String> {
    Optional<Owner> findById(@Param("id") String id);
    List<Owner> findByName(@Param("name") String name);

    // List<Owner> findByPhone(@Param("phoneNumber") String phoneNumber);
    // List<Apartment> findApartmentsByUserId(@Param("id") String id);
}
