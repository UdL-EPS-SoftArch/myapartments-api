Feature: Create Room
  In order to use the app
  As a user
  I must be able to create Room

  Background:
    Given There is a registered user with username "demo" and password "password" and email "demo@sample.app"
    Given There is a registered owner with username "owner" and password "password" and email "owner@sample.app"
    Given There is a registered owner with username "owner1" and password "password" and email "owner1@sample.app"
    Given There is a apartment with the name "testApartment", floor "0", address "roomAddressX", postal code "postalCodeX", city "cityX", country "countryX", description "roomDescriptionX" and a creation date "2024-10-18T17:44:30.551316+00:00" by owner username "owner1"


  Scenario: Create a new Room while logged in
    Given I login as "owner1" with password "password"
    When I create a Room with the surface "30", occupied "false", window "false", desk "false" and bed "false" and the apartment_name "testApartment"
    Then The response code is 201

  Scenario: Create a Room without being logged in
    Given I'm not logged in
    When I create a Room with the surface "30", occupied "false", window "false", desk "false" and bed "false" and the apartment_name "testApartment"
    Then The response code is 401

  Scenario: Create Room as a non-owner
    Given I login as "demo" with password "password"
    When I create a Room with the surface "30", occupied "false", window "false", desk "false" and bed "false" and the apartment_name "testApartment"
    Then The response code is 403
    And The error message is "Not owner type."


  Scenario: Create Room as a other owner
    Given I login as "owner" with password "password"
    When I create a Room with the surface "30", occupied "false", window "false", desk "false" and bed "false" and the apartment_name "testApartment"
    Then The response code is 403
    And The error message is "Unauthorized owner."