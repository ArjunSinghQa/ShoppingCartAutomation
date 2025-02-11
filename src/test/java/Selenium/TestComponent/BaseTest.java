package Selenium.TestComponent;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Selenium.MavenProject1.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage ;
	
	
	public WebDriver initializeDriver() throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Selenium\\MavenProject1\\rResources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
        
		if(browserName.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
	    }

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication () throws Exception 
	{
	    initializeDriver();
	    landingpage = new LandingPage(driver);
	    landingpage.goTo();
		return landingpage;
		
	}
	//We have always run = true for these two methods as testng takes only group headed methods while execution.But if there are prerequisites in running a message
	// then it will fail. So always run =true is required for such methods.
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.close();
	}
	
}
