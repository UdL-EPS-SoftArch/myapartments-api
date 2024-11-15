Feature: Update Review
  In order to update reviews in the system
  As a user
  I want to be able to create and update reviews

  Background:
    Given There is a registered user with username "user" and password "password123" and email "reviewer@test.com"
    Given There is an existing apartment with id "1" named "Luxury Suite"
    Given There is an existing advertisement status "Available"
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    When I create a review with title "Great experience", description "Everything was excellent" and rating "4" for the advertisement with title "Luxury Suite Ad"

  Scenario: Update review title and description
    Given I login as "user" with password "password123"
    When I update the review with title "Great experience" to have new title "Updated Title" and new description "Updated description"
    Then The review with title "Updated Title" should have description "Updated description"
    And The response code is 200

  Scenario: Update review rating
    Given I login as "user" with password "password123"
    When I update the review with title "Great experience" to have rating "5.0"
    Then The review with title "Great experience" should have rating "5.00"
    And The response code is 200

  Scenario: Attempt to update review with invalid rating
    Given I login as "user" with password "password123"
    When I update the review with title "Great experience" to have rating "-1.0"
    Then The response code is 400
    And The error message is "must be greater than or equal to 0"

  Scenario: Update review description only
    Given I login as "user" with password "password123"
    When I update the review with title "Great experience" to have description "An updated description only"
    Then The review with title "Great experience" should have description "An updated description only"
    And The response code is 200
