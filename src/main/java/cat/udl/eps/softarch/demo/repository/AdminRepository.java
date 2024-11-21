package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AdminRepository extends CrudRepository<User, String>, PagingAndSortingRepository<User, String> {
  
}
