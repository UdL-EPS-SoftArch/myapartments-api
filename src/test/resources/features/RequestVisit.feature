# Created by abdee at 17/10/24
Feature: Request Visit
  As a user
  I want to request a visit to an advertisement
  So that I can see the advertisement in person

  Scenario: Successful visit request
    Given There is an advertisement with and title "Cozy Loft" and address "Carrer de les Flors 10"
    When I request a visit to the advertisement with title "Cozy Loft"
    Then The visit is successfully requested