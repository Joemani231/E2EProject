package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public WebDriver driver;
	
	
	By email = By.cssSelector("input[id = 'user_email']");
	By password = By.cssSelector("input[id = 'user_password']");
	By go = By.name("commit");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public WebElement email() {
		return driver.findElement(email);
	}
	
	public WebElement password() {
		return driver.findElement(password);
	}
	
	public WebElement go() {
		return driver.findElement(go);
	}
	

}
