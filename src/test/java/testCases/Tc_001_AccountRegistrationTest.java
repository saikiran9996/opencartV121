package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class Tc_001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression","Master"})

	public void verify_account_registration() {

		logger.info("*******---Started Tc_01_verify_account_registration---*******");

		try {

			HomePage hp = new HomePage(driver);
			hp.clickAccount();

			logger.info("--clicked on Account ----");

			hp.clickRegistration();
			logger.info("---clicked on registration----");

			AccountRegistrationPage rp = new AccountRegistrationPage(driver);

			logger.info("--provide customer info----");

			rp.setFirstName(randomString().toUpperCase());
			rp.setLastName(randomString().toUpperCase());
			rp.setEmail(randomString() + "@gmail.com");
			rp.setTelephone(randomNumber());

			String password = randomAlphaNum();

			rp.setPassword(password);
			rp.setConfirmpassword(password);
			rp.setPolicy();
			rp.clickContinue();

			logger.info("-- validating msg  condition---");

			String msg = rp.getconfirmationMsg(); // Declare 'msg' only once
			logger.info("Received confirmation message: " + msg);

			Assert.assertEquals(msg, "Your Account Has Been Created!", "Account creation failed. Message: " + msg);

			rp.clkButton();
		} 
		catch (Exception e) 
		{
			logger.error("Exception during account registration: ", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		logger.info("*******---Finished Tc_01_verify_account_registration---*******");

	}
}
