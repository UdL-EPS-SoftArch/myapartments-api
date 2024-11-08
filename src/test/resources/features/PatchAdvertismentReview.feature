Feature: Patch Advertisement
  In order to update advertisement details
  as an authenticated owner
  I want to patch an existing advertisement's data

  Background:
    Given There is a registered user with username "user" and password "password" and email "test@test.com"
    And There is an existing apartment with id "1" named "Luxury Suite"
    And There is an existing advertisement status "Available"

  Scenario: Patch an existing advertisement review
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    And There is an advertisement review with title "Good place", description "Clean and comfortable", rating "5", advertisement title "Luxury Suite Ad"
    And I login as "owner" with password "password"
    When I patch the review with title "Good place" to have new description "Updated and comfortable" and new rating "4"
    Then The review should be updated with new description "Updated and comfortable" and new rating "4"
    And The response code is 200

  Scenario: Attempt to patch a non-existent review
    Given I login as "owner" with password "password"
    When I attempt to patch a non-existent review with id "9999" to have new description "Should not exist" and new rating "3"
    Then The response code is 404

  Scenario: Attempt to patch an advertisement review without authentication
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    And There is an advertisement review with title "Good place", description "Clean and comfortable", rating "5", advertisement title "Luxury Suite Ad"
    Given I'm not logged in
    When I attempt to patch the review with title "Good place" without authentication to have new description "Unauthorized update" and new rating "1"
    Then The response code is 401
