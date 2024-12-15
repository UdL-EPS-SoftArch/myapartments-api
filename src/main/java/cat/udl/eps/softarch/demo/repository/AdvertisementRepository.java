package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.domain.Apartment;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementRepository extends CrudRepository<Advertisement, Long>, PagingAndSortingRepository<Advertisement, Long> {
    List<Advertisement> findByTitle(@Param("title") String title);
    List<Advertisement> findByApartment(@Param("apartment")@NotNull Apartment apartment);


}
