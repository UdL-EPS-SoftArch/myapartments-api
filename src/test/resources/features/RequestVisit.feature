# Created by abdee at 17/10/24
Feature: Request Visit
  As a user
  I want to request a visit to an advertisement
  So that I can see the advertisement in person

  Scenario: Successful visit request
    Given There is an advertisement with id 1
    When I request a visit to the advertisement with id 1
    Then The visit is successfully requested