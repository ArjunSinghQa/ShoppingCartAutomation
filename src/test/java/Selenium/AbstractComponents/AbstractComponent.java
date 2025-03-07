package Selenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium.MavenProject1.CartPage;
import Selenium.MavenProject1.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(css ="[routerlink*=cart]")
	WebElement cartHeader;
	
	@FindBy(css ="[routerlink*=myorders]")
	WebElement orderHeader;
	
	
	public void waitForelementToappear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForelementToappear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		 wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForelementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cp= new CartPage(driver);
		return cp;
	}
	
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage op= new OrderPage(driver);
		return op;
	}
}
