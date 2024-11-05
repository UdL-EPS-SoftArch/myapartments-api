Feature: Patch Advertisement
  In order to update advertisement details
  as an authenticated owner
  I want to patch an existing advertisement's data

  Scenario: Patch an existing apartment advertisement
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And I login as "owner" with password "password"
    And There is an existing advertisement status "Available"
    And There is an advertisement with title "Apartment for rent", description "Old description", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    When I patch the apartment advertisement with title "Apartment for rent" and new description "Updated description"
    Then The response code is 204


  Scenario: Patch a non-existing apartment advertisement
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    Given I login as "owner" with password "password"
    And There is an existing advertisement status "Available"
    When I patch the apartment advertisement with title "Non-existing title" and new description "Should not update"
    Then The response code is 404

  Scenario: Patch an advertisement when not authenticated
    Given There is an existing apartment with id "1" named "Cozy Apartment"
    And There is an existing advertisement status "Available"
    And There is an advertisement with title "Apartment for rent", description "Old description", price "1200", zipCode "12345", address "456 Elm St", country "Spain", status "Available", apartment title "Cozy Apartment"
    Given I'm not logged in
    When I patch the apartment advertisement with title "Apartment for rent" and new description "Updated description"
    Then The response code is 401
