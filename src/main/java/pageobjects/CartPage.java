package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutElement;
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> productTitles;
	
	public Boolean isProductDisplayed(String productName) {
		Boolean isMatch = productTitles.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return isMatch;
	}
	
	public CheckoutPage goToCheckoutPage() {
		checkoutElement.click();
		return new CheckoutPage(driver);
	}
}
