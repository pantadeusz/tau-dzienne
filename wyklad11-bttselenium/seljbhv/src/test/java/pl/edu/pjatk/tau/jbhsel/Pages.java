package pl.edu.pjatk.tau.jbhsel;

import org.jbehave.web.selenium.WebDriverProvider;
import pl.edu.pjatk.tau.jbhsel.pages.HelpDesk;

/**
 * Created by tp on 03.04.17.
 */
public class Pages {

    private WebDriverProvider driverProvider;

    //Pages -- moze byc ich kilka
    private HelpDesk helpDesk;

    public Pages(WebDriverProvider driverProvider) {
        super();
        this.driverProvider = driverProvider;
    }

    public HelpDesk helpdesk() {
        if (helpDesk == null) {
            helpDesk = new HelpDesk(driverProvider);
        }
        return helpDesk;
    }
}
