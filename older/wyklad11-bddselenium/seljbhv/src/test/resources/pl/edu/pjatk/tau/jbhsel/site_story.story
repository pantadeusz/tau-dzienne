Scenario:  Tab on helpdesk page works as expected when clicked

Given user is on helpdesk page
When user clicks the Tags tab
Then the tab with text Tags should be selected

Scenario:  Tab on helpdesk page works as expected when unselected

Given user is on helpdesk page
When user clicks the More tab
Then the tab with text Tags should not be selected


Scenario: User is searching articles

Given user is on helpdesk page
When user types wifi in search field
Then the article Instrukcje is displayed
Then the article Inny is not displayed

Scenario: User is searching articles that are not present

Given user is on helpdesk page
When user types asfasdfas in search field
Then the article Instrukcje is not displayed
