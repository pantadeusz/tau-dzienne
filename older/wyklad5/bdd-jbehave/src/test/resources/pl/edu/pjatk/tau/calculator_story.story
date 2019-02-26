Narrative:
In order to perform calculations
As a clerc
I want to accelerate my work

Lifecycle:
Before: 
Scope: STORY
Given prepare calculator


Scenario: I have calculator and I would like to know if it calcuates

Given we have two numbers 2 and 5
When we add them
Then the result is 7


Scenario: Some different calculations

Given we have two numbers <a> and <b>
When we add them
Then the result is <result>

Examples:
a|b|result
2|2|4
3|4|7


Scenario: Substraction

Given we have two numbers <a> and <b>
When we substract them
Then the result is <result>

Examples:
a|b|result
2|2|0
3|4|-1
