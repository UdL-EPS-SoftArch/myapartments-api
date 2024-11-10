# Created by abdee at 10/11/24
Feature: Modify Visit
  As a user
  I want to modify a visit to an advertisement
  So that I can change the visit details

  Scenario: Modify visit request
    Given There is an advertisement with title "Cozy Loft" and address "Carrer de les Flors 10"
    And I login as "demo" with password "password"
    And I request a visit to the advertisement with title "Cozy Loft"
    When I modify the visit request to the advertisement with title "Cozy Loft" with new date "2025-08-01T10:00:00Z"
    Then The visit is successfully modified