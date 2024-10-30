Feature: Create advertisement
  In order to use the app
  As a user
  I want to create my advertisement for an existing apartment with a specific status

  Scenario: Create new advertisement for existing apartment with existing status
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And There is an existing advertisement status "Available"
    When I create a new advertisement with title "Apartment for rent", description "A beautiful apartment", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    Then The response code is 201
    And The advertisement has been created with title "Apartment for rent"

  Scenario: Create advertisement with missing title
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And There is an existing advertisement status "Available"
    When I create a new advertisement with title "", description "A beautiful apartment", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created an advertisement

  Scenario: Create advertisement with missing description
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And There is an existing advertisement status "Available"
    When I create a new advertisement with title "Apartment for rent", description "", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created an advertisement

  Scenario: Create advertisement with invalid price
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And There is an existing advertisement status "Available"
    When I create a new advertisement with title "Apartment for rent", description "A beautiful apartment", price "-100", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    Then The response code is 400
    And The error message is "must be greater than or equal to 0.01"
    And It has not been created an advertisement

  Scenario: Create advertisement with missing zip code
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And There is an existing advertisement status "Available"
    When I create a new advertisement with title "Apartment for rent", description "A beautiful apartment", price "1200", zipCode "", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created an advertisement

  Scenario: Create advertisement with missing country
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And There is an existing advertisement status "Available"
    When I create a new advertisement with title "Apartment for rent", description "A beautiful apartment", price "1200", zipCode "12345", address "456 Elm St", country "", status "Available", apartment title "Cozy Apartment"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created an advertisement

  Scenario: Create advertisement with missing ad status
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    When I create a new advertisement with title "Apartment for rent", description "A beautiful apartment", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "", apartment title "Cozy Apartment"
    Then The response code is 400
    And The error message is "must not be null"
    And It has not been created an advertisement
  