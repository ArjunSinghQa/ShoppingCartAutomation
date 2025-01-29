package Selenium.MavenProject1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("arjunsingh308569@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Bangari@308569");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		 
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
	    WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).
	    getText().equals(productname)).findFirst().orElse(null);
	    prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated((By.cssSelector(".ng-animating"))));
	    
	    driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
	    
	    List<WebElement>  cartproducts =  driver.findElements(By.cssSelector(".cartSection h3"));
	    
	    Boolean match = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
	    
	    Assert.assertTrue(match);
	    
	    driver.findElement(By.cssSelector(".totalRow button")).click();
	    
	    //hello
	    
		
		
	
		
		
		
		
		
		
		
		
		
		
	}
	
	

}
