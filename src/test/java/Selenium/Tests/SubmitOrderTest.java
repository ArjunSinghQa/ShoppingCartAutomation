package Selenium.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	public void submitOrder(HashMap<String, String> input) throws Exception
	{
		ProductCatalogue pc = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = pc.getProductlist();
		pc.productAddToCart(input.get("productname"));
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.VerifyProductDisplay(input.get("productname"));
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
	public Object[][] getdata() throws IOException
	{
		List<HashMap<String,String>> data = getJsonToMap(System.getProperty("user.dir")+"//src//test//java//Selenium//Data//PurchaseOrder.Json") ;
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * HashMap<String, String> map = new HashMap<String, String>();
	 *  map.put("email",
	 *"arjunsingh308569@gmail.com"); map.put("password", "Bangari@308569");
	 * map.put("productname","IPHONE 13 PRO");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("email", "arjunsih308569@gmail.com"); map1.put("password",
	 * "Bangari@30569"); map1.put("productname","IPHONE 13 PRO");
	 */
	
	
	
	/*
	 * @DataProvider public Object[][] getdata() {
	 * 
	 * return new Object[][]
	 * {{"arjunsingh308569@gmail.com","Bangari@308569","IPHONE 13 PRO"},{
	 * "arjunsih308569@gmail.com","Bangari@30569","IPHONE 13 PRO"}}; }
	 */
	
	
	

}
