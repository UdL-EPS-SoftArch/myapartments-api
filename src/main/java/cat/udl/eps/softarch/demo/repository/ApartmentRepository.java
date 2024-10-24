package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface ApartmentRepository extends CrudRepository<Apartment, Long>, PagingAndSortingRepository<Apartment, Long> {
    @NotNull Optional<Apartment> findById(@NotNull @Param("id") Long id);
    List<Apartment> findByCountry(@Param("country") String country);
    List<Apartment> findByPostalCode(@Param("postalCode") String postalCode);
    List<Apartment> findByCity(@Param("city") String city);
    List<Apartment> findByRegistrationDate(@Param("registrationDate") Date registrationDate);
    List<Apartment> findByName(@Param("name") String name);
    List<Apartment> findByOwner(@Param("owner") Owner owner);
    List<Apartment> findByAddress(@Param("address") String address);
}
