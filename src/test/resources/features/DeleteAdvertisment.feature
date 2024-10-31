Feature: Delete Advertisement
  In order to use the app
  owners
 can delete advertisement for an existing apartment



  Scenario: Delete an existing apartment advertisement with existing status as owner
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And I login as "owner" with password "password"
    And There is an existing advertisement status "Available"
    And There is an advertisement with title "Apartment for rent", description "A beautiful apartment", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    When I delete the apartment advertisement with title "Apartment for rent"
    Then The response code is 200

  Scenario: Delete a non-existing apartment as owner
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And I login as "owner" with password "password"
    And There is an existing advertisement status "Available"
    And There is an advertisement with title "Apartment for rent", description "A beautiful apartment", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    When I delete the apartment advertisement with title "non existing title"
    Then The response code is 404

  Scenario: Delete apartment advertisement when not authenticated
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And There is an existing advertisement status "Available"
    And There is an advertisement with title "Apartment for rent", description "A beautiful apartment", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    Given I'm not logged in
    When I delete the apartment advertisement with title "Apartment for rent"
    Then The response code is 401



