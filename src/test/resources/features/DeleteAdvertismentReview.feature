Feature: Delete a review for an advertisement
  In order to delete a review
  As a user
  I want to delete my review for an existing advertisementBackground:


  Background:
    Given There is a registered user with username "user" and password "password" and email "test@test.com"
    And There is an existing apartment with id "1" named "Luxury Suite"
    And There is an existing advertisement status "Available"


  Scenario: Delete an existing advertisement review
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    And There is an advertisement review with title "Good place", description "Clean and comfortable", rating "5", advertisement title "Luxury Suite Ad"
    And I login as "owner" with password "password"
    When I delete the review with title "Good place" for the advertisement with title "Luxury Suite Ad"
    Then The response code is 200

  Scenario: Attempt to delete a non-existent review
    Given I login as "owner" with password "password"
    When I attempt to delete a non-existent review with id "9999"
    Then The response code is 404

  Scenario: Attempt to delete an advertisement review without authentication
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Available", apartment title "Luxury Suite"
    And There is an advertisement review with title "Good place", description "Clean and comfortable", rating "5", advertisement title "Luxury Suite Ad"
    Given I'm not logged in
    When I attempt to delete the review with title "Good place" without authentication
    Then The response code is 401






