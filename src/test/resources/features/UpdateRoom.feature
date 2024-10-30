Feature: Update Room
  In order to use the app
  As an owner
  I must be able to update Room

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a apartment with the name "testApartment", floor "0", address "roomAddressX", postal code "postalCodeX", city "cityX", country "countryX", description "roomDescriptionX" and a creation date "2024-10-18T17:44:30.551316+00:00" by owner username "owner"
    Given There is a room with surface "30", is occupied "true", has window "true", has desk "false" and have bed "true", by owner username "owner" and the apartment_name "testApartment"

  Scenario: Update Room without being logged in
    Given I'm not logged in
    When I update an apartment called "testApartment"
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario: Update Room being logged in
    Given I login as "user" with password "password"
    When I update an apartment called "testApartment"
    Then The response code is 200

  Scenario: Update Room being logged in without surface
    Given I login as "user" with password "password"
    When I update an apartment called "testApartment"
    Then The response code is 200

  Scenario: Update Room being logged in without occupancy status
    Given I login as "user" with password "password"
    When I update an apartment called "testApartment"
    Then The response code is 200

  Scenario: Update Room with non-existing ID
    Given I login as "wrong-user" with password "wrong-password"
    When I update an apartment called "testApartment"
    Then The response code is 401
    And The error message is "Unauthorized"