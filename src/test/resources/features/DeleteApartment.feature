Feature: Delete Apartment
  In order to manage apartments
  As an Owner
  I want to delete apartments

  Scenario: Delete an apartment as Owner
    Given I login as "owner" with password "password"
    And There is an apartment registered with the name "Cozy Loft"
    When I delete the apartment with name "Cozy Loft"
    Then The response code is 204
    And The apartment with name "Cozy Loft" no longer exists