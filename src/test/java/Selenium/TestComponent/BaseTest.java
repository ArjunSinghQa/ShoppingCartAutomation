package Selenium.TestComponent;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Selenium.MavenProject1.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	
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
	
	public LandingPage launchApplication () throws Exception 
	{
	    initializeDriver();
	    LandingPage lp = new LandingPage(driver);
		lp.goTo();
		return lp;
		
	}
}
