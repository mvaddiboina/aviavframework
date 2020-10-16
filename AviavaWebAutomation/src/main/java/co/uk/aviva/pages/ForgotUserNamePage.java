package co.uk.aviva.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotUserNamePage {
	private WebDriver driver;

	public ForgotUserNamePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "EmailAddress")
	WebElement txtFieldEmailAddress;

	@FindBy(name = "SubmitButton")
	WebElement buttonContinue;

	public void enterEmailAddress(String emailAddress) {
		txtFieldEmailAddress.sendKeys(emailAddress);
	}

	public void clickContinue() {
		buttonContinue.click();
	}

}
