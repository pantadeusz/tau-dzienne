package pl.edu.pjatk.tau.jbhsel.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by tp on 03.04.17.
 */
public class HelpDesk extends WebDriverPage {

    public HelpDesk(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://szuflandia.pjwstk.edu.pl/helpdesk.html");
        manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    public void click(String linkText) {
        WebElement e = findElement(By.partialLinkText(linkText));
        e.click();
    }

    public String getClassesForLink(String linkText) {
        WebElement e = findElement(By.partialLinkText(linkText));
        return e.getAttribute("class");
    }

    public boolean isTabSelected(String tabText) {
        WebElement e = findElement(By.partialLinkText(tabText));
        return e.getAttribute("class").contains("tabSelected");
    }

    public void setSearchText(String text) {
        WebElement searchInput = findElement(By.cssSelector(
            "#header > div.headerShadow > span.searchBar > input"));
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.RETURN);
    }

    public boolean isArticlePresent(String articleTitle) {
        try {
            List<WebElement> elements = findElements(By.cssSelector("#tiddlerInstrukcje > div.title"));
            for (WebElement e : elements) {
                if  (e.getText().toLowerCase().contains(articleTitle.toLowerCase())) return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
