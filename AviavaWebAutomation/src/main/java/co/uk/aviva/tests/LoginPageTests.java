package co.uk.aviva.tests;

import java.io.IOException;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

import co.uk.aviva.base.BaseTest;
import co.uk.aviva.pages.ForgotUserNamePage;
import co.uk.aviva.pages.HomePage;
import co.uk.aviva.pages.LoginPage;
import co.uk.aviva.utils.ExcelUtility;
import jxl.read.biff.BiffException;

public class LoginPageTests extends BaseTest {

	@Test
	public void veirfyLogin() throws BiffException, IOException {
		test = extent.createTest("veirfyLogin", "verify login");
		Map<String, String> loginData = ExcelUtility.getTestData("verifyLoginTest");
		HomePage homePage = new HomePage(driver);
		homePage.navigateToLoginPage();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enteruserName(loginData.get("USERNAME"));
		loginPage.enterPassword(loginData.get("PASSWORD"));
		loginPage.clickLogin();

		String actualMessage = loginPage.getAccountLockMessage().trim();
		boolean actualValue = actualMessage.contains("Your account has been locked.");
		Assert.assertEquals(actualValue, true, "Account lock message verification failed");
	}

	@Test
	public void verifyForgotusername() throws BiffException, IOException {
		test = extent.createTest("verifyForgotusername", "verifyForgotusername");
		Map<String, String> testData = ExcelUtility.getTestData("verifyForgotUserName");
		HomePage homePage = new HomePage(driver);
		homePage.navigateToLoginPage();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToForgotUsernamePage();
		ForgotUserNamePage forgotUserNamePage = new ForgotUserNamePage(driver);
		forgotUserNamePage.enterEmailAddress(testData.get("EMAIL_ADDRESS"));
		forgotUserNamePage.clickContinue();
	}
}
