package co.uk.aviva.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, LoginPage.class);
	}

	@FindBy(id = "username")
	WebElement txtFieldUserName;

	@FindBy(id = "password")
	WebElement txtFieldPassword;

	@FindBy(id = "loginButton")
	WebElement buttonLogin;

	@FindBy(xpath = ".//a[text()='Register']")
	WebElement linkRegister;

	@FindBy(xpath = ".//a[text()='Forgot Username']")
	WebElement linkForgotUserName;

	@FindBy(xpath = ".//a[text()='Forgot Password']")
	WebElement linkForgotPassword;
	
	@FindBy(xpath = ".//span[text()='Your account has been locked.']")
	WebElement labelAccountLockMessage;

	public void enteruserName(String userName) {
		txtFieldUserName.sendKeys(userName);
	}

	public void enterPassword(String password) {
		txtFieldPassword.sendKeys(password);
	}

	public void clickLogin() {
		buttonLogin.click();
	}
	
	public String getAccountLockMessage()
	{
		return labelAccountLockMessage.getText();
	}

	public void navigateToForgotUsernamePage() {
		linkForgotUserName.click();
	}

	public void navigateToForgotPasswordPage() {
		linkForgotPassword.click();
	}

	public void navigateToRegisterPage() {
		linkRegister.click();
	}

}
