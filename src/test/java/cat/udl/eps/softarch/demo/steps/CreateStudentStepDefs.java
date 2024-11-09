package cat.udl.eps.softarch.demo.steps;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.softarch.demo.domain.Student;
import cat.udl.eps.softarch.demo.repository.StudentRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

public class CreateStudentStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private StudentRepository studentRepository;

    @When("^I create a student with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\" and phoneNumber \"([^\"]*)\" and name \"([^\"]*)\"$")
    public void createStudent(String username, String password, String email, String phoneNumber, String name) throws Throwable {
        Student studentTest = new Student();

        studentTest.setUsername(assignValueInput(username));
        studentTest.setEmail(assignValueInput(email));
        studentTest.setPhoneNumber(assignValueInput(phoneNumber));
        studentTest.setName(assignValueInput(name));



        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new JSONObject(
                                        stepDefs.mapper.writeValueAsString(studentTest)
                                ).put("password", assignValueInput(password)).toString())
                                .characterEncoding(StandardCharsets.UTF_8))
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

    @And("^There is 0 Student created$")
    public void thereIs0StudentCreated() throws Throwable {
        assertEquals(studentRepository.count(), 0);
    }

    @And("^There is 1 Student created with username \"([^\"]*)\" and email \"([^\"]*)\" and phoneNumber \"([^\"]*)\" and name \"([^\"]*)\"$")
    public void thereIs1StudentCreated(String username, String email, String phoneNumber, String name) throws Throwable {
        assertEquals(studentRepository.count(), 1);
        Student studentToCheck = this.studentRepository.findByEmail(email);
        assertEquals(studentToCheck.getUsername(), username);
        assertEquals(studentToCheck.getName(), name);
        assertEquals(studentToCheck.getPhoneNumber(), phoneNumber);
        assertEquals(studentToCheck.getEmail(), email);
    }
}
