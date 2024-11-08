package cat.udl.eps.softarch.demo.steps;
import cat.udl.eps.softarch.demo.domain.Review;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.ReviewRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;;

public class PatchAdvReviewStepsDefs {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StepDefs stepDefs;

    @When("I patch the review with title {string} to have new description {string} and new rating {string}")
    public void iPatchTheReviewWithTitleToHaveNewDescriptionAndNewRating(String reviewTitle, String newDescription, String newRating) throws Exception {
        Review review = reviewRepository.findByTitle(reviewTitle).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Review with title " + reviewTitle + " not found"));
        Map<String, String> updatedFields = new HashMap<>();
        updatedFields.put("description", newDescription);
        updatedFields.put("rating", newRating);
        String jsonContent = stepDefs.mapper.writeValueAsString(updatedFields);

        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/reviews/{id}", review.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }

    @When("I attempt to patch a non-existent review with id {string} to have new description {string} and new rating {string}")
    public void iAttemptToPatchANonExistentReview(String reviewId, String newDescription, String newRating) throws Exception {
        Map<String, String> updatedFields = new HashMap<>();
        updatedFields.put("description", newDescription);
        updatedFields.put("rating", newRating);
        String jsonContent = stepDefs.mapper.writeValueAsString(updatedFields);

        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/reviews/{id}", Long.parseLong(reviewId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }

    @When("I attempt to patch the review with title {string} without authentication to have new description {string} and new rating {string}")
    public void iAttemptToPatchTheReviewWithoutAuthentication(String reviewTitle, String newDescription, String newRating) throws Exception {
        Review review = reviewRepository.findByTitle(reviewTitle).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Review with title " + reviewTitle + " not found"));

        Map<String, String> updatedFields = new HashMap<>();
        updatedFields.put("description", newDescription);
        updatedFields.put("rating", newRating);
        String jsonContent = stepDefs.mapper.writeValueAsString(updatedFields);

        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/reviews/{id}", review.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }

    @Then("The review should be updated with new description {string} and new rating {string}")
    public void theReviewShouldBeUpdatedWithNewDescriptionAndNewRating(String expectedDescription, String expectedRating) throws Exception {
        stepDefs.result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(expectedDescription))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(new BigDecimal(expectedRating)));
    }
}