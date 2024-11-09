package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Property;
import cat.udl.eps.softarch.demo.domain.Student;
import cat.udl.eps.softarch.demo.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ModifyStudentStepDefs {

    @Autowired
    StepDefs stepDefs;

    @Autowired
    StudentRepository studentRepository;

    Student student;


    @Given("There is a registered student with username {string} and password {string} and email {string} and phoneNumber {string} and name {string}")
    public void there_is_a_student_already_created_with_values(String username, String password, String email, String phoneNumber, String name) {
        this.student = new Student();
        student.setUsername(username);
        student.setPassword(password);
        student.encodePassword();
        student.setEmail(email);
        student.setPhoneNumber(phoneNumber);
        student.setName(name);


        this.studentRepository.save(student);
    }



    @When("I modify student {string} with username {string} and password {string} and email {string} and phoneNumber {string} and name {string}")
    public void modify_a_student_with_values(String studentEmail, String username, String password, String email, String phoneNumber, String name) throws Throwable {
        Student studentTest = studentRepository.findByEmail(studentEmail);
        String id = studentTest.getId();


        studentTest.setEmail(assignValueInput(email));
        studentTest.setPhoneNumber(assignValueInput(phoneNumber));
        studentTest.setName(assignValueInput(name));
        stepDefs.result = this.stepDefs.mockMvc.perform(
                put("/students/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(
                                stepDefs.mapper.writeValueAsString(studentTest)
                        ).put("password", assignValueInput(password)).toString())
                        .characterEncoding(StandardCharsets.UTF_8)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    private String assignValueInput(String value) {
        if(value.equals("null")){
            return null;
        }
        else {
            return value;
        }
    }

}
