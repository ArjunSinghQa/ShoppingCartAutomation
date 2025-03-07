package Selenium.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public List<HashMap<String, String>> getJsonToMap(String FilePath) throws IOException
	{
		// reading json to string
		String jsonContent = FileUtils.readFileToString(new File(FilePath),
		StandardCharsets.UTF_8);
		
		// String to HashMap using Jackson-DataBind dependency
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
	});
		
		return data;
	}

	public String getScreenShot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot sc = (TakesScreenshot)driver;
		File source = sc.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" + testcaseName + ".png";
		
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
