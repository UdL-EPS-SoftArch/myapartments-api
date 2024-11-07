# Created by abdee at 31/10/24
# Created by abdee at 17/10/24
Feature: Delete Visit
  As a user
  I want to delete a visit to an advertisement
  So that I can cancel the visit request

Scenario: Delete visit request
  Given There is an advertisement with title "Cozy Loft" and address "Carrer de les Flors 10"
  And  I login as "demo" with password "password"
  And  I request a visit to the advertisement with title "Cozy Loft"
  When I delete the visit request to the advertisement with title "Cozy Loft"
  Then The visit is successfully deleted