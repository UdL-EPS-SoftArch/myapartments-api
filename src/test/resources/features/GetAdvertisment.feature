Feature: Get Advertisement
  In order to use the app
  users can retrieve advertisement details for existing apartments

  Scenario: Get an existing apartment advertisement by title
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And I login as "owner" with password "password"
    And There is an existing advertisement status "Available"
    And There is an advertisement with title "Apartment for rent", description "A beautiful apartment", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    When I get the apartment advertisement with title "Apartment for rent"
    Then The response code is 200

  Scenario: Get a non-existing apartment advertisement by title
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And I login as "owner" with password "password"
    And There is an existing advertisement status "Available"
    And There is an advertisement with title "Apartment for rent", description "A beautiful apartment", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    When I get the apartment advertisement with title "non existing title"
    Then The response code is 404


  Scenario: Get apartment advertisement when not authenticated
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And There is an existing advertisement status "Available"
    And There is an advertisement with title "Apartment for rent", description "A beautiful apartment", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    Given I'm not logged in
    When I get the apartment advertisement with title "Apartment for rent"
    Then The response code is 200
