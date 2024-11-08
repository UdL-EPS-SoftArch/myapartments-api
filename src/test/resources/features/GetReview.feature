Feature: Get Review
  In order to use the app
  users can retrieve review details for existing advertisements

  Background:
    Given There is a registered user with username "user" and password "password" and email "test@test.com"
    And There is an existing apartment with id "1" named "Luxury Suite"
    And There is an existing advertisement status "Available"

  Scenario: Get an existing review by title
    Given I login as "user" with password "password"
    And There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    When I create a review with title "Great experience", description "Everything was excellent" and rating "4" for the advertisement with title "Luxury Suite Ad"
    And I get the review with title "Great experience"
    Then The response code is 200

  Scenario: Get a non-existing review by title
    Given I login as "user" with password "password"
    And There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    When I create a review with title "Great experience", description "Everything was excellent" and rating "4" for the advertisement with title "Luxury Suite Ad"
    When I get the review with title "Non-existing Review"
    Then The response code is 404

  Scenario: Get review when not authenticated
    Given I'm not logged in
    And There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    When I create a review with title "Great experience", description "Everything was excellent" and rating "4" for the advertisement with title "Luxury Suite Ad"
    And I get the review with title "Great experience"
    Then The response code is 200
