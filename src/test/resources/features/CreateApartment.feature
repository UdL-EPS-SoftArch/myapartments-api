Feature: Create Apartment
  In order to manage apartments
  As an Owner
  I want to create apartments

  Scenario: Create an apartment as Owner
    Given There is a registered owner with username "1" and password "password" and email "owner@example.com"
    And I login as "owner" with password "password"
    When I create an apartment with name "Cozy Loft", floor "3", address "Carrer de les Flors 10", postal code "08001", city "Barcelona", country "Spain", description "A cozy loft in the center of Barcelona"
    Then The response code is 201
    And The apartment has been created with name "Cozy Loft", floor "3", address "Carrer de les Flors 10", postal code "08001", city "Barcelona", country "Spain", description "A cozy loft in the center of Barcelona"

  Scenario: Create an apartment without being logged in
    Given I'm not logged in
    When I create an apartment with name "Cozy Loft", floor "3", address "Carrer de les Flors 10", postal code "08001", city "Barcelona", country "Spain", description "A cozy loft in the center of Barcelona"
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario: Create apartment with missing name
    Given There is a registered owner with username "1" and password "password" and email "owner@example.com"
    And I login as "owner" with password "password"
    When I create an apartment with name "", floor "3", address "Carrer de les Flors 10", postal code "08001", city "Barcelona", country "Spain", description "A cozy loft in the center of Barcelona"
    Then The response code is 401
    And The error message is "Bad Request"

  Scenario: Create an apartment as Student
    Given There is a registered student with username "1" and password "password" and email "student@example.com"
    And I login as "student" with password "password"
    When I create an apartment with name "Cozy Loft", floor "3", address "Carrer de les Flors 10", postal code "08001", city "Barcelona", country "Spain", description "A cozy loft in the center of Barcelona"
    Then The response code is 403
    And The error message is "Forbidden"
