package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public Boolean isOrderDisplayed(String productName) {
		Boolean isMatch = productNames.stream().anyMatch(ordersNames -> ordersNames.getText().equalsIgnoreCase(productName));
		return isMatch;
	}
}
