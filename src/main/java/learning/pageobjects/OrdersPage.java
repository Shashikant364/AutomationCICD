package learning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learning.AbstractComponent.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
public OrdersPage(WebDriver driver)
{
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
}
	public Boolean VerifyOrdersDisplay(String productName)
	{
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
