Feature: Patch an Advertisement Status
  In order to make changes on a property
  As an owner
  I want to patch a property

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@user.app"
    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"

  Scenario: Patch an Advertisement Status when not logged in
    Given I'm not logged in
    And There is an advertisement Status already created with status "Available"
    When I patch an advertisementStatus with status "Available" and change it to "Occupied"
    Then The response code is 401
    And The error message is "Unauthorized"
    And The advertisementStatus has not been patched to "Occupied"

  Scenario: Patch a Advertisement Status when logged in as a user
    Given I login as "user" with password "password"
    And There is an advertisement Status already created with status "Available"
    When I patch an advertisementStatus with status "Available" and change it to "Occupied"
    Then The response code is 403
    And The error message is "Forbidden"
    And The advertisementStatus has not been patched to "Occupied"

  Scenario: Patch a Advertisement Status when logged in as a owner
    Given I login as "owner" with password "password"
    And There is an advertisement Status already created with status "Available"
    When I patch an advertisementStatus with status "Available" and change it to "Occupied"
    Then The response code is 403
    And The advertisementStatus has been patched to "Available"


  Scenario: Patch a Advertisement Status when logged in as a owner
    Given I login as "admin" with password "password"
    And There is an advertisement Status already created with status "Available"
    When I patch an advertisementStatus with status "Available" and change it to "Occupied"
    Then The response code is 204
    And The advertisementStatus has been patched to "Occupied"


