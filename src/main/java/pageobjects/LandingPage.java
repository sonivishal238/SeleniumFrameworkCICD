package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ProductCatalogue loginApplication(String userName, String password) {
		userEmail.sendKeys(userName);
		userPassword.sendKeys(password);
		submit.click();
		
		// We are sure that after login we will got to the ProductCatalogue so it can be used here
		return new ProductCatalogue(driver);
	}
	
	// This is called page factory, cool way of finding elements
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getInvalidCredentialsErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
}
