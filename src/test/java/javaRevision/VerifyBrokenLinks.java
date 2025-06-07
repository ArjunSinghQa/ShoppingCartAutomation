package javaRevision;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyBrokenLinks {

	public static WebDriver driver;
	
	public static void main(String[] args) {
		
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com");
		
		List<WebElement> linkElements = driver.findElements(By.tagName("a"));
		
		Set<String> links = new HashSet<>();
		
		for(WebElement element : linkElements)
		{
			String url = element.getDomAttribute("href");
			links.add(url); // collecting non duplicate urls
		}
		
		
		ExecutorService executor = Executors.newFixedThreadPool(50);
		
		for(String link : links)
		{
			executor.submit(()->verifylink(link));
		}
		

		executor.shutdown();
		driver.quit();
		
	}

	public static void verifylink(String url) {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("deprecation")
			URL link = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)link.openConnection();
			connection.setConnectTimeout(3000);
			connection.connect();
			if(connection.getResponseCode()>=400) {
				System.out.println(url+" "+connection.getResponseMessage()+"  "+" is broken");
			}
		}catch(Exception e)
		{
			System.out.println(url + "  is broken");
		}
	}

}
