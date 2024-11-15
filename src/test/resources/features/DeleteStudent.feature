Feature: Delete Student
  In order to make changes on a student
  As a student
  I want to delete students

  Background:
    Given There is a registered student with username "student" and password "password" and email "student@student.app" and phoneNumber "123123123" and name "Student"

  Scenario: Delete a student without being logged in
    Given I'm not logged in
    When I delete a student with an email "student@student.app"
    Then The response code is 401
    And The error message is "Unauthorized"
    And There is 1 Student created with username "student" and email "student@student.app" and phoneNumber "123123123" and name "Student"


  Scenario: Delete a student as a student with a valid email:
    Given I login as "student" with password "password"
    When I delete a student with an email "student@student.app"
    Then The response code is 204
    And There is 0 Student created
