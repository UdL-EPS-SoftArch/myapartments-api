Feature: Update Advertisement
  In order to update advertisements in the system
  As a user
  I want to be able to create and update advertisements

  Background:
    Given There is a registered user with username "owner" and password "password" and email "test@test.com"
    Given There is an existing apartment with id "1" named "Luxury Suite"
    And There is an existing advertisement status "Active"

  Scenario: Create and update an advertisement without logged in
    Given I'm not logged in
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Active", apartment title "Luxury Suite"
    When I update an advertisement with title "Luxury Suite Ad" to have name "Updated Suite Ad" and address "Updated Street 123"
    Then The advertisement with title "Updated Suite Ad" should have name "Updated Suite Ad" and address "Updated Street 123"
    And The response code is 401

  Scenario: Create and update an advertisement
    Given I login as "owner" with password "password"
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Active", apartment title "Luxury Suite"
    When I update an advertisement with title "Luxury Suite Ad" to have name "Updated Suite Ad" and address "Updated Street 123"
    Then The advertisement with title "Updated Suite Ad" should have name "Updated Suite Ad" and address "Updated Street 123"
    And The response code is 200

  Scenario: Update advertisement price
    Given I login as "owner" with password "password"
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Active", apartment title "Luxury Suite"
    When I update the advertisement with title "Luxury Suite Ad" to have price "2500"
    Then The advertisement with title "Luxury Suite Ad" should have price "2500.00"
    And The response code is 200

  Scenario: Attempt to update advertisement with invalid data
    Given I login as "owner" with password "password"
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Active", apartment title "Luxury Suite"
    When I update the advertisement with title "Luxury Suite Ad" to have price "-100.00"
    Then The response code is 400
    And The error message is "must be greater than or equal to 0.01"

  Scenario: Update advertisement zip code
    Given I login as "owner" with password "password"
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Active", apartment title "Luxury Suite"
    When I update the advertisement with title "Luxury Suite Ad" to have zipCode "54321"
    Then The advertisement with title "Luxury Suite Ad" should have zipCode "54321"
    And The response code is 200

  Scenario: Update advertisement country
    Given I login as "owner" with password "password"
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Active", apartment title "Luxury Suite"
    When I update the advertisement with title "Luxury Suite Ad" to have country "Dreamland"
    Then The advertisement with title "Luxury Suite Ad" should have country "Dreamland"
    And The response code is 200

  Scenario: Update advertisement description
    Given I login as "owner" with password "password"
    Given There is an advertisement with title "Luxury Suite Ad", description "A beautiful suite", price "2000", zipCode "12345", address "High Street 123", country "Wonderland", status "Active", apartment title "Luxury Suite"
    When I update the advertisement with title "Luxury Suite Ad" to have description "An updated beautiful suite"
    Then The advertisement with title "Luxury Suite Ad" should have description "An updated beautiful suite"
    And The response code is 200


