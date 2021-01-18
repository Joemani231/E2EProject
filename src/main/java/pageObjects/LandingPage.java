package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	public WebDriver driver;
	
	
	By signin = By.cssSelector("a[href*= 'sign_in']");
	By title = By.xpath("//*[@id=\"content\"]/div/div/h2");
	By navigation = By.xpath("//*[@id=\"homepage\"]/header/div[2]/div/nav/ul");
	public LandingPage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public LoginPage getlogin() {
		driver.findElement(signin).click();
		return new LoginPage(driver);
	}
	
	public WebElement getTile() {
		return driver.findElement(title);
	}
	
	public WebElement getNavigation() {
		return driver.findElement(navigation);
	}

}
