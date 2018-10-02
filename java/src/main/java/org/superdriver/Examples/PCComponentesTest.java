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
public class PCComponentesTest {
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

		
		@Test
		public void PCComponentesTestWhitSD() throws InterruptedException, AttributeNotFoundException{
			_sd.maximize();
			_sd.loadURL("https://www.pccomponentes.com");
			System.out.println(_sd.getAttribute(How.TAG_NAME, "a", "href"));
			System.out.println("Estamos testeando la pagina:\n" + _sd.getTitle() + "\n");
			_sd.clickByTagName("i");
			List<String> mainmenu = _sf.mapListOfAttributes(_sf.ByAttribute("id", "GTM").applyFilter(_sd.getByID("main-menu").findElements(By.tagName("a"))), "title");
			_sf.clearFilter();
			List<String> mainmenu1 = _sf.mapListOfAttributes(_sf.ByAttribute("id", "GTM").applyFilter(_sd.getByID("main-menu").findElements(By.tagName("a"))), "href");
			_sf.clearFilter();
			Object[] mainmenuarray = mainmenu.toArray();
			Object[] mainmenuarray1 = mainmenu1.toArray();
			for(int i=0; i<mainmenu1.size();i++) {
				if (i == 0) {
					System.out.println("La pagina tiene las siguientes secciones:");
				}
				System.out.println(mainmenuarray[i] +" - "+ mainmenuarray1[i]);
				_sd.loadURL(mainmenuarray1[i].toString());
				List<String> subsection = _sf.mapListOfText(_sf.ByAttribute("class", "enlace-secundario").applyFilter( _sd.getByID("main").findElements(By.tagName("a"))));
				_sf.clearFilter();
				System.out.println(subsection + "\n" + "---------------------------------");
				List<String> subsection1 = _sf.mapListOfAttributes((_sf.ByAttribute("class", "enlace-secundario", false).applyFilter( _sd.getByID("main").findElements(By.tagName("a")))), "href");
				_sf.clearFilter();
				for (String urlsub:subsection1) {
					_sd.loadURL(urlsub);
					System.out.println("----------" + _driver.findElement(By.tagName("h1")).getText() + "----------");
					List<String> item = _sf.mapListOfAttributes((_sf.ByAttribute("class", "enlace-superpuesto").applyFilter( _sd.getByID("articleListContent").findElements(By.tagName("a")))),"data-name");
					_sf.clearFilter();
					List<String> item1 = _sf.mapListOfAttributes((_sf.ByAttribute("class", "enlace-superpuesto").applyFilter( _sd.getByID("articleListContent").findElements(By.tagName("a")))),"href");
					_sf.clearFilter();
					Object[] itemarray = item.toArray();
					int counter=0;
					for (String itemurl:item1) {
						_sd.loadURL(itemurl);
						System.out.println(itemarray[counter]);
						counter++;
						List<WebElement> objitem =_sf.ByTagName("img", false).applyFilter(_sd.getByClass("pccom-super-slider-tabs").findElements(By.className("lazyOwl")));
						_sf.clearFilter();
						for (WebElement img : objitem) {
							_sd.click(img);
						}
					}
				}
			}
		}


		@Test
		public void PCComponentesTestWhitoutSD() throws InterruptedException, AttributeNotFoundException{
			_driver.navigate().to("https://www.pccomponentes.com");
			_driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
			System.out.println("Estamos testeando la pagina:\n" + _driver.getTitle() + "\n");
			_driver.findElement(By.tagName("i")).click();
			List<String> mainmenu = _driver.findElement(By.id("main-menu")).findElements(By.tagName("a")).stream()
					.filter(a -> a.getAttribute("id").contains("GTM"))
					.map(a -> a.getAttribute("title").toString())
					.collect(Collectors.toList());
			List<String> mainmenu1 = _driver.findElement(By.id("main-menu")).findElements(By.tagName("a")).stream()
					.filter(a -> a.getAttribute("id").contains("GTM"))
					.map(a -> a.getAttribute("href").toString())
					.collect(Collectors.toList());
			Object[] mainmenuarray = mainmenu.toArray();
			Object[] mainmenuarray1 = mainmenu1.toArray();
			for(int i=0; i<mainmenu1.size();i++) {
				if (i == 0) {
					System.out.println("La pagina tiene las siguientes secciones:");
				}
				System.out.println(mainmenuarray[i] +" - "+ mainmenuarray1[i]);
				_driver.navigate().to(mainmenuarray1[i].toString());
				_driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
				List<String> subsection = _driver.findElement(By.id("main")).findElements(By.tagName("a"))
						.stream()
						.filter(a -> a.getAttribute("class").contains("enlace-secundario"))
						.map(a -> a.getText())
						.collect(Collectors.toList());
				System.out.println(subsection + "\n" + "---------------------------------");
				List<String> subsection1 = _driver.findElement(By.id("main")).findElements(By.tagName("a"))
						.stream()
						.filter(a -> a.getAttribute("class").contains("enlace-secundario"))
						.map(a -> a.getAttribute("href"))
						.collect(Collectors.toList());
				for (String urlsub:subsection1) {
					_driver.navigate().to(urlsub);
					_driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
					System.out.println("----------" + _driver.findElement(By.tagName("h1")).getText() + "----------");
					List <String> item = _driver.findElement(By.id("articleListContent")).findElements(By.tagName("a"))
							.stream()
							.filter(a -> a.getAttribute("class").contains("enlace-superpuesto"))
							.map(a -> a.getAttribute("data-name"))
							.collect(Collectors.toList());
					List <String> item1 = _driver.findElement(By.id("articleListContent")).findElements(By.tagName("a"))
							.stream()
							.filter(a -> a.getAttribute("class").contains("enlace-superpuesto"))
							.map(a -> a.getAttribute("href"))
							.collect(Collectors.toList());
					Object[] itemarray = item.toArray();
					int counter=0;
					for (String itemurl:item1) {
						Thread.sleep(200);
						_driver.navigate().to(itemurl);
						_driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);
						System.out.println(itemarray[counter]);
						counter++;
						List<WebElement> objitem = _driver.findElement(By.className("pccom-super-slider-tabs")).findElements(By.className("item")).stream()
								.map(a-> a.findElement(By.tagName("img")))
								.collect(Collectors.toList());
						for (WebElement img : objitem) {
							img.click();
							Thread.sleep(200);
						}
					}
				}
			}
		}
	
		
		
}