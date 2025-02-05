package Selenium.MavenProject1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	
	   WebDriver driver;
		
	   public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	   }

	
		@FindBy(css=".totalRow button")
		WebElement checkOutEle;
		
		@FindBy(css=".cartSection h3")
		private List<WebElement> productTitles;
		
		public boolean VerifyProductDisplay(String productName)
		{
			Boolean match = productTitles.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		    return match;
		}
		
		
		public WebElement getProductByName(String productName)
		{
			WebElement prod = productTitles.stream().filter(product->product.findElement(By.cssSelector("b")).
				    getText().equals(productName)).findFirst().orElse(null);
			return prod;
		}
		
		public  CheckOutPage goToCheckout()
		{
			checkOutEle.click();
			return new CheckOutPage(driver);
		}
		

}
