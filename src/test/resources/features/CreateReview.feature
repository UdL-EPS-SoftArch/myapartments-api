Feature: Create a review for an advertisement
  In order to make a review
  As a user
  I want to create my review for an existing advertisement

  Background:
    Given There is a registered user with username "user" and password "password" and email "test@test.com"
    And There is an existing apartment with id "1" named "Luxury Suite"
    And There is an existing advertisement status "Available"

  Scenario: Create a review with all required attributes
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    When I create a review with title "Great experience", description "Everything was excellent" and rating "4" for the advertisement with title "Luxury Suite Ad"
    Then The review should be created with title "Great experience"
    And The review should have description "Everything was excellent"
    And The review should have rating "4"
    Then The response code is 201
    
  Scenario: Attempt to create a review without a description
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    When I attempt to create a review with title "Review Title", no description and rating "4.5" for the advertisement with title "Luxury Suite Ad"
    Then The response code is 400

  Scenario: Attempt to create a review without a title
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    When I attempt to create a review with no title, description "Sample description" and rating "4.5" for the advertisement with title "Luxury Suite Ad"
    Then The response code is 400

  Scenario: Attempt to create a review without a rating
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    When I attempt to create a review with title "Review Title", description "Sample description" and no rating for the advertisement with title "Luxury Suite Ad"
    Then The response code is 400
