# Created by abdee at 17/10/24
Feature: Request Visit
  As a user
  I want to request a visit to an advertisement
  So that I can see the advertisement in person

  Scenario: Successful visit request
    Given There is an advertisement with title "Cozy Loft" and address "Carrer de les Flors 10"
    And  I login as "demo" with password "password"
    When I request a visit to the advertisement with title "Cozy Loft"
    Then The visit is successfully requested

  Scenario: Visit request with invalid advertisement
    Given There is an advertisement with title "Cozy Loft" and address "Carrer de les Flors 10"
    And  I login as "demo" with password "password"
    When I request a visit to the advertisement with title "Invalid Advertisement"
    Then The visit is not successfully requested

