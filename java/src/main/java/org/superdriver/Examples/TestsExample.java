package org.superdriver.Examples;

import java.awt.AWTException;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.AttributeNotFoundException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.How;
import org.superdriver.SuperChecker;
import org.superdriver.SuperDriver;
import org.superdriver.SuperFilter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


@SuppressWarnings("unused")
public class TestsExample {

	//private static InternetExplorerDriver _driver;
	private static ChromeDriver _driver;
	private static SuperDriver _sd;
	private static SuperChecker _sc;
	private static SuperFilter _sf;
	private static String downloadDir = "C:\\Users\\aconesa\\Desktop\\pmproyect\\proyect\\java\\pruebas\\downloads";



	@BeforeClass
	public static void testIn() {
		/// Need to configure a custom driver? Do it here...

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");		
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("plugins.plugins_disabled", new String[] {"Chrome PDF Viewer"});
		chromeOptions.put("plugins.always_open_pdf_externally", true);
		chromeOptions.put("profile.default_content_settings.popups", 0);
		chromeOptions.put("download.prompt_for_download", "false");
		options.setExperimentalOption("prefs", chromeOptions);
		//Change here the download folder
		String downloadFilepath = downloadDir;
		chromeOptions.put("download.default_directory", downloadFilepath);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);  

		/// Create here the original driver and make custom configurations if needed
		_driver = new ChromeDriver(cap);
		_driver.manage().window().maximize();

