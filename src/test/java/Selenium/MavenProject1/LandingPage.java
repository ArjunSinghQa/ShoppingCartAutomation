package Selenium.MavenProject1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	// first of all this page's driver does not know about the driver of main class
	// we can fetch the driver from main class as this is the first thing that needs to be done
	// for this, we have constructor as constructor is the first thing that gets executed on running a class code
	// in this constructor we can receive the driver from the main class
	// in main class we will create the object of this class and in the constructor call we will pass the main page driver
	// and here in landing page class we will receive the driver and attach it to the driver of this class
	// through this.driver = driver ;
	
	
    WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver); // for sending driver from child class to parent class we use super keyword
		this.driver = driver ;
		PageFactory.initElements(driver,this);
	}
	
	
	//WebElement useremail = driver.findElement(By.id("userEmail"));
	//WebElement userpassword =  driver.findElement(By.id("userPassword"));
	
	
	// one question arises how does the above code finds the driver
	// so for that we have constructor which have the line "PageFactory.initElements(driver,this);"
	// which helps in initializing the element with the local driver which in turn is attached to the main driver of other class
	// this line constructs the full line of code for webElement
	
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	
	@FindBy(id="userPassword")
	WebElement userpassword;
	
	
	@FindBy(id="login")
	WebElement submit;
	
	//.ng-tns-c4-3.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalogue loginApplication(String Email, String userPassword) //actions method for webElements
	{
		userEmail.sendKeys(Email);
		userpassword.sendKeys(userPassword);
		submit.click();
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc ;
	}
	
	public String getErrorMessage()
	{
		waitForelementToappear(errorMessage);
		 return errorMessage.getText();
	}
	
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	
	
	
	
}
