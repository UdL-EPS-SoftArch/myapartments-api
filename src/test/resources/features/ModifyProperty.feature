Feature: Modify a Property
  In order to make changes on a property
  As an owner
  I want to modify a property

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@user.app"
    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"
    Given There is a property already created with description "This is the description to modify"


  Scenario: Modify a Property when not logged in
    Given I'm not logged in
    When I modify a property with a description "This is the description to modify" and change it to "This is the modified description"
    Then The response code is 401
    And The error message is "Unauthorized"
    And The property has not been modified to "This is the modified description"


  Scenario: Modify a Property when logged in as an owner
    Given I login as "owner" with password "password"
    When I modify a property with a description "This is the description to modify" and change it to "This is the modified description"
    Then The response code is 204
    And The property has been modified with the description "This is the modified description"

  Scenario: Modify a Property with null description
    Given I login as "owner" with password "password"
    When I modify a property with a description "This is the description to modify" and change it to "null"
    Then The response code is 400
    And The property has not been modified to "This is the modified description"

  Scenario: Modify a Property with void description
    Given I login as "owner" with password "password"
    When I modify a property with a description "This is the description to modify" and change it to ""
    Then The response code is 400
    And The property has not been modified to "This is the modified description"

  Scenario: Modify Property as an user with a valid description
    Given I login as "user" with password "password"
    When I modify a property with a description "This is the description to modify" and change it to "This is the modified description"
    Then The response code is 403
    And The error message is "Forbidden"
    And The property has not been modified to "This is the modified description"


