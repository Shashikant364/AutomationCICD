package learning.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import learning.TestComponents.BaseTest;
import learning.pageobjects.CartPage;
import learning.pageobjects.CheckoutPage;
import learning.pageobjects.ConfirmationPage;
import learning.pageobjects.LandingPage;
import learning.pageobjects.ProductCatalogue;

public class StepDefinitionImp extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^I logged in with username (.+) and password (.+)$")
	public void I_logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	
	@When("^I add product (.+) to cart$")
	public void I_add_prduct_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and Submit order$")
	public void Checkout_product_and_Submit_order(String productName)
	{
		CartPage cartPage = productCatalogue.gotToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToChekout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	    driver.close();
	    
	}
	
	@Then("{string} message is displayed")
	public void message_displayed(String string)
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}

}
