package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {

		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chdPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement clkContinue;

	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String number) {
		txtTelephone.sendKeys(number);
	}

	public void setPassword(String pswd) {
		txtPassword.sendKeys(pswd);
	}

	public void setConfirmpassword(String confirmpswd) {
		txtConfirmPassword.sendKeys(confirmpswd);
	}

	public void setPolicy() {
		chdPolicy.click();
	}

	public void clickContinue() {
		btnContinue.click();
	}

	public String getconfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

	public void clkButton() {
		clkContinue.click();
	}
}
