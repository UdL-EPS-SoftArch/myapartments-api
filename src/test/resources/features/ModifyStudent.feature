Feature: Modify Student

  In order to make changes on a student
  As a student
  I want to modify a student

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@student.app" and phoneNumber "123123123" and name "Student"

  Scenario: Modify a Student when not logged in
    Given I'm not logged in
    When I modify student "student@student.app" with username "student" and password "password" and email "student@student.app" and phoneNumber "123123123" and name "Student"
    Then The response code is 401
    And The error message is "Unauthorized"
    And There is 1 Student created with username "student" and email "student@student.app" and phoneNumber "123123123" and name "Student"


  Scenario: Modify a Student with valid data
    Given I login as "student" with password "password"
    When I modify student "student@student.app" with username "student" and password "passwordModified" and email "studentModified@student.app" and phoneNumber "111222333" and name "StudentModified"
    Then The response code is 204
    And There is 1 Student created with username "student" and email "studentModified@student.app" and phoneNumber "111222333" and name "StudentModified"


  Scenario: Modify a student with a blank email:
    Given I login as "student" with password "password"
    When I modify student "student@student.app" with username "student" and password "password" and email "" and phoneNumber "123123123" and name "Student"
    Then The response code is 400
    And There is 1 Student created with username "student" and email "student@student.app" and phoneNumber "123123123" and name "Student"

  Scenario: Modify a student with a blank password:
    Given I login as "student" with password "password"
    When I modify student "student@student.app" with username "student" and password "" and email "student@student.app" and phoneNumber "123123123" and name "Student"
    Then The response code is 400
    And There is 1 Student created with username "student" and email "student@student.app" and phoneNumber "123123123" and name "Student"

  Scenario: Modify a student with an empty phoneNumber:
    Given I login as "student" with password "password"
    When I modify student "student@student.app" with username "student" and password "passwordModified" and email "studentModified@student.app" and phoneNumber "" and name "StudentModified"
    Then The response code is 400
    And There is 1 Student created with username "student" and email "student@student.app" and phoneNumber "123123123" and name "Student"

  Scenario: Modify a student with an empty name:
    Given I login as "student" with password "password"
    When I modify student "student@student.app" with username "student" and password "password" and email "student@student.app" and phoneNumber "123123123" and name ""
    Then The response code is 400
    And There is 1 Student created with username "student" and email "student@student.app" and phoneNumber "123123123" and name "Student"

  Scenario: Modify a student with a null email:
    Given I login as "student" with password "password"
    When I modify student "student@student.app" with username "student" and password "passwordModified" and email "null" and phoneNumber "111222333" and name "StudentModified"
    Then The response code is 400
    And There is 1 Student created with username "student" and email "student@student.app" and phoneNumber "123123123" and name "Student"

  Scenario: Modify a student with a null password:
    Given I login as "student" with password "password"
    When I modify student "student@student.app" with username "student" and password "null" and email "student@student.app" and phoneNumber "123123123" and name "Student"
    Then The response code is 400
    And There is 1 Student created with username "student" and email "student@student.app" and phoneNumber "123123123" and name "Student"

  Scenario: Modify a student with an null phoneNumber:
    Given I login as "student" with password "password"
    When I modify student "student@student.app" with username "student" and password "password" and email "student@student.app" and phoneNumber "null" and name "Student"
    Then The response code is 400
    And There is 1 Student created with username "student" and email "student@student.app" and phoneNumber "123123123" and name "Student"

  Scenario: Modify a student with an null name:
    Given I login as "student" with password "password"
    When I modify student "student@student.app" with username "student" and password "password" and email "student@student.app" and phoneNumber "123123123" and name "null"
    Then The response code is 400
    And There is 1 Student created with username "student" and email "student@student.app" and phoneNumber "123123123" and name "Student"
