package Selenium.MavenProject1;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	
	public static void main(String args[])
	{
		String productname = "IPHONE 13 PRO";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		
		LandingPage lp = new LandingPage(driver);
		lp.goTo();
		
		ProductCatalogue pc = lp.loginApplication("arjunsingh308569@gmail.com", "Bangari@308569");
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
		
		driver.close();
		

	}
	
	

}
