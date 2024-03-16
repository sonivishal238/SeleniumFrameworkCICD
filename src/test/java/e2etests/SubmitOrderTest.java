package e2etests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.OrdersPage;
import pageobjects.ProductCatalogue;
import testcomponents.BaseTest;

// Lets run using CICD
public class SubmitOrderTest extends BaseTest {

	//String zaraCoatName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups = {"Purchase"})
	public void SubmitOrder(HashMap<String, String> input) throws IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("userName"), input.get("password"));
		
		// product catalog page
		// List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		// cart page
		Assert.assertTrue(cartPage.isProductDisplayed(input.get("product")));
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();

		// Checkout page
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		// Confirmation message
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
	}

	// Test to verify ZARA COAT 3 is displaying in the orders page
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest() {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("vishaltest@test.com", "Test@123");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		
		Assert.assertTrue(ordersPage.isOrderDisplayed(productName));
	}
	
	@DataProvider
	public Object getData() throws IOException {
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("userName", "vishaltest@test.com");
//		map1.put("password", "Test@123");
//		map1.put("product", "IPHONE 13 PRO");
//		
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("userName", "vishaltest@test.com");
//		map2.put("password", "Test@123");
//		map2.put("product", "ZARA COAT 3");
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
}
