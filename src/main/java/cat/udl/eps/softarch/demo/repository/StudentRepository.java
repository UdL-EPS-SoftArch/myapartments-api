package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Property;
import cat.udl.eps.softarch.demo.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, String> {
    Student findByEmail(@Param("email") String email);
}
