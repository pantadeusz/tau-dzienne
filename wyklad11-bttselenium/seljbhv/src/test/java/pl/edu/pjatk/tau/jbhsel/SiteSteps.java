package pl.edu.pjatk.tau.jbhsel;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SiteSteps {

    private final Pages pages;

    public SiteSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on helpdesk page")
    public void userOnHelpdeskPage() {

        pages.helpdesk().open();
    }

    @When("user clicks the $linkText tab")
    public void userClicksTabLink(String linkText) {

        pages.helpdesk().click(linkText);
    }

    @Then("the tab with text $text should be selected")
    public void thenTheTabWithTextTagsShouldBeSelected(String text) {
        assertTrue(pages.helpdesk().isTabSelected(text));
    }

    @Then("the tab with text $text should not be selected")
    public void thenTheTabWithTextTagsShouldNotBeSelected(String text) {
        assertFalse(pages.helpdesk().isTabSelected(text));
    }
}
