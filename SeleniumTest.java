package unitest;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import unitest.SuperDriver;
import unitest.SuperChecker;

public class SeleniumTest {
	 	private static InternetExplorerDriver driver;
	 	private static SuperDriver superDriver;
	 	private static SuperChecker superChecker;
	 	//WebElement element;

	 @BeforeClass
     public static void openBrowser(){
         driver = new InternetExplorerDriver();
         superDriver = new SuperDriver(driver);
         superChecker = new SuperChecker();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} 

	 @Test
     public void valid_UserCredential(){

		 System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	     driver.get("http://www.store.demoqa.com");	
	     superDriver.waitAndClickByXpath(".//*[@id='account']/a");
	     //driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	     //driver.findElement(By.id("log")).sendKeys("testuser_3");
	     superDriver.waitAndGetById("log").sendKeys("testuser_3");
	     superDriver.waitAndGetById("pwd").sendKeys("Test@123");
	     //driver.findElement(By.id("pwd")).sendKeys("Test@123");
	     //driver.findElement(By.id("login")).click();
	     superDriver.waitAndClickByID("login");
	     //try{
	    	 superChecker.AssertElementPresent(".//*[@id='account_logout']/a");
	     }
	    	 //element = driver.findElement (By.xpath(".//*[@id='account_logout']/a"));
		 //catch (Exception e){
			//}   
	    
	     //Assert.assertNotNull(element);
	     //System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
    //}

	 @AfterClass
	 public static void closeBrowser(){
		 driver.quit();
	 }
}