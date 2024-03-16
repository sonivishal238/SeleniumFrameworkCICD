package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.LandingPage;
import pageobjects.ProductCatalogue;
import testcomponents.BaseTest;

// Contains test to demonstrate cucumber
public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	CartPage cartPage;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on the Ecommerce Page")
	public void I_landed_on_the_Ecommerce_page() throws IOException {
		
		landingPage = LaunchApplication();
	}
	
	// Pay attention to the regex that we are using, starting from ^ and ending with $, and place holders are (.+)
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String userName, String password) {
		
		productCatalogue = landingPage.loginApplication(userName, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		
		cartPage = productCatalogue.goToCartPage();
		// cart page
		Assert.assertTrue(cartPage.isProductDisplayed(productName));
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();

		// Checkout page
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page.")
	public void message_is_displayed_on_confirmation_page(String message) {
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmationMessage, message);
		
		driver.quit();
	}
	
	@But("{string} message is displayed")
	public void error_message_is_displayed(String message) {
		Assert.assertEquals("Incorrect email or password.", landingPage.getInvalidCredentialsErrorMessage());
		driver.quit();
	}
}
