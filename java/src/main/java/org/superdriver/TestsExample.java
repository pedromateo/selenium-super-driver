package org.superdriver;

import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.How;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestsExample {

	//private static InternetExplorerDriver _driver;
	private static ChromeDriver _driver;
	private static SuperDriver _sd;
	private static SuperChecker _sc;

	@BeforeClass
	public static void testIn(){

		///
		/// Need to configure a custom driver? Do it here...
		System.setProperty("webdriver.chrome.driver","D:\\testing_p\\seleniumpack\\selenium_pm\\drivers\\chromedriver.exe");
		//System.setProperty("webdriver.ie.driver","/home/user/selenium/drivers/IEDriverServer.exe");

		///
		/// Create here the original driver and make custom configurations if needed
		_driver = new ChromeDriver();
		//_driver = new InternetExplorerDriver();
		//_driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		///
		/// create SuperDriver and SuperChecker here
		_sd = new SuperDriver(_driver);
		_sc = new SuperChecker(_sd);
	}

	/*@Test
		public void pruebaCarm() {
			System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
			_sd.loadURL("http://localhost:4002/");
			_sd.maximize();
			_sd.clickByXpath("//*[@id=\"navbarColor02\"]/ul[1]/li[1]/a");
			_sc.assertElementPresent("/html/body/app-root/app-form-list/div/app-table/table/tbody/tr[2]/td[1]");
			_sd.clickByXpath("/html/body/app-root/app-form-list/div/app-table/table/tbody/tr[2]/td[1]");
			_sc.assertElementPresent("/html/body/app-root/app-form-list/app-dialog[2]/div/div/div/div[2]/card-body/button");
			_sd.clickByXpath("/html/body/app-root/app-form-list/app-dialog[2]/div/div/div/div[2]/card-body/button");
			_sd.clickByXpath("//*[@id=\"steps-canvas\"]/li[1]/button/i");
			_sd.dragAndDrop(How.XPATH, "//*[@id=\"1\"]/button", "//*[@id=\"0\"]/div/div");
			_sd.clickByXpath("/html/body/app-root/app-drap-and-drop/div[1]/button/i");
			_sc.assertElementPresent("//*[@id=\"form-preview\"]/div/div/form/formly-form/formly-field/formly-wrapper-fieldset/div/formly-wrapper-label/label");
		}*/



	/*@Test
		public void dragAndDropTest() {
			System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
			_sd.loadURL("http://code.makery.ch/library/dart-drag-and-drop/");
			while(true) {
			_sd.dragAndDrop(How.ID, "draggable-a", "dropzone-1");
			_sd.dragAndDrop(How.ID, "draggable-b", "dropzone-2");
			}

		}*/

	/*@Test
		public void browserFeatures() {
			System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
			_sd.loadURL("http://www.store.demoqa.com");	
			_sd.maximize();
			_sd.newTab();
			_sd.newTab();
			_sd.switchTab();

		}*/

	@Test
	public void assert_Title() {
		System.out.println("Starting test "+ new Object() {}.getClass().getEnclosingMethod().getName());
		_sd.loadURL("http://www.store.demoqa.com");
		_sc.assertTitle("ONLINE STORE | Toolsqa Dummy Test site");
	}

	@Test
	public void valid_UserCredential(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		_sd.loadURL("http://www.store.demoqa.com");	
		_sd.clickByXpath(".//*[@id='account']/a");
		_sd.sendKeysByID("log", "murciatest1");
		_sd.sendKeysByID("pwd", "murcia20,8");
		_sd.clickByID("login");
		//try{
		_sc.assertElementPresent(".//*[@id='account_logout']/a");
	}

	@Test
	public void novalid_UserCredential(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		_sd.loadURL("http://www.store.demoqa.com");	
		_sd.clickByXpath(".//*[@id='account']/a");
		_sd.sendKeysByName("log", "testuser_3");
		_sd.sendKeysByID("pwd", "Test@123");
		_sd.clickByID("login");
		//try{
		_sc.assertElementPresent(".//*[@id='account_logout']/a");
	}

	@AfterClass
	public static void testOut(){
		_driver.quit();
	}

}