package org.superdriver.Examples;

import java.io.IOException;
import java.util.HashMap;

import javax.management.AttributeNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.superdriver.SuperChecker;
import org.superdriver.SuperDriver;
import org.superdriver.SuperFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DownloadMethods {
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
	public void Download() throws InterruptedException, AttributeNotFoundException, IOException{
		_sd.loadURL("https://www.thinkbroadband.com/download");
		_sd.click(_sd.getElement(By.xpath("//*[@id='main-col']/div/div/div[8]/p[1]/a/img")));
		_sd.enableLog();
		_sd.waitForFileDownloaded("5MB.zip", "C:\\Users\\aconesa\\Downloads\\pruebas-descargas");
		}
	 */
	
	/*
	@Test
	public void Downloadfail() throws InterruptedException, AttributeNotFoundException, IOException{
		_sd.loadURL("https://www.thinkbroadband.com/download");
		_sd.click(_sd.getElement(By.xpath("//*[@id=\'main-col\']/div/div/div[1]/p[1]/a/img")));
		_sd.enableLog();
		_sd.waitForFileDownloaded("1GB.zip", "C:\\Users\\aconesa\\Downloads\\pruebas-descargas", 10);
	}
	*/
	
	/*
	@Test
	public void deletefiles() throws InterruptedException, AttributeNotFoundException, IOException{
		_sd.enableLog();
		_sd.DeleteFileOrFolder("C:\\Users\\aconesa\\Downloads\\pruebas-descargas\\5MB.zip");
	}
	*/
	
}
