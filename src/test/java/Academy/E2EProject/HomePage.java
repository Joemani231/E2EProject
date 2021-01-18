package Academy.E2EProject;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class HomePage extends base{
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	@BeforeClass
	public void initialize() throws IOException {
		driver = initializeBrowser();
		log.info("Driver is initialized");
	}
	@Test(dataProvider="getData")
	public void basePageNavigation(String username, String Password) throws IOException {
		driver.get(prop.getProperty("URL"));
		log.info("Navigated to home page");
		LandingPage lp = new LandingPage(driver);
		LoginPage login = lp.getlogin();
		log.info("Navigated to login page");
		//LoginPage log = new LoginPage(driver);
		login.email().sendKeys(username);
		login.password().sendKeys(Password);
		
		//System.out.println(text);
		login.go().click();
		
		
		
	}
	
	@AfterClass
	public void end() {
	driver.close();
	}
	
	@DataProvider
	public Object[][] getData() {
		
		Object[][] obj = new Object[2][2];
				
		obj[0][0] = "restricteduser@qa.com";
		obj[0][1] = "1234";
		//obj[0][2] = "Restricted User";
		
		obj[1][0] = "nonresuser@qa.com";
		obj[1][1] = "567";
		//obj[1][2] = "Non restricted user";
		
		return obj;
		
	}
	
}
