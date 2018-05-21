Scenario:  Tab on helpdesk page works as expected when clicked

Given user is on helpdesk page
When user clicks the Tags tab
Then the tab with text Tags should be selected

Scenario:  Tab on helpdesk page works as expected when unselected

Given user is on helpdesk page
When user clicks the More tab
Then the tab with text Tags should not be selected

