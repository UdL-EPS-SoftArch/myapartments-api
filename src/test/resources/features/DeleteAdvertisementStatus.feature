Feature: AdvertisementStatus Creation

  Scenario: Delete an AdvertisementStatus

    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"
    And I login as "owner" with password "password"
    And There is an existing advertisement status "Available"
    When I delete the advertisement status with status "Available"
    Then The response code is 204

  Scenario: Delete a non existing AdvertisementStatus

    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"
    And I login as "owner" with password "password"
    And There is an existing advertisement status "Available"
    When I delete the advertisement status with status "Non existing"
    Then The response code is 404


