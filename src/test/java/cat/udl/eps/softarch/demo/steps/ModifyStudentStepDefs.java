package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Property;
import cat.udl.eps.softarch.demo.domain.Student;
import cat.udl.eps.softarch.demo.repository.StudentRepository;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class ModifyStudentStepDefs {

    @Autowired
    StepDefs stepDefs;

    @Autowired
    StudentRepository studentRepository;

    Student studentTest;


    @Given("There is a registered student with username {string} and password {string} and email {string} and phoneNumber {string} and name {string}")
    public void there_is_a_student_already_created_with_values(String username, String password, String email, String phoneNumber, String name) {
        this.studentTest = new Student();
        studentTest.setUsername(username);
        studentTest.setPassword(password);
        studentTest.setEmail(email);
        studentTest.setPhoneNumber(phoneNumber);
        studentTest.setName(name);

        this.studentRepository.save(studentTest);
    }


}
