package cat.udl.eps.softarch.demo.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import cat.udl.eps.softarch.demo.domain.Review;
import cat.udl.eps.softarch.demo.repository.ReviewRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

public class GetReviewStepDefs {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StepDefs stepDefs;

    @When("I get the review with title {string}")
    public void iGetTheReviewWithTitle(String title) throws Exception {
        Review review = reviewRepository.findByTitle(title).stream().findFirst().orElse(null);

        if (review == null) {
            stepDefs.result = stepDefs.mockMvc.perform(
                    get("/reviews/{id}", -1)
                            .accept(MediaType.APPLICATION_JSON)
            ).andDo(print());
        } else {
            stepDefs.result = stepDefs.mockMvc.perform(
                    get("/reviews/{id}", review.getId())
                            .accept(MediaType.APPLICATION_JSON)
            ).andDo(print());
        }
    }
}
