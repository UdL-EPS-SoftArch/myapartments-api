Feature: Update Apartment
  In order to manage apartments as an Owner
  I want to update an apartment's feature

  Scenario: Update an apartment's name as Owner
    Given I login as "owner" with password "password"
    And Exists an apartment registered as "Cozy Loft"
    When I update it from name "Cozy Loft" to "Super Cozy Loft"
    Then The response code is 204
    And The apartment "Cozy Loft" name is "Super Cozy Loft"


  Scenario: Update an apartment's description as Owner
    Given I login as "owner" with password "password"
    And Exists an apartment registered as "Cozy Loft" with description "A cozy loft in the center of Barcelona"
    When I update "Cozy Loft" from description "A cozy loft in the center of Barcelona" to "A super cozy loft in the center of Barcelona!"
    Then The response code is 204
    And The apartment "Cozy Loft" description is "A super cozy loft in the center of Barcelona!"
