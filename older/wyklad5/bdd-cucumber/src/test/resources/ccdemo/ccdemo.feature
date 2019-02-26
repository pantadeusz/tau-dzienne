Feature: AwesomeMul
  The demo shows how to test mutliplication app
  and how to write BDD tests in
  Cucumber

  Scenario Outline: The multiplication of two numbers
    Given there is a multiplication object
    When numbers <a> and <b> are multiplied
    Then the result should be <result>
    Examples:
      |  a |  b | result |
      |  5 |  5 |     25 |
      |  0 |  2 |      0 |
      | -3 | -3 |      9 |
