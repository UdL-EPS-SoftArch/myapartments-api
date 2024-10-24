Feature: Create Room
  In order to use the app
  As a user
  I must be able to create Room

  Background:
    Given There is a registered owner with username "owner1" and password "password" and email "owner1@sample.app"
    Given There is a apartment with the name "test", floor "2", address "123 Wall Street", postal code "10005", city "New York", country "United States of America", description "..." and a creation date "2024-10-16T10:30:00-04:00" by owner username "owner"


  Scenario: Create a new Room while logged in
    Given I login as "owner1" with password "password"
    When I create a Room with the surface "30", occupied "false", window "false", desk "false" and bed "false", by owner username "owner1" and the apartment_name "test"
    Then The response code is 201

  Scenario: Create a Room without being logged in
    Given I'm not logged in
    When I create a Room with the surface "30", occupied "false", window "false", desk "false" and bed "false", by owner username "owner1" and the apartment_name "test"
    Then The response code is 401