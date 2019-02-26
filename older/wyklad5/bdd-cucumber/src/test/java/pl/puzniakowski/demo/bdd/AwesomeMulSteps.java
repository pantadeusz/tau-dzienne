package pl.puzniakowski.demo.bdd;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AwesomeMulSteps {

    private AwesomeMul awesomeMul;

    // UWAGA: NIE MYLIĆ Z org.junit.Before !!!!
    // JAK NIE JESTEŚ PEWIEN - wrzuć te rzeczy do "Given"
    @Before
    public void setUp() {
        awesomeMul = new AwesomeMul();
    }

    @Given("^there is a multiplication object$")
    public void there_is_a_multiplication_object() throws Throwable {
        assertNotNull(awesomeMul);
    }

    @When("^numbers ([-]*\\d+) and ([-]*\\d+) are multiplied$")
    public void numbers_are_multiplied(double a, double b) throws Throwable {
        awesomeMul.mul(a,b);
    }

    @Then("^the result should be (\\d+)$")
    public void the_result_should_be(double m) throws Throwable {
        assertEquals(m, awesomeMul.getResult(), 0.0001);
    }

}
