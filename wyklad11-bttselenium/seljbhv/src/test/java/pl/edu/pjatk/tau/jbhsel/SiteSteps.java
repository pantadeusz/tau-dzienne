package pl.edu.pjatk.tau.jbhsel;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SiteSteps {

    private final Pages pages;

    public SiteSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on helpdesk page")
    public void userOnHelpdeskPage(){

        pages.helpdesk().open();
    }

    @When("user clicks the $linkText tab")
    public void userClicksTabLink(String linkText) {

        pages.helpdesk().click(linkText);
    }

    @Then("the tab with text $linkText should have class $classInside")
    public void tabWithTextAndClass(String linkText, String classInside) {
        assertTrue( pages.helpdesk().getClassesForLink(linkText).contains(classInside));
    }

    @Then("the tab with text $linkText should not have class $classInside")
    public void tabWithTextAndNotClass(String linkText, String classInside) {
        assertTrue( !pages.helpdesk().getClassesForLink(linkText).contains(classInside));
    }
}
