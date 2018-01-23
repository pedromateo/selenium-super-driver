package org.superdriver;

import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

	//private static InternetExplorerDriver _driver;
	private static ChromeDriver _driver;
	private static SuperDriver _sd;
	private static SuperChecker _sc;
	//WebElement element;

	@BeforeClass
	public static void openBrowser(){

		//System.setProperty("webdriver.firefox.marionette","/home/carm/selenium/drivers/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver","/home/carm/selenium/drivers/chromedriver");
		//System.setProperty("webdriver.ie.driver","/home/carm/selenium/drivers/IEDriverServer.exe");

		//_driver = new InternetExplorerDriver();
		_driver = new ChromeDriver();
		_sd = new SuperDriver(_driver);
		_sc = new SuperChecker(_sd);
		_driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	} 

	@Test
	public void valid_UserCredential(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		_sd.loadURL("http://www.store.demoqa.com");	
		_sd.clickByXpath(".//*[@id='account']/a");
		_sd.sendKeysByID("log", "user");
		_sd.sendKeysByID("pwd", "pass");
		_sd.clickByID("login");
		//try{
		_sc.assertElementPresent(".//*[@id='account_logout']/a");
	}


	@Test
	public void valid_UserCredential_2(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		_sd.loadURL("http://www.store.demoqa.com");	
		_sd.clickByXpath(".//*[@id='account']/a");
		_sd.sendKeysByID("log", "testuser_3");
		_sd.sendKeysByID("pwd", "Test@123");
		_sd.clickByID("login");
		//try{
		_sc.assertElementPresent(".//*[@id='account_logout']/a");
	}
	
	@Test
	public void assert_Title() {
		System.out.println("Starting test "+ new Object() {}.getClass().getEnclosingMethod().getName());
		_sd.loadURL("http://www.store.demoqa.com");
		_sc.assertTitle("ONLINE STORE | Toolsqa Dummy Test site");
		
	}


	@AfterClass
	public static void closeBrowser(){
		_driver.quit();
	}
}