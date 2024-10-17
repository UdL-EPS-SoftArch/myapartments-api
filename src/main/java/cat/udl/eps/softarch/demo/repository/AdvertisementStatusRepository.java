package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.AdvertisementStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdvertisementStatusRepository extends CrudRepository<AdvertisementStatus, Long> {
    boolean existsByStatus(String status);
    List<AdvertisementStatus> findByStatus(@Param("status")String status);
}
