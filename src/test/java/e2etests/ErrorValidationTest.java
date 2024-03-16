package e2etests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.ProductCatalogue;
import testcomponents.BaseTest;
import testcomponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void incorrectCredentialsLoginTest() throws IOException {
		
		landingPage.loginApplication("invalid@test.com", "invalid");
		Assert.assertEquals("Incorrect email or password.", landingPage.getInvalidCredentialsErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException {

		String zaraCoatName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("vishaltest@test.com", "Test@123");

		// product catalog page
		// List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(zaraCoatName);
		CartPage cartPage = productCatalogue.goToCartPage();

		// cart page
		Assert.assertTrue(cartPage.isProductDisplayed(zaraCoatName));
	}
}
