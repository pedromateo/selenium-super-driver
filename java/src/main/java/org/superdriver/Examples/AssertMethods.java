package org.superdriver.Examples;


import java.util.HashMap;
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
import java.util.concurrent.TimeUnit;
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
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

@SuppressWarnings("unused")
public class AssertMethods {
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
	public void assertTrue() throws InterruptedException, AttributeNotFoundException{
		_sd.loadURL("https://www.google.es/");
		_sc.assertTrue(_sc.elementPresentByID("lst-ib"));
	}
	*/
	
	/*
	@Test
	public void assertTruefail() throws InterruptedException, AttributeNotFoundException{
		_driver.navigate().to("https://www.pccomponentes.com");
		_driver.findElement(By.tagName("i")).click();
		List<String> ListD = _driver.findElement(By.id("main-menu")).findElements(By.tagName("a")).stream()
				.filter(a -> a.getAttribute("id").contains("GTM"))
				.map(a -> a.getAttribute("title").toString())
				.collect(Collectors.toList());
		
		List<String> ListSD = _sf.mapListOfAttributes(_sf.ByAttribute("id", "GTM").applyFilter(_sd.getByID("main-menu").findElements(By.tagName("a"))), "title");
		_sf.clearFilter();
		_sc.assertEquals(ListD, ListSD);
	}
	*/
	
	/*
	@Test
	public void assertFalse() throws InterruptedException, AttributeNotFoundException{
		_sd.loadURL("https://www.google.es/");
		System.out.print(_sd.getByXpath("//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]").getAttribute("value"));
		_sc.assertFalse(_sd.getByXpath("//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]").getAttribute("value").contains("Hola"));
	}
	*/
	
	/*
	@Test
	public void assertFalse() throws InterruptedException, AttributeNotFoundException{
		_sd.loadURL("https://www.google.es/");
		_sc.fail();
	}
	*/
	
}
