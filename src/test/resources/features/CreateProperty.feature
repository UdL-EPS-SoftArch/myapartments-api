Feature: Create Property
  In order to rent properties to students
  As an User
  I want to create properties

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@user.app"
    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"

   Scenario: Create a property without being logged in
    Given I'm not logged in
    When I create a property with a description "description"
    Then The response code is 401
    And The error message is "Unauthorized"
    And There is 0 Property created


  Scenario: Create a property with a null description:
    Given I login as "owner" with password "password"
    When I create a property with a description "null"
    Then The response code is 400
    And There is 0 Property created

  Scenario: Create a property with an empty description:
    Given I login as "owner" with password "password"
    When I create a property with a description ""
    Then The response code is 400
    And There is 0 Property created


  Scenario: Create a property as an owner with a valid description:
    Given I login as "owner" with password "password"
    When I create a property with a description "description"
    Then The response code is 201
    And There is 1 Property created with description "description"


  Scenario: Create a property as an user with a valid description:
    Given I login as "user" with password "password"
    When I create a property with a description "description"
    Then The response code is 403
    And The error message is "Forbidden"
    And There is 0 Property created