# Here we are going to be typing in a language called GHERKIN
# This language allows us to define requirements by examples
# In addition, those examples are going to be our automated tests

Feature: User REST API

  Background:
    Given a user
    And the user's "name" is "Ivan"
    And the user's "email" is "ivan@gmail.com"
    And the user's "phone" is "12345678"
    And the user is stored in the repository
    Given a user
    And the user's "name" is "Mohamed"
    And the user's "email" is "mohamed@gmail.com"
    And the user's "phone" is "98765432"
    And the user is stored in the repository

  Scenario Outline: Get users information
    When the service "/api/v1/users" is call
    And method "GET" is used
    Then the response return code must be "OK"
    And a JSON array must be returned
    And the JSON array must contain 2 elements
    And the element at position <position> must have a field called "name" with value "<name>"
    And the element at position <position> must have a field called "phone" with value "<phone>"
    And the element at position <position> must have a field called "email" with value "<email>"

    Examples:
      | position |  name      | phone     | email               |
      | 0        |  Ivan      | 12345678  | ivan@gmail.com      |
      | 1        |  Mohamed   | 98765432  | mohamed@gmail.com   |

  Scenario: Create a user
    Given a JSON object
    And the JSON Object contains a field "name" with value "UncleBob"
    And the JSON Object contains a field "phone" with value "111111111"
    And the JSON Object contains a field "email" with value "uncle@bob.com"
    When the service "/api/v1/users" is call
    And method "POST" is used
    Then the response return code must be "CREATED"
    And a JSON Object must be returned
    And the JSON Object must contain a field "name" with value "Uncle Bob"
    And the JSON Object must contain a field "phone" with value "111111111"
    And the JSON Object must contain a field "email" with value "uncle@bob.com"

  Scenario: Get a user
    When the service "/api/v1/users/UncleBob" is call
    And method "GET" is used
    Then the response return code must be "OK"
    And a JSON Object must be returned
    And the JSON Object must contain a field "name" with value "Uncle Bob"
    And the JSON Object must contain a field "phone" with value "111111111"
    And the JSON Object must contain a field "email" with value "uncle@bob.com"

  Scenario: Delete a user
    When the service "/api/v1/users/UncleBob" is call
    And method "DELETE" is used
    Then the response return code must be "OK"
    And a JSON Object must be returned
    And the JSON Object must contain a field "name" with value "Uncle Bob"
    And the JSON Object must contain a field "phone" with value "111111111"
    And the JSON Object must contain a field "email" with value "uncle@bob.com"
