Feature: Update Apartment
  In order to manage apartments
  As an Owner
  I want to update an apartment's feature

  Scenario: Update an apartment's name as Owner
    Given I login as "owner" with password "password"
    And Exists an apartment registered with the name "Cozy Loft"
    When I update the apartment with name "Cozy Loft" to name "Super Cozy Loft"
    Then The response code is 204
    And The apartment with name "Cozy Loft" has updated the name to "Super Cozy Loft"