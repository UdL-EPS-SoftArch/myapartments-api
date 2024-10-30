Feature: Delete Advertisement
  In order to use the app
  owners
 can delete advertisement for an existing apartment



  Scenario: Delete an existing apartment advertisement with existing status as owner
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And There is an existing advertisement status "Available"
    And There is an  existing advertisement with name "name"
    When I delete the apartment advertisement with name ""
    Then The response code is 200

  Scenario: Delete a non-existing apartment as owner
    Given I login as admin with password "password"
    When I attempt to delete the apartment advertisement with id "9999"
    Then The response code is 404

  Scenario: Delete apartment advertisement when not authenticated
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And There is an existing advertisement status "Available"
    Given I'm not logged in
    Then The response code is 401




