package unitest;

import unitest.SuperDriver;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.remote.RemoteWebDriver;



public class SuperChecker {

	private SuperDriver _SuperDriver;
	private Assert _checker;
	private RemoteWebDriver _driver;
	


	public SuperChecker() {
		// TODO Auto-generated constructor stub
	}


	public boolean AssertElementPresent (String what)
	{
		SuperDriver _SuperDriver = new SuperDriver(_driver);
		try {
	        return true;
		boolean elemento = _driver.findElementsByXPath(what);
		Assert.assertNotNull(elemento);
		}
		catch (Exception e) {
			
		}
		
		}
		
		
		//Assert.assertTrue(elemento.isDisplayed());
	}
