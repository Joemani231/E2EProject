package Academy.E2EProject;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LandingPage;

import resources.base;

public class ValidatingNavigationBar extends base{
	
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	@BeforeClass
public void starttest() throws IOException {
		driver = initializeBrowser();
		log.info("Driver initialised.");
		driver.get(prop.getProperty("URL"));
		log.info("Navigated to home page.");
	}
	
	@Test()
	public void basePageNavigation() throws IOException {
		
		LandingPage lp = new LandingPage(driver);
		//Assert.assertEquals(lp.getTile().getAttribute("textContent"), "Featured Courses");
		Assert.assertTrue(lp.getNavigation().isDisplayed());
		log.info("Navigation bar is displayed.");
		
		
	}
	
	@AfterClass
	public void endTest() {
		driver.close();
	}
	
	
}
