package pl.edu.pjatk.tau;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class CalculatorSteps {
    Calculator calculator;

    @Given("prepare calculator")
    public void givenPrepareCalculator() {
        calculator = new Calculator();
    }
    
    @Given("we have two numbers $x and $y")
    public void givenWeHaveTwoNumbersXandY(double x, double y) {
        calculator.pushNumber(x);
        calculator.pushNumber(y);
    }
    
    @When("we add them")
    public void whenWeAddThem() {
        calculator.add();
    }
    
    @Then("the result is $result")
    public void thenTheResultIs4(double result) {
        assertEquals(result, calculator.popResult(),0.001);
    }
}
