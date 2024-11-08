Feature: Update Review
  In order to update reviews in the system
  As a user
  I want to be able to create and update reviews

  Background:
    Given There is a registered user with username "reviewer" and password "password123" and email "reviewer@test.com"
    Given There is an existing apartment with id "1" named "Luxury Suite"
    And There is an existing review with title "Original Review", description "Initial description", and rating "4.0" for the advertisement with title "Sample Ad"

  Scenario: Update review title and description without being logged in
    Given I'm not logged in
    When I update the review with title "Original Review" to have new title "Updated Title" and new description "Updated description"
    Then The review with title "Updated Title" should have description "Updated description"
    And The response code is 401

  Scenario: Update review title and description
    Given I login as "reviewer" with password "password123"
    When I update the review with title "Original Review" to have new title "Updated Title" and new description "Updated description"
    Then The review with title "Updated Title" should have description "Updated description"
    And The response code is 200

  Scenario: Update review rating
    Given I login as "reviewer" with password "password123"
    When I update the review with title "Original Review" to have rating "5.0"
    Then The review with title "Original Review" should have rating "5.0"
    And The response code is 200

  Scenario: Attempt to update review with invalid rating
    Given I login as "reviewer" with password "password123"
    When I update the review with title "Original Review" to have rating "-1.0"
    Then The response code is 400
    And The error message is "Rating must be between 0.0 and 5.0"

  Scenario: Update review description only
    Given I login as "reviewer" with password "password123"
    When I update the review with title "Original Review" to have description "An updated description only"
    Then The review with title "Original Review" should have description "An updated description only"
    And The response code is 200
