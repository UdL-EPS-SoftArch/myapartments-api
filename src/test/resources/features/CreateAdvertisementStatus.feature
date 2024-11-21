Feature: AdvertisementStatus Creation

  Scenario: Create an AdvertisementStatus with a valid status

    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"
    And I login as "admin" with password "password"
    When I create a new advertisement status with status "PENDING"
    Then The advertisement status has been created with status "PENDING"

  Scenario: Create an AdvertisementStatus with an invalid user

    Given There is a registered user with username "user" and password "password" and email "owner@example.com"
    And I login as "user" with password "password"
    When I create a new advertisement status with status "PENDING"
    Then The response code is 403

  Scenario: Attempt to create an AdvertisementStatus without a status

    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"
    And I login as "admin" with password "password"
    When I create a new advertisement status with status ""
    Then The response code is 400


  Scenario: Attempt to create an AdvertisementStatus without login

    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"
    When I create a new advertisement status with status ""
    Then The response code is 401

