Feature: Get Advertisement
  In order to use the app
  users can retrieve advertisement details for existing apartments

  Scenario: Get an existing apartment advertisement by title
    And I login as "owner" with password "password"
    And There is an existing advertisement status "Available"
    When I get the advertisementStatus with status "Available"
    Then The response code is 200

  Scenario: Get a non existing advertisementStatus by status
    And I login as "owner" with password "password"
    And There is an existing advertisement status "Available"
    When I get the advertisementStatus with status "EMPTY"
    Then The response code is 404

