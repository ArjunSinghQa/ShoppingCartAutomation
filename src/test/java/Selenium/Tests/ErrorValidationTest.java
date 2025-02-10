package Selenium.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Selenium.MavenProject1.ProductCatalogue;
import Selenium.TestComponent.BaseTest;

public class ErrorValidationTest extends BaseTest {
	
	@Test
	public void submitOrder() throws Exception
	{
		String productname = "IPHONE 13 PRO";
		ProductCatalogue pc = landingpage.loginApplication("arjunsingh308569@gmail.com", "Bangari@3089");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		//
		//.ng-tns-c4-3.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		
		
		

	}
	
	

}
