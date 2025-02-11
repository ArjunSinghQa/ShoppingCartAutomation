package Selenium.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium.MavenProject1.CartPage;
import Selenium.MavenProject1.CheckOutPage;
import Selenium.MavenProject1.ConfirmationPage;
import Selenium.MavenProject1.OrderPage;
import Selenium.MavenProject1.ProductCatalogue;
import Selenium.TestComponent.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	
	String productname = "IPHONE 13 PRO";
	@Test(dataProvider="getdata",groups= {"Purchase"})
	public void submitOrder(String username,String password, String productname) throws Exception
	{
		
		ProductCatalogue pc = landingpage.loginApplication(username, password);
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
	
	
	@DataProvider
	public Object[][] getdata()
	{
		return new Object[][] {{"arjunsingh308569@gmail.com","Bangari@308569","IPHONE 13 PRO"},{"arjunsih308569@gmail.com","Bangari@30569","IPHONE 13 PRO"},{"arjh308569@gmail.com","Bangari@38569","IPHONE 13 PRO"}};
	}
	
	

}
