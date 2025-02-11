package Selenium.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Selenium.MavenProject1.CartPage;
import Selenium.MavenProject1.CheckOutPage;
import Selenium.MavenProject1.ConfirmationPage;
import Selenium.MavenProject1.OrderPage;
import Selenium.MavenProject1.ProductCatalogue;
import Selenium.TestComponent.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	
	String productname = "IPHONE 13 PRO";
	@Test
	public void submitOrder() throws Exception
	{
		
		ProductCatalogue pc = landingpage.loginApplication("arjunsingh308569@gmail.com", "Bangari@308569");
		List<WebElement> products = pc.getProductlist();
		pc.productAddToCart(productname);
		
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.VerifyProductDisplay(productname);
		Assert.assertTrue(match);
		
		CheckOutPage cop = cp.goToCheckout();
		cop.selectCountry("india");
		ConfirmationPage confirmationPage = cop.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue pc = landingpage.loginApplication("arjunsingh308569@gmail.com", "Bangari@308569");
		OrderPage op = pc.goToOrderPage();
		Assert.assertTrue(op.VerifyOrderDisplay(productname));
		
	}
	
	

}
