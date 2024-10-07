package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Property, Long>{
}
