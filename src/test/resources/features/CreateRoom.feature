Feature: Create Room
  In order to use the app
  As a user
  I must be able to create Romm

  Background:
    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"
    Given There is apartment with name "test" and floor "2", address "123 Wall Street", postalCode "10005", city "New York" and country "United States of America", description "...", registrationDate "2024-10-16T10:30:00-04:00" by owner the username "test"

  Scenario: Create a new Room while logged in
    Given I login as "owner" with password "password"
    When I create a Room with name "test" and ocupied "false" and window "false" and desk "false", and bed "false"
    Then The response code is 201

  Scenario: Create a Room without being logged in
    Given I'm not logged in
    When I create a Room with name "test1" and ocupied "false" and window "false" and desk "false", and bed "false"
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario: Create a occupied Room while logged in
    Given I login as "owner" with password "password"
    And I don't have any Room
    When I create a Room with name "test2" and ocupied "true" and window "false" and desk "false", and bed "true"
    Then The response code is 400
    And The error message is "occupied cannot be true"

  Scenario: Create a Room without windowRoom while logged in
    Given I login as "owner" with password "password"
    And I don't have any Room
    When I create a Room with name "test3" and ocupied "false" and window "false" and desk "true", and bed "true"
    Then The response code is 400
    And The error message is "Window cannot be empty"

  Scenario: Create a Room without bed while logged in
    Given I login as "owner" with password "password"
    And I don't have any Room
    When I create a Room with name "test4" and ocupied "true" and window "false" and desk "true", and bed "false"
    Then The response code is 400
    And The error message is "Bed  cannot be empty"