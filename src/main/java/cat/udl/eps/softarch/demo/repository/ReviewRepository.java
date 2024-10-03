package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long>{
}
