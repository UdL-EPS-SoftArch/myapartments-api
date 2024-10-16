Feature: Delete Advertisement
  In order to use the app
  Only admins and owners
 can delete advertisement for an existing apartment

  Scenario: Delete an existing apartment advertisement with existing status as admin
    Given There is an existing apartment with id "1" named "Cozy Apartment" and I login as admin with password "password"
    And There is an existing advertisement status with id "1" and status "Available"
    When I delete the apartment advertisement with id "1"
    Then The response code is 200

  Scenario: Delete an existing apartment advertisement with existing status as owner
    Given There is an existing apartment with id "1" named "Cozy Apartment" and I login as owner with password "password"
    And There is an existing advertisement status with id "1" and status "Available"
    When I delete the apartment advertisement with id "1"
    Then The response code is 200

  Scenario: Delete an existing apartment advertisement with existing status as user
    Given There is an existing apartment with id "1" named "Cozy Apartment" and I login as student with password "password"
    And There is an existing advertisement status with id "1" and status "Available"
    When I delete the apartment advertisement with id "1"
    Then The response code is 401

  Scenario: Delete a non-existing apartment as admin
    Given I login as admin with password "password"
    When I attempt to delete the apartment advertisement with id "9999"
    Then The response code is 404

  Scenario: Delete a non-existing apartment as owner
    Given I login as admin with password "password"
    When I attempt to delete the apartment advertisement with id "9999"
    Then The response code is 404

  Scenario: Delete a non-existing apartment as student
    Given I login as admin with password "password"
    When I attempt to delete the apartment advertisement with id "9999"
    Then The response code is 403

  Scenario: Delete apartment advertisement when not authenticated
    Given I'm not logged in
    And There is an existing apartment with id "1" named "Cozy Apartment"
    When I attempt to delete the apartment advertisement with id "1"
    Then The response code is 401




