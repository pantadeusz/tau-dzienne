package pl.edu.pjatk.tau.jbhsel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

// UWAGA -- przerobilem dla phantomjs -- powinno dzialac na pracowni PJATK
public class SomeSiteTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		caps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"/opt/tp/phantomjs-2.1.1-linux-x86_64/bin/phantomjs"
		);
		driver = new PhantomJSDriver(caps);
	}

	@AfterClass
	public static void cleanp() {
		driver.quit();
	}

	@Test
	public void clickAndSelectTab() throws IOException {
		driver.get("http://szuflandia.pjwstk.edu.pl/helpdesk.html");
		// tab
		WebElement e;
		e = driver.findElement(By.partialLinkText("Tags"));
		assertTrue(!e.getAttribute("class").contains("tabSelected"));
		File screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("bss.1.png"));
		e.click();
		assertTrue(e.getAttribute("class").contains("tabSelected"));
		screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("bss.2.png"));
		assertNotNull(e);
	}
}
