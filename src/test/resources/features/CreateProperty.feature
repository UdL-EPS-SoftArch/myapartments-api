Feature: Create Property
  In order to rent properties to students
  As an User
  I want to create properties


  # Scenario: Create a shelter without being logged in
   # Given I'm not logged in
   # When I create a property with a description "description"
   # Then The response code is 401
   # And The error message is "Unauthorized"
   # And There is 0 Property created


  Scenario: Create a property with a null description:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given I login as "user" with password "password"
    When I create a property with a description "null"
    Then The response code is 400
    And There is 0 Property created

  Scenario: Create a property with an empty description:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given I login as "user" with password "password"
    When I create a property with a description ""
    Then The response code is 400
    And There is 0 Property created


  Scenario: Create a property with a valid description:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given I login as "user" with password "password"
    When I create a property with a description "description"
    Then The response code is 201
    And There is 1 Property created with description "description"