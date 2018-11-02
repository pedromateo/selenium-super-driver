package org.superdriver.examples;

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
public class MouseMethods {

	//private static InternetExplorerDriver _driver;
	private static ChromeDriver _driver;
	private static SuperDriver _sd;
	private static SuperChecker _sc;
	private static SuperFilter _sf;
	private static String downloadDir = "C:\\Users\\aconesa\\Downloads\\pruebas-descargas";



	@BeforeClass
	public static void testIn() {



		/// Need to configure a custom driver? Do it here...

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\aconesa\\Downloads\\chromedriver_win32\\chromedriver_1.exe");		
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
	public void scroll() {
		_sd.loadURL("https://www.google.es/search?q=gatos&rlz=1C1GGRV_enES802ES802&source=lnms&tbm=isch&sa=X&ved=0ahUKEwja143fx9jdAhXN3KQKHeB5DCoQ_AUICigB&biw=1273&bih=934");
		_sd.scrollDownByPixel(500);
		_sd.waitTime(3000);
		_sd.scrollUpByPixel(500);
		_sd.waitTime(3000);
		_sd.scrollToElement(_sd.getElement(By.xpath("//*[@id=\"rg_s\"]/div[56]/a/img")));
		_sd.waitTime(3000);
	}
	*/

	/*
	@Test
	public void hover(){
		_sd.loadURL("https://www.google.es/");
		_sd.hover(By.id("gsri_ok0"));
	}
	*/
	
	/*
	@Test
	public void dragAndDropTest() {
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		_sd.loadURL("http://code.makery.ch/library/dart-drag-and-drop/");
		while(true) {
		_sd.dragAndDrop(By.id("draggable-a"), By.id("dropzone-1"));
		_sd.dragAndDrop(By.id("draggable-b"), By.id("dropzone-2"));
		}
	}
	*/
	
	
	@AfterClass
	public static void testOut(){
		_driver.quit();
	}
	
}
