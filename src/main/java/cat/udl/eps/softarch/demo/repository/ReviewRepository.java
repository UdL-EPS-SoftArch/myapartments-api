package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.domain.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long>{
    List<Review> findByTitle(@Param("title") String title);;
}
