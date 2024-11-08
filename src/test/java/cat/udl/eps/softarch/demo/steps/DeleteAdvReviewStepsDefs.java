package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.domain.Review;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.ReviewRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteAdvReviewStepsDefs {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StepDefs stepDefs;

    @And("There is an advertisement review with title {string}, description {string}, rating {string}, advertisement title {string}")
    public void thereIsAnAdvertisementReviewWithTitleDescriptionRatingAdvertisementTitle(String title, String description, String rating, String adTitle) {
        Advertisement advertisement = advertisementRepository.findByTitle(adTitle).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Advertisement with title " + adTitle + " not found"));

        Review review = new Review();
        review.setTitle(title);
        review.setDescription(description);
        review.setRating(new BigDecimal(rating));
        review.setAdvertisement(advertisement);

        reviewRepository.save(review);
    }

    @When("I delete the review with title {string} for the advertisement with title {string}")
    public void iDeleteTheReviewWithTitleForTheAdvertisementWithTitle(String reviewTitle, String adTitle) throws Exception {
        Review review = reviewRepository.findByTitle(reviewTitle).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Review with title " + reviewTitle + " not found"));

        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/reviews/{id}", review.getId())
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }

    @Then("The review should be deleted successfully")
    public void theReviewShouldBeDeletedSuccessfully() throws Exception {
        stepDefs.result.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @When("I attempt to delete a non-existent review with id {string}")
    public void iAttemptToDeleteANonExistentReviewWithId(String reviewId) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/reviews/{id}", Long.parseLong(reviewId))
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }

    @Then("I should receive a not found error")
    public void iShouldReceiveANotFoundError() throws Exception {
        stepDefs.result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @When("I attempt to delete the review with title {string} without authentication")
    public void iAttemptToDeleteTheReviewWithoutAuthentication(String reviewTitle) throws Exception {
        Review review = reviewRepository.findByTitle(reviewTitle).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Review with title " + reviewTitle + " not found"));

        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/reviews/{id}", review.getId()) // sin autenticación
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }

    @Then("I should receive an unauthorized error")
    public void iShouldReceiveAnUnauthorizedError() throws Exception {
        stepDefs.result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}