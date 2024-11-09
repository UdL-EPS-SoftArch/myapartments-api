Feature: Create Student
  In order to look for properties as an student
  I want to create an student profile


  Scenario: Create a Student with valid data
    Given I'm not logged in
    When I create a student with username "student" and password "password" and email "student@student.app" and phoneNumber "123123123" and name "Student"
    Then The response code is 201
    And There is 1 Student created with username "student" and email "student@student.app" and phoneNumber "123123123" and name "Student"


  Scenario: Create a student with a blank email:
    Given I'm not logged in
    When I create a student with username "student" and password "password" and email "" and phoneNumber "123123123" and name "Student"
    Then The response code is 400
    And There is 0 Student created

  Scenario: Create a student with a blank password:
    Given I'm not logged in
    When I create a student with username "student" and password "" and email "student@student.app" and phoneNumber "123123123" and name "Student"
    Then The response code is 400
    And There is 0 Student created

  Scenario: Create a student with an empty phoneNumber:
    Given I'm not logged in
    When I create a student with username "student" and password "password" and email "student@student.app" and phoneNumber "" and name "Student"
    Then The response code is 400
    And There is 0 Student created

  Scenario: Create a student with an empty name:
    Given I'm not logged in
    When I create a student with username "student" and password "password" and email "student@student.app" and phoneNumber "123123123" and name ""
    Then The response code is 400
    And There is 0 Student created

  Scenario: Create a student with an empty username:
    Given I'm not logged in
    When I create a student with username "" and password "password" and email "student@student.app" and phoneNumber "123123123" and name ""
    Then The response code is 400
    And There is 0 Student created

  Scenario: Create a student with a null email:
    Given I'm not logged in
    When I create a student with username "student" and password "password" and email "null" and phoneNumber "123123123" and name "Student"
    Then The response code is 400
    And There is 0 Student created

  Scenario: Create a student with a null password:
    Given I'm not logged in
    When I create a student with username "student" and password "null" and email "student@student.app" and phoneNumber "123123123" and name "Student"
    Then The response code is 400
    And There is 0 Student created

  Scenario: Create a student with an null phoneNumber:
    Given I'm not logged in
    When I create a student with username "student" and password "password" and email "student@student.app" and phoneNumber "null" and name "Student"
    Then The response code is 400
    And There is 0 Student created

  Scenario: Create a student with an null name:
    Given I'm not logged in
    When I create a student with username "student" and password "password" and email "student@student.app" and phoneNumber "123123123" and name "null"
    Then The response code is 400
    And There is 0 Student created


  Scenario: Create a student with a null username:
    Given I'm not logged in
    When I create a student with username "null" and password "password" and email "student@student.app" and phoneNumber "123123123" and name ""
    Then The response code is 400
    And There is 0 Student created