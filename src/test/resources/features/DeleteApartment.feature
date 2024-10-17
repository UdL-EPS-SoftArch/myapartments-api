Feature: Delete Apartment
  In order to manage apartments
  As an Owner
  I want to delete apartments

  Scenario: Delete an apartment as Owner
    Given There is a registered owner with username "owner" and password "password" and email "owner@example.com"
    And I login as "owner" with password "password"
    When I delete an apartment with name "Cozy Loft"
    Then The response code is 201
    And The apartment has been deleted with name "Cozy Loft"