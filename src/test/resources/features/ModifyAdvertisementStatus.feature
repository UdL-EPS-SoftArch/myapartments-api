Feature: Modify an Advertisement Status
  In order to make changes on a property
  As an owner
  I want to modify a property

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@user.app"
    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"

  Scenario: Modify an Advertisement Status when not logged in
    Given I'm not logged in
    And There is an advertisementStatus already created with status "Available"
    When I modify an advertisementStatus with status "Available" and change it to "Occupied"
    Then The response code is 401
    And The error message is "Unauthorized"
    And The advertisementStatus has not been modified to "Occupied"

  Scenario: Modify a Advertisement Status when logged in as a user
    Given I login as "user" with password "password"
    And There is an advertisementStatus already created with status "Available"
    When I modify an advertisementStatus with status "Available" and change it to "Occupied"
    Then The response code is 403
    And The error message is "Forbidden"
    And The advertisementStatus has not been modified to "Occupied"

  Scenario: Modify a Advertisement Status when logged in as a owner
    Given I login as "owner" with password "password"
    And There is an advertisementStatus already created with status "Available"
    When I modify an advertisementStatus with status "Available" and change it to "Occupied"
    Then The response code is 204
    And The advertisementStatus has been modified to "Occupied"

  Scenario: Modify a Advertisement Status when logged in as a owner and status null
    Given I login as "owner" with password "password"
    And There is an advertisementStatus already created with status "Available"
    When I modify an advertisementStatus with status "Available" and change it to "null"
    Then The response code is 400
    And The advertisementStatus has not been modified to "null"


