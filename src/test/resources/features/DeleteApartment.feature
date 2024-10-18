Feature: Delete Apartment
  In order to manage apartments
  As an Owner
  I want to delete apartments

  Scenario: Delete an apartment as Owner
    Given There is an apartment registered with the name "Cozy Loft"
    And I login as "owner" with password "password"
    When I delete the apartment with name "Cozy Loft"
    Then The response code is 201
    And The apartment with name "Cozy Loft" no longer exists