package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Apartment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApartmentRepository extends CrudRepository<Apartment, Long>, PagingAndSortingRepository<Apartment, Long> {
    Optional<Apartment> findById(@Param("id") Long id);
}
