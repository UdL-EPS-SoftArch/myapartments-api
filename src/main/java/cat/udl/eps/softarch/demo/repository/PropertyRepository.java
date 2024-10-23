package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends CrudRepository<Property, Long>{
    List<Property> findByDescription(@Param("description") String description);

}
