package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
