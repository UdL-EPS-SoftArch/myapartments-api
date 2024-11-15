package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Student;
import cat.udl.eps.softarch.demo.repository.StudentRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteStudentStepDefs {
    @Autowired
    StepDefs stepDefs;
    @Autowired
    StudentRepository studentRepository;


    @When("^I delete a student with an email \"([^\"]*)\"$")
    public void modifyProperty(String email)throws Throwable {
        Student student = studentRepository.findByEmail(email);
        String studentId = student.getId();
        this.stepDefs.result = this.stepDefs.mockMvc.perform(delete("/students/"+studentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(student))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}