		///
		/// create SuperDriver, SuperChecker and SuperFilter here
		_sd = new SuperDriver(_driver);
		_sc = new SuperChecker(_sd);
		_sf = new SuperFilter(_sd);
	}

	//********************************
	//			Test area
	//********************************

	/*
	@Test
		public void excelprueba() throws InvalidFormatException, IOException {
		_sd.fromXLStoArray("./sample-xlsx-file.xlsx", 0);
	}
	*/

	/*
	@Test
	public void pruebaCarm() {
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		_sd.loadURL("http://localhost:4002/");
		_sd.maximize();
		_sd.click(By.xpath("//*[@id=\"navbarColor02\"]/ul[1]/li[1]/a"));
		_sc.elementPresent(By.xpath("/html/body/app-root/app-form-list/div/app-table/table/tbody/tr[2]/td[1]"));
		_sd.click(By.xpath("/html/body/app-root/app-form-list/div/app-table/table/tbody/tr[2]/td[1]"));
		_sc.elementPresent(By.xpath("/html/body/app-root/app-form-list/app-dialog[2]/div/div/div/div[2]/card-body/button"));
		_sd.click(By.xpath("/html/body/app-root/app-form-list/app-dialog[2]/div/div/div/div[2]/card-body/button"));
		_sd.click(By.xpath("//*[@id=\"steps-canvas\"]/li[1]/button/i"));
		_sd.dragAndDrop(By.xpath("//*[@id=\"1\"]/button"), By.xpath("//*[@id=\"0\"]/div/div"));
		_sd.click(By.xpath("/html/body/app-root/app-drap-and-drop/div[1]/button/i"));
		_sc.elementPresent(By.xpath("//*[@id=\"form-preview\"]/div/div/form/formly-form/formly-field/formly-wrapper-fieldset/div/formly-wrapper-label/label"));
	}
	*/

	/*
	@Test
	public void downloadPrueba() throws InterruptedException, AttributeNotFoundException, IOException{
		_sd.loadURL("https://www.thinkbroadband.com/download");
		_sd.click(By.xpath("//*[@id=\"main-col\"]/div/div/div[8]/p[1]/a/img"));
		_sd.waitForFileDownloaded("5MB.zip", "C:\\Users\\aconesa\\Downloads\\pruebas-descargas");
	}
	*/
	
	/*
	@Test
	public void browserFeatures() throws AWTException {
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		_sd.loadURL("http://www.store.demoqa.com");	
		_sd.maximize();
		_sd.newTab();
		_sd.newTab();
		_sd.switchTab();
	}
	*/

	/*
	@Test
	public void valid_UserCredential(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		_sd.loadURL("http://www.store.demoqa.com");	
		_sd.click(By.xpath(".//*[@id='account']/a"));
		_sd.sendKeys(By.id("log"), "murciatest1");
		_sd.sendKeys(By.id("pwd"), "murcia20,8");
		_sd.click(By.id("login"));
		//try{
		_sc.elementPresent(By.xpath(".//*[@id='account_logout']/a"));
	}
	*/

	/*
	@Test
	public void novalid_UserCredential(){
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		_sd.loadURL("http://www.store.demoqa.com");	
		_sd.click(By.xpath(".//*[@id='account']/a"));
		_sd.sendKeys(By.name("log"), "testuser_3");
		_sd.sendKeys(By.id("pwd"), "Test@123");
		_sd.click(By.id("login"));
		//try{
		_sc.elementPresent(By.xpath(".//*[@id='account_logout']/a"));
	}
	*/
	
	/*
	@Test
	public void pdfTest(){
		_sd.existInPDF("Problema", "./Selenium tutorial 4.pdf");
	}
	*/
	
	
	@Test
	public void AutomationRPA() throws IOException, InterruptedException, InvalidFormatException {

		_sd.loadURL("http://www.rpachallenge.com/");
		if (_sd.isFileDownloaded("challenge.xlsx", ".\\pruebas\\downloads")==false) {
			_sd.click(By.xpath("/html/body/div/div/div[1]/div/section[2]/div/a[1]"));
			_sd.waitForFileDownloaded("challenge.xlsx", ".\\pruebas\\downloads", 12);	
		}
		String[][] data = _sd.fromXLStoArray(".\\pruebas\\downloads\\challenge.xlsx", 0);
		_sd.click(By.xpath("/html/body/div/div/div[1]/div/section[3]/div/button"));		        

		for (int count=1; count<11;count++) {
			List<String> cuadros = _sf.mapListOfText(_sd.getElement(By.className("js-randomFormContainer")).findElements(By.tagName("label")));

			List<WebElement> cajas = _sd.getElement(By.className("js-randomFormContainer")).findElements(By.tagName("input")).stream()
					.collect(Collectors.toList());

			for (String titulo:cuadros) {
				int pos = cuadros.indexOf(titulo);
				if (titulo.contains("First Name")){
					cajas.get(pos).sendKeys(data[count][0]);

				}else if(titulo.contains("Last Name")){
					cajas.get(pos).sendKeys(data[count][1]);

				}else if(titulo.contains("Role in Company")){
					cajas.get(pos).sendKeys(data[count][3]);

				}else if(titulo.contains("Address")){
					cajas.get(pos).sendKeys(data[count][4]);

				}else if(titulo.contains("Email")){
					cajas.get(pos).sendKeys(data[count][5]);

				}else if(titulo.contains("Phone Number")){
					cajas.get(pos).sendKeys(data[count][6]);

				}else if(titulo.contains("Company Name")){
					cajas.get(pos).sendKeys(data[count][2]);
				}
			}
			_sd.getElement(By.className("js-randomFormContainer")).findElement(By.className("btn-default")).click();
		}
		_sd.DeleteFileOrFolder(".\\pruebas\\downloads\\challenge.xlsx");
	}
	
	
	/*
	@Test
	public void takeScreenshot() throws Exception{
		
		_sd.enableLog().takeScreenshot();
	}
	*/
	
	/*
	@Test
	public void waits(){
		_sd.waitForElementPresenceBy(How.ID, "lst-ib");
		_sd.waitForElementVisibilityBy(How.ID, "lst-ib");
		_sd.waitTime(100);
	}
	*/
	
	/*
	@Test
	public void prueba(){
		_sd.loadURL("https://www.google.com");
	}
	*/
	
	@AfterClass
	public static void testOut(){
		_driver.quit();
	}
}


