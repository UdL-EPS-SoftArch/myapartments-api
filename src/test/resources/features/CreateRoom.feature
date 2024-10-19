Feature: Create Room
  In order to use the app
  As a user
  I must be able to create Romm

  Background:
    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"
    Given There is apartment with name "test", floor "2", address "123 Wall Street", postal code "10005", city "New York", country "United States of America", description "..." and a creation date "2024-10-16T10:30:00-04:00" by owner username "test"

  Scenario: Create a new Room while logged in
    Given I login as "owner" with password "password"
    When I create a Room with name "RoomWithDefaultAttributes" and occupied "false" and window "false" and desk "false", and bed "false"
    Then The response code is 201

  Scenario: Create a Room without being logged in
    Given I'm not logged in
    When I create a Room with name "test1", occupied "false", window "false", desk "false", bed "false"
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario: Create a occupied Room while logged in
    Given I login as "owner" with password "password"
    And I don't have any Room
    When I create a Room with name "test2", occupied "true", window "false", desk "false", bed "false"
    Then The response code is 400
    And The error message is "occupied cannot be false"

  Scenario: Create a Room with windowRoom while logged in
    Given I login as "owner" with password "password"
    And I don't have any Room
    When I create a Room with name "test3", occupied "false", window "true" desk "false", bed "false"
    Then The response code is 400
    And The error message is "Window cannot be false"

  Scenario: Create a Room with bed while logged in
    Given I login as "owner" with password "password"
    And I don't have any Room
    When I create a Room with name "test4", occupied "false", window "false" and desk "false", and bed "true"
    Then The response code is 400
    And The error message is "Bed  cannot be false"

  Scenario: Create a Room with window and bed while logged in
    Given I login as "owner" with password "password"
    And I don't have any Room
    When I create a Room with name "test5", occupied "false", window "true" and desk "false", and bed "true"
    Then The response code is 400
    And The error message is "Window and Bed cannot be false"

  Scenario: Create a occupied Room with window and bed while logged in
    Given I login as "owner" with password "password"
    And I don't have any Room
    When I create a Room with name "test6", occupied "true", window "true", desk "false", bed "true"
    Then The response code is 400
    And The error message is "occupied, window and bed cannot be false"