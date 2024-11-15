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

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateReviewStepDefs {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StepDefs stepDefs;


    @When("I create a review with title {string}, description {string} and rating {string} for the advertisement with title {string}")
    public void iCreateAReview(String title, String description, String rating, String adTitle) throws Exception {
        Advertisement advertisement = advertisementRepository.findByTitle(adTitle).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Advertisement with title " + adTitle + " not found"));

        Review review = new Review();
        review.setTitle(title);
        review.setDescription(description);
        review.setRating(new BigDecimal(rating));
        review.setAdvertisement(advertisement);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(review))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());
    }


    @Then("The review should be created with title {string}")
    public void theReviewShouldBeCreatedWithTitle(String expectedTitle) throws Exception {
        stepDefs.result.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(expectedTitle));
    }

    @And("The review should have description {string}")
    public void theReviewShouldHaveDescription(String expectedDescription) throws Exception {
        stepDefs.result.andExpect(MockMvcResultMatchers.jsonPath("$.description").value(expectedDescription));
    }

    @And("The review should have rating {string}")
    public void theReviewShouldHaveRating(String expectedRating) throws Exception {
        BigDecimal expectRating = new BigDecimal(expectedRating);
        stepDefs.result.andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(expectRating));
    }

    @When("I attempt to create a review with title {string}, no description and rating {string} for the advertisement with title {string}")
    public void iAttemptToCreateAReviewWithoutDescription(String title, String rating, String adTitle) throws Exception {
        Advertisement advertisement = advertisementRepository.findByTitle(adTitle).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Advertisement with title " + adTitle + " not found"));

        Review review = new Review();
        review.setTitle(title);
        review.setRating(new BigDecimal(rating));
        review.setAdvertisement(advertisement);

        stepDefs.result = stepDefs.mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(review))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());
    }

    @When("I attempt to create a review with no title, description {string} and rating {string} for the advertisement with title {string}")
    public void iAttemptToCreateAReviewWithoutTitle(String description, String rating, String adTitle) throws Exception {
        Advertisement advertisement = advertisementRepository.findByTitle(adTitle).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Advertisement with title " + adTitle + " not found"));

        Review review = new Review();
        review.setDescription(description);
        review.setRating(new BigDecimal(rating));
        review.setAdvertisement(advertisement);

        stepDefs.result = stepDefs.mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(review))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());
    }

    @When("I attempt to create a review with title {string}, description {string} and no rating for the advertisement with title {string}")
    public void iAttemptToCreateAReviewWithoutRating(String title, String description, String adTitle) throws Exception {
        Advertisement advertisement = advertisementRepository.findByTitle(adTitle).stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Advertisement with title " + adTitle + " not found"));

        Review review = new Review();
        review.setTitle(title);
        review.setDescription(description);
        review.setAdvertisement(advertisement);

        stepDefs.result = stepDefs.mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(review))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print());
    }
}
