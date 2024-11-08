package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Review;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.ReviewRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UpdateReviewStepDefs {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private StepDefs stepDefs;

    @When("I update the review with title {string} to have description {string}")
    public void iUpdateReviewDescription(String title, String newDescription) throws Exception {
        List<Review> reviews = reviewRepository.findByTitle(title);

        if (reviews.isEmpty()) {
            throw new IllegalArgumentException("Review with title " + title + " not found");
        }

        Review review = reviews.get(0);
        review.setDescription(newDescription);
        reviewRepository.save(review);

        stepDefs.result = stepDefs.mockMvc.perform(put("/reviews/" + review.getId())
                        .with(AuthenticationStepDefs.authenticate())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(review))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());
    }

    @Then("The review with title {string} should have description {string}")
    public void theReviewWithTitleShouldHaveDescription(String title, String expectedDescription) {
        Review updatedReview = reviewRepository.findByTitle(title).stream().findFirst().orElse(null);

        if (updatedReview == null) {
            throw new IllegalArgumentException("Review with title " + title + " not found");
        }

        assertEquals(expectedDescription, updatedReview.getDescription());
    }

    @When("I update the review with title {string} to have rating {string}")
    public void iUpdateReviewRating(String title, String newRating) throws Exception {
        List<Review> reviews = reviewRepository.findByTitle(title);

        if (reviews.isEmpty()) {
            throw new IllegalArgumentException("Review with title " + title + " not found");
        }

        Review review = reviews.get(0);
        review.setRating(new BigDecimal(newRating));

        stepDefs.result = stepDefs.mockMvc.perform(put("/reviews/" + review.getId())
                        .with(AuthenticationStepDefs.authenticate())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(review))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());
    }

    @Then("The review with title {string} should have rating {string}")
    public void theReviewWithTitleShouldHaveRating(String title, String expectedRating) {
        Review updatedReview = reviewRepository.findByTitle(title).stream().findFirst().orElse(null);

        if (updatedReview == null) {
            throw new IllegalArgumentException("Review with title " + title + " not found");
        }

        assertEquals(new BigDecimal(expectedRating), updatedReview.getRating());
    }
}
