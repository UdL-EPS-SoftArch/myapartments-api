Feature: Delete Room
  In order to use the app
  As a owner
  I must be able to delete Room

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a apartment with a name "testApartment", floor "0", address "roomAddressX", postal code "postalCodeX", city "cityX", country "countryX", description "roomDescriptionX" and a creation date "2024-10-10"
    Given There is a room with surface "soft", is occuped "true", has window "true", has desk "false" and have bed "true"
    
  Scenario: Delete a Room with owner and room not logged in
    Given I'm not logged in
    When I try to delete Room with user "user" and apartment "testApartment"
    Then The response code is 401
    
  Scenario: Delete a Room with owner and room logged in
    Given I login as "user" with password "password"
    When I try to delete Room with user "user" and apartment "testApartment"
    Then The response code is 200