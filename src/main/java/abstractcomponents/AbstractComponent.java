package abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.CartPage;
import pageobjects.OrdersPage;

// This will be the parent of all of the page object classes
public class AbstractComponent {

	protected WebDriver driver;
	WebDriverWait wait;
	Duration explicitTimeout;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.explicitTimeout = Duration.ofSeconds(10);
	}

	@FindBy(css = "button[routerlink$='cart']")
	WebElement cartHeader;

	@FindBy(css = "button[routerlink$='myorders']")
	WebElement orderHeader;

	public CartPage goToCartPage() {
		cartHeader.click();
		return new CartPage(driver);
	}

	public OrdersPage goToOrdersPage() {
		orderHeader.click();
		return new OrdersPage(driver);
	}

	public void waitForElementToAppear(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToDisappear(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void setExplicitTimeout(int seconds) {
		explicitTimeout = Duration.ofSeconds(seconds);
	}

	public Duration getExplicitTimeout() {
		return explicitTimeout;
	}
	
	public void scrollElementInViewJavaScript(String selector) {
		String script = "document.querySelector("+selector+").scrollIntoView();";
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		exe.executeScript(script);
	}
}
