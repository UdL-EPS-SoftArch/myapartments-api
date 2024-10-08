package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.AdvertisementStatus;
import org.springframework.data.repository.CrudRepository;

public interface AdvertisementStatusRepository extends CrudRepository<AdvertisementStatus, Long> {
}
