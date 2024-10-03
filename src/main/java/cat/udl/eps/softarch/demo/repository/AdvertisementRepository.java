package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Advertisement;
import org.springframework.data.repository.CrudRepository;

public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {
}
