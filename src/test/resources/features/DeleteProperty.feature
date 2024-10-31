Feature: Delete Property
  In order to make changes on a property
  As an Owner
  I want to delete properties

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@user.app"
    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"
    Given There is a property already created with description "description"

  Scenario: Delete a property without being logged in
    Given I'm not logged in
    When I delete a property with a description "description"
    Then The response code is 401
    And The error message is "Unauthorized"
    And There is 1 Property created with description "description"


  Scenario: Delete a property as an owner with a valid description:
    Given I login as "owner" with password "password"
    When I delete a property with a description "description"
    Then The response code is 204
    And There is 0 Property created with description "description"


  Scenario: Delete a property as an user with a valid description:
    Given I login as "user" with password "password"
    When I delete a property with a description "description"
    Then The response code is 403
    And The error message is "Forbidden"
    And There is 1 Property created with description "description"