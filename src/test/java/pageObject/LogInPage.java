package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage {

	public  LogInPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_Email;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_Pswd;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;

	public void enterEmail(String email) {
		txt_Email.sendKeys(email);
	}

	public void enterPswd(String pswd) {
		txt_Pswd.sendKeys(pswd);
	}

	public void clickButton() {
		btnLogin.click();
	}

}
