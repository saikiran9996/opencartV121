package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	// constructor

	public HomePage(WebDriver driver) {

		super(driver);

	}

	// locators

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;

	@FindBy(linkText = "Login")
	WebElement lnkLogin;

	// Actions

	public void clickAccount() {
		
		 
		lnkMyAccount.click();
	}

	public void clickRegistration() {
		lnkRegister.click();
	}

	public void clickLogin() {
		lnkLogin.click();
	}

}
