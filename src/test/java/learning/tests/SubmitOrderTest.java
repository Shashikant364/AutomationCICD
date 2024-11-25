package learning.tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import learning.TestComponents.BaseTest;
import learning.pageobjects.CartPage;
import learning.pageobjects.CheckoutPage;
import learning.pageobjects.ConfirmationPage;
import learning.pageobjects.LandingPage;
import learning.pageobjects.OrdersPage;
import learning.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups={"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	
	{
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.gotToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToChekout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("shantshetty@gmail.com", "Shetty@123");
		OrdersPage ordersPage = productCatalogue.gotToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrdersDisplay(productName));
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//learning//data//PurchaseOrder.json");
		 return new Object[][] {{data.get(0)},{data.get(1)}};
		 
	}
	/*
	 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
	 * "shantshetty@gmail.com"); map.put("password", "Shetty@123");
	 * map.put("product", "ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
	 * "shant@gmail.com"); map1.put("password", "Shetty@123"); map1.put("product",
	 * "ADIDAS ORIGINAL");
	 */

}
