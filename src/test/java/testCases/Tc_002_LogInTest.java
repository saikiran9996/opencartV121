package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LogInPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class Tc_002_LogInTest extends BaseClass {

	@Test(groups= {"Sanity","Master"})
	public void verify_Login() {
		try {

			logger.info("******* starting Tc_002_LogInTest *****");

			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickAccount();
			hp.clickLogin();

			// LogInPage
			
			logger.info("login page in voked");
			
			LogInPage lp = new LogInPage(driver);
			lp.enterEmail(p.getProperty("email"));
			lp.enterPswd(p.getProperty("password"));
			lp.clickButton();

			// MyAccountPage
			MyAccountPage map = new MyAccountPage(driver);

			boolean target = map.isMyAccountExsist();

			logger.info("Target value for account existence: " + target);

			// Assert if target is true
			Assert.assertTrue(target, "Login failed: MyAccount element not found or login unsuccessful!");

			map.linkLogOut();

		} catch (Exception e) {
			logger.error("Exception during login test: ", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}

		logger.info("****** Finished Tc_002_LogInTest ********");
	}
}