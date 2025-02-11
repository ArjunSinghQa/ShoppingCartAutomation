package Selenium.MavenProject1;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	
	   WebDriver driver;
		
	   public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	   }

	
		@FindBy(css=".totalRow button")
		WebElement checkOutEle;
		
		@FindBy(css="tr td:nth-child(3)")
		private List<WebElement> productList;
		
		public boolean VerifyOrderDisplay(String productName)
		{
			Boolean match = productList.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		    return match;
		}
				
}
