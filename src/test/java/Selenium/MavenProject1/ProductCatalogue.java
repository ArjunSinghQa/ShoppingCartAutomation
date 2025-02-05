package Selenium.MavenProject1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

    WebDriver driver;
    
	public ProductCatalogue(WebDriver driver) {
		
		super(driver); //every child needs to serve the driver to parent
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductlist()
	{
		waitForelementToappear(productsBy);
		return products;
	}
	
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).
			    getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void productAddToCart(String productName)
	{
		WebElement prod =  getProductByName(productName);
		prod.findElement(addToCart).click();
		 waitForelementToappear(toastMessage);
		 waitForelementToDisappear(spinner);
	}
	
	
	
	
	
	
	
	
	
	
	
}
