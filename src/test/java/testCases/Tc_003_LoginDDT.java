package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LogInPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class Tc_003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class , groups="DataDriven")
	public void verify_DDT(String email, String pswd, String res) {

		logger.info("******* starting Tc_002_LogInTestDDT *****");
		try {
			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickAccount();
			hp.clickLogin();
			
			Thread.sleep(1000);

			// LogInpage
			LogInPage lp = new LogInPage(driver);
			lp.enterEmail(email);
			lp.enterPswd(pswd);
			lp.clickButton();

			// MyaccountPage
			MyAccountPage map = new MyAccountPage(driver);
			boolean target = map.isMyAccountExsist();

			// data is valid - login sa- test pass - logout
			// login failed - test failed
			// data is invalid - login sa- test fail - logout
			// login failed - test failed
			if (res.equalsIgnoreCase("valid")) {
				if (target == true) {
					map.linkLogOut();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (res.equalsIgnoreCase("invalid")) {
				if (target == true) {
					map.linkLogOut();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("******* Finisd Tc_002_LogInTestDDT *****");
	}

}
