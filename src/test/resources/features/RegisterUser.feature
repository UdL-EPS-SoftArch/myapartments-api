Feature: Register User
  In order to use the app
  As a user
  I want to register myself and get an account

  Scenario: Register new user
    Given There is no registered user with username "user"
    And I'm not logged in
    When I register a new user with username "user", email "user@sample.app" and password "password"
    Then The response code is 201
    And It has been created a user with username "user" and email "user@sample.app", the password is not returned
    And I can login with username "user" and password "password"

  Scenario: Register existing username
    Given There is a registered user with username "user" and password "existing" and email "user@sample.app"
    And I'm not logged in
    When I register a new user with username "user", email "user@sample.app" and password "newpassword"
    Then The response code is 409
    And I cannot login with username "user" and password "newpassword"

  Scenario: Register user when already authenticated
    Given I login as "demo" with password "password"
    When I register a new user with username "user", email "user@sample.app" and password "password"
    Then The response code is 403
    And It has not been created a user with username "user"

  Scenario: Register user with empty password
    Given I'm not logged in
    When I register a new user with username "user", email "user@sample.app" and password ""
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a user with username "user"

  Scenario: Register user with empty email
    Given I'm not logged in
    When I register a new user with username "user", email "" and password "password"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a user with username "user"

  Scenario: Register user with invalid email
    Given I'm not logged in
    When I register a new user with username "user", email "userasample.app" and password "password"
    Then The response code is 400
    And The error message is "must be a well-formed email address"
    And It has not been created a user with username "user"

  Scenario: Register user with password shorter than 8 characters
    Given I'm not logged in
    When I register a new user with username "user", email "user@sample.app" and password "pass"
    Then The response code is 400
    And The error message is "length must be between 8 and 256"
    And It has not been created a user with username "user"

  Scenario: Register user with an existing email
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    And I'm not logged in
    When I register a new user with username "user2", email "user@sample.app" and password "password2"
    Then The response code is 409
    And I can login with username "user" and password "password"
