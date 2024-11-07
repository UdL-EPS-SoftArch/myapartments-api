Feature: Update Room
  In order to use the app
  As an owner
  I must be able to update Room

  Background:
    Given There is a registered user with username "demo" and password "password" and email "demo@sample.app"
    Given There is a registered owner with username "owner" and password "password" and email "owner@sample.app"
    Given There is a registered owner with username "owner1" and password "password" and email "owner1@sample.app"
    Given There is a apartment with the name "testApartment", floor "0", address "roomAddressX", postal code "postalCodeX", city "cityX", country "countryX", description "roomDescriptionX" and a creation date "2024-10-18T17:44:30.551316+00:00" by owner username "owner1"
    Given There is a room with surface "30", is occupied "true", has window "true", has desk "false" and have bed "true", by owner username "owner1" and the apartment_name "testApartment"

  Scenario: Update Room without being logged in
    Given I'm not logged in
    When I update the room "1"
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario: Update Room being logged in as owner
    Given I login as "owner1" with password "password"
    When I update the room "1"
    Then The response code is 200

  Scenario: Update Room being logged in without surface
    Given I login as "owner1" with password "password"
    When I update the room "1"
    Then The response code is 200



  Scenario: Update Room being logged in as owner without occupancy status
    Given I login as "owner1" with password "password"
    When I update the room "1"
    Then The response code is 200

  Scenario: Update Room with non-existing ID
    Given I login as "wrong-user" with password "wrong-password"
    When I update the room "1"
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario: Update Room that not exist
    Given I login as "owner" with password "password"
    When I update the room "3"
    Then The response code is 404

  Scenario: Update Room as a non-owner
    Given I login as "demo" with password "password"
    When I update the room "1"
    Then The response code is 403
    And The error message is "Forbidden"


  Scenario: Update Room as a other owner
    Given I login as "owner" with password "password"
    When I update the room "1"
    Then The response code is 403
    And The error message is "Unauthorized owner."

