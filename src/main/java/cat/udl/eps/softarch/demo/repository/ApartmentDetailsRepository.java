package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.ApartmentDetails;
import cat.udl.eps.softarch.demo.domain.Room;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentDetailsRepository extends CrudRepository<ApartmentDetails, Long> {

}
