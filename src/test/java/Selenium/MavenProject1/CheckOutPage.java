package Selenium.MavenProject1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
	@FindBy(xpath="//section/button[@class=\"ta-item list-group-item ng-star-inserted\"][2]")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String Countryname)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, "india").build().perform();
		waitForelementToappear(results);
		selectCountry.click();
	}
	
	
	public ConfirmationPage submitOrder()
	{
		submitButton.click();
		return new ConfirmationPage(driver);
	}
	
	
	

}
