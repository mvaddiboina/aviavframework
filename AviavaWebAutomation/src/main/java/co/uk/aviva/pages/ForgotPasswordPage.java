package co.uk.aviva.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage {
	private WebDriver driver;

	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "Username")
	WebElement txtFieldUsername;

	@FindBy(id = "ResetPassword")
	WebElement buttonSend;

	public void enterUsername(String username) {
		txtFieldUsername.sendKeys(username);
	}

	public void clickkOnSend() {
		buttonSend.click();
	}
}
