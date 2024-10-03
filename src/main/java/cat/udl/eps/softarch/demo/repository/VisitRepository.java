package cat.udl.eps.softarch.demo.repository;


import cat.udl.eps.softarch.demo.domain.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends CrudRepository<Visit, Long>, PagingAndSortingRepository<Visit, Long> {

    Optional<Visit> findById(@Param("id") Long id);
    List<Visit> findByWhen(@Param("when") ZonedDateTime when);
}
