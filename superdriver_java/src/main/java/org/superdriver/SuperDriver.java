package org.superdriver;

import static org.junit.Assert.fail;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.management.AttributeNotFoundException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

/**
 * 
 * @author Adrián Nicolás Conesa, Pedro Luís Mateo Navarro, ...
 * 
 */
public class SuperDriver {

	private RemoteWebDriver _driver;
	private static final int WAIT_TIMEOUT = 6;

	/**
	 * Builder that receives the driver as an argument and creates a SuperDriver type object.
	 * @param driver
	 */
	public SuperDriver(RemoteWebDriver driver) {
		_driver = driver;
	}
	public RemoteWebDriver getDriver() {
		return _driver;
	}


	///
	/// internal SuperDriver configuration
	///
	/// log system
	protected static boolean log_enabled = false;
	private final static String LOG_TAG = "[SD]: ";

	public SuperDriver enableLog() {
		log_enabled = true;
		return this;
	}

	public SuperDriver disableLog() {
		log_enabled = false;
		return this;
	}

	protected static <a> void _log(a msg) {
		if (log_enabled) {
			System.out.println(LOG_TAG + msg);
		}
	}


	///
	/// URL methods
	///
	/**
	 * Load a URL in the browser by .get function.
	 * @param String url
	 */
	public void loadURL(String url) {
		//_driver.get(url);	
		_driver.navigate().to(url);	
	}

	/**
	 * Load a URL in the browser by .navigate().to() function.
	 * @param url
	 */
	public void navigateURL(String url) {
		_driver.navigate().to(url);
	}


	///
	/// Wait
	///
	/**
	 * Makes a wait until the WebElement have presence on the WebPage.
	 * @param mode
	 * @param key
	 * @throws NoSuchElementException
	 */
	public void waitElementPresence(By selector){
		try {
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			wait.until(ExpectedConditions.presenceOfElementLocated(selector));
		}
		catch(Exception e){
			if (log_enabled) {
				e.printStackTrace();
			}
			throw new NotFoundException("Element "+ selector.toString()  +" is not found" );
		}
	}

	/**
	 * Wait until the a WebElement is not displayed.
	 * @param element
	 */
	public void waitInvisibilityOfElement(By selector) {
		try {
			if(isElementDisplayed(selector)){
				WebElement element = getElement(selector);
				_log("element" + element + "exists");
				WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
				wait.until(ExpectedConditions.invisibilityOf(element));
			}
		} catch (Exception e) {
			WebElement element = getElement(selector);
			highlightLocator(element);
			fail("The element - " + element + " - still present");
		}
	}

	/**
	 * Check if an element is displayed.
	 * @param selector
	 * @return
	 */
	public boolean isElementDisplayed(By selector) {
		WebElement elem;
		try {
			elem = getElement(selector);
			if (elem != null && elem.isDisplayed()) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			return false;
		}
		//		}
		//		catch (org.openqa.selenium.TimeoutException e) {
		//			return false;
		//		}
		//		catch (org.openqa.selenium.NoSuchElementException e) {
		//			return false;
		//		}
		//		catch (org.openqa.selenium.WebDriverException e) {
		//			return false;
		//		}
	}

	/**
	 * Makes a wait until the WebElement is visible on the WebPage.
	 * @param mode
	 * @param key
	 * @throws ElementNotVisibleException
	 */
	public void waitElementVisibilityBy(By selector){
		try {
			WebElement elemento;
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			elemento = getElement(selector);
			wait.until(ExpectedConditions.visibilityOf(elemento));
		}catch(Exception e) {
			if (log_enabled) {
				e.printStackTrace();
			}
			throw new ElementNotVisibleException("Element "+ selector.toString() +" is not visible" );
		}
	}

	/**
	 * Makes a wait until the WebElement is visible on the WebPage.
	 * @param Webelement
	 * @throws ElementNotVisibleException
	 */
	public void waitElementVisibility(WebElement elemento)throws ElementNotVisibleException{
		try {
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOf(elemento));
		}catch(Exception e) {
			if (log_enabled) {
				e.printStackTrace();
			}
			throw new ElementNotVisibleException("Element is not visible" + elemento);
		}
	}

	/**
	 * Makes a wait a determined time.
	 * @param Int Time in ms
	 * @throws TimeoutException
	 */
	public static void waitTime(int time)throws TimeoutException{
		try {
			TimeUnit.MILLISECONDS.sleep(time);
		}catch (Exception e){
			if (log_enabled) {
				e.printStackTrace();
			}
		throw new TimeoutException();
		}
	}

	/**
	 * Wait for a element to be clickable.
	 * @param element
	 */
	public void waitElementToBeClickable(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (NoSuchElementException e) {
			fail("Element "+element+" is not present");
		} catch (Exception e) {
			fail("Wait for the element "+element+" is not working");
		}
	}

	/**
	 * Wait for a element to be clickable.
	 * @param selector
	 */
	public void waitElementToBeClickable(By selector) {
		try {
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			wait.until(ExpectedConditions.elementToBeClickable(selector));
		} catch (NoSuchElementException e) {
			fail("Element "+selector.toString()+" is not present");
		} catch (Exception e) {
			fail("Wait for the element "+selector.toString()+" is not working");
		}
	}

	///
	/// Wait and get
	///
	/**
	 * Get the title of the WebPage
	 * @returnString title of the WebPage
	 */
	public String getTitle() {
		String title;
		waitElementPresence(By.tagName("title"));
		title = _driver.getTitle();
		return title;
	}

	/**
	 * Get an attribute from a WebElement
	 * @param WebElement
	 * @param String attribute
	 * @return String Attribute
	 * @throws AttributeNotFoundException
	 */
	public String getAttribute(WebElement elemento, String Att) throws AttributeNotFoundException {
		try {
			return elemento.getAttribute(Att);
		} catch (Exception e) {
			if (log_enabled) {
				e.printStackTrace();
			}
			throw new AttributeNotFoundException();
		}
	}

	/**
	 * Get an attribute from a WebElement
	 * @param String mode
	 * @param String key
	 * @param String Attribute
	 * @returnString Attribute
	 * @throws AttributeNotFoundException
	 */
	public String getAttribute(By selector, String Attribute) throws AttributeNotFoundException {
		try {
			WebElement element = getElement(selector);
			String att = null;
			att = element.getAttribute(Attribute);
			if (att == null) {
				throw new AttributeNotFoundException();
			}
			else
				return att;
		} catch (Exception e) {
			if (log_enabled) {
				e.printStackTrace();
			}
		throw new AttributeNotFoundException();
		}
	}

	/**
	 * Get a WebElement
	 * @param By
	 * @returnWebElement
	 * @throws NotFoundException
	 */
	public WebElement getElement(By selector) throws NotFoundException {
		try {
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			wait.until(ExpectedConditions.presenceOfElementLocated(selector)); 

			WebElement elem = _driver.findElement(selector);
			if (elem == null)
				throw new NotFoundException("Element not found " + selector.toString());
			else
				return elem;
		} catch (org.openqa.selenium.TimeoutException e) {
			if (log_enabled) {
				e.printStackTrace();
			}
			throw new NotFoundException("Element not found " + selector.toString());
		}
	}

	/**
	 * Get a WebElement list
	 * @param selector
	 * @returnList<WebElement>
	 * @throws NotFoundException
	 */
	public List<WebElement> getListOfElements(By selector) throws NotFoundException {
		try {
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			wait.until(ExpectedConditions.presenceOfElementLocated(selector)); 
			List<WebElement> listelem = null;
			listelem = _driver.findElements(selector);
			if (listelem == null)
				throw new NotFoundException(selector.toString());
			else
				return listelem;
		} catch (Exception e) {
			if (log_enabled) {
				e.printStackTrace();
			}
		}
		throw new NotFoundException("Element not found " + selector.toString());
	}


	///
	/// Wait and Click
	///
	/**
	 * Simulates a click on a WebElement.
	 * @param elemento
	 * @throws NotFoundException
	 */
	public void click(WebElement elemento) throws NotFoundException {
		WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT); 
		wait.until(ExpectedConditions.elementToBeClickable(elemento));
		if (elemento != null) {
			elemento.click();
		}
		else {throw new NoSuchElementException("WebElement not found.");}
	}

	/**
	 * Simulates a click on a WebElement.
	 * @param mode
	 * @param key
	 * @throws NotFoundException
	 */
	public void click(By selector) throws NotFoundException {
		WebElement elemento = getElement(selector);
		if (elemento != null) {
			click(elemento);
		}
		else {throw new NoSuchElementException("WebElement not found.");}
	}


	///
	/// Wait and Select option
	///
	/// By index
	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param mode
	 * @param key
	 * @param option 
	 */
	public void selectOptionByIndex(By selector, int option) {
		WebElement elemento = getElement(selector);
		selectOptionByIndex(elemento, option);
	}

	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param elemento
	 * @param option 
	 */
	public void selectOptionByIndex(WebElement elemento, int option) {
		waitElementVisibility(elemento);
		Select sel = new Select(elemento);
		sel.selectByIndex(option);
	}

	///By VisibleText
	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param mode
	 * @param key
	 * @param option 
	 */
	public void selectOptionByVisibleText(By selector, String option) {
		WebElement elemento = getElement(selector);
		selectOptionByVisibleText(elemento, option);
	}

	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param elemento
	 * @param option 
	 */
	public void selectOptionByVisibleText(WebElement elemento, String option) {
		waitElementVisibility(elemento);
		Select sel = new Select(elemento);
		sel.selectByVisibleText(option);
	}

	///By Value
	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param mode
	 * @param key
	 * @param option 
	 */
	public void selectOptionByValue(By selector, String option) {
		WebElement elemento = getElement(selector);
		selectOptionByValue(elemento, option);
	}

	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param Elemento
	 * @param option 
	 */
	public void selectOptionByValue(WebElement element, String option) {
		waitElementVisibility(element);
		Select sel = new Select(element);
		sel.selectByValue(option);
	}

	/**
	 * Check if a element is selected and return the value of this element
	 * @param element
	 * @return
	 */
	public String selectGetFirstSelectedOption(WebElement element) {
		String strFirstSelectedOption = null;
		try {
			Select sel = new Select(element);
			strFirstSelectedOption = sel.getFirstSelectedOption().getText();
		} catch (NoSuchElementException e) {
			fail("Select element is not present");
		} catch (Exception e) {
			fail("Could not find any selected option");
		}
		return strFirstSelectedOption;
	}


	///
	///deSelect option
	///
	/// Deselect By index
	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param mode
	 * @param key
	 * @param option 
	 */
	public void deSelectOptionByIndex(By selector, int option) {
		WebElement elemento = getElement(selector);
		deSelectOptionByIndex(elemento, option);
	}

	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param elemento
	 * @param option
	 */
	public void deSelectOptionByIndex(WebElement elemento, int option) {
		waitElementVisibility(elemento);
		Select sel = new Select(elemento);
		sel.deselectByIndex(option);
	}


	///Deselect By VisibleText
	/**
	 * Deselect a option from a dropbox, radio button or checkbox.
	 * @param mode
	 * @param key
	 * @param option
	 */
	public void deSelectOptionByVisibleText(By selector, String option) {
		WebElement elemento = getElement(selector);
		deSelectOptionByVisibleText(elemento, option);
	}

	/**
	 * Deselect a option from a dropbox, radio button or checkbox.
	 * @param elemento
	 * @param option
	 */
	public void deSelectOptionByVisibleText(WebElement elemento, String option) {
		waitElementVisibility(elemento);
		Select sel;
		sel = new Select(elemento);
		sel.deselectByVisibleText(option);
	}


	///Deselect By Value
	/**
	 * Deselect a option from a dropbox, radio button or checkbox.
	 * @param mode
	 * @param key
	 * @param option
	 */
	public void deSelectOptionByValue(By selector, String option) {
		WebElement elemento = getElement(selector);
		deSelectOptionByValue(elemento, option);
	}

	/**
	 * Deselect a option from a dropbox, radio button or checkbox.
	 * @param elemento
	 * @param option
	 */
	public void deSelectOptionByValue(WebElement elemento, String option) {
		waitElementVisibility(elemento);
		Select sel = new Select(elemento);
		sel.deselectByValue(option);
	}

	///deselect all
	/**
	 * Deselect all the options from a multiple choose box, radio button or checkbox.
	 * @param mode
	 * @param key
	 */
	public void deSelectAllOptions(By selector) {
		WebElement elemento = getElement(selector);
		deSelectAllOptions(elemento);
	}

	/**
	 * Deselect all the options from a multiple choose box, radio button or checkbox.
	 * @param elemento
	 */
	public void deSelectAllOptions(WebElement elemento) {
		waitElementVisibility(elemento);
		Select sel = new Select(elemento);
		sel.deselectAll();
	}


	///
	/// wait and send keys
	///
	/**
	 * Clear a the input text from a textbox or textinput element
	 * @param element
	 */
	public void textboxClear(WebElement element) {
		try {
			waitElementToBeClickable(element);
			element.clear();
		} catch (java.util.NoSuchElementException e) {
			fail("Textbox element is not present");
		} catch (Exception e) {
			fail("Textbox text can not be cleared");
		}
	}

	public void textboxClear(By selector) {
		try {
			waitElementToBeClickable(selector);
			WebElement element = getElement(selector);
			element.clear();
		} catch (java.util.NoSuchElementException e) {
			fail("Textbox element is not present");
		} catch (Exception e) {
			fail("Textbox text can not be cleared");
		}
	}
	/**
	 * Write in a Text box.
	 * @param By selector
	 * @param String key
	 * @param String sendKey
	 */
	public void sendKeys(By selector, String sendKey) {
		WebElement elemento = getElement(selector);
		sendKeys(elemento, sendKey);
	}

	/**
	 * Write in a Text box.
	 * @param elemento
	 * @param sendKey
	 */
	public void sendKeys(WebElement elemento, String sendKey) {
		if (elemento != null) {
			elemento.sendKeys(sendKey);
		}
		else {throw new NoSuchElementException("Element not found.");
		}
	}


	///
	/// Browser and Window Methods
	///

	// Change between tabs
	/**
	 * Switch to the selected window. 0 = main window of the browser 
	 * @param int number
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void switchToWindow(int number) {
		ArrayList tabs = new ArrayList (_driver.getWindowHandles());
		_driver.switchTo().window(tabs.get(number).toString());
	}

	/**
	 * Open a new tab in the browser
	 * @throws AWTException
	 */
	public void newTab() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);
	}

	/**
	 * Switch between the tabs in the browser
	 * @throws AWTException
	 */
	public void switchTab() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	/**
	 * Maximize window
	 */
	public void maximize() {
		_driver.manage().window().maximize();
	}

	/**
	 * Switch to last window and maximize it
	 * @throws InterruptedException
	 */
	public void switchToNewWindow() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
		wait.wait(WAIT_TIMEOUT);
		for(String window : _driver.getWindowHandles()) {
			_driver.switchTo().window(window);
		}
		this.maximize();
	}

	/**
	 * Switch to main window and maximize it
	 * @throws InterruptedException
	 */
	public void switchToMainWindow() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
		wait.wait(WAIT_TIMEOUT);
		_driver.switchTo().window(_driver.getWindowHandles().iterator().next());
	}

	/**
	 * Load a cookie inserting cookie name and token
	 * @param name
	 * @param token
	 */
	public void loadCookie(String name, String token) {
		Cookie cookie = new Cookie(name, token);
		_driver.manage().addCookie(cookie);
	}

	/// Delete cookies
	/**
	 * Delete all the cookies of the browser.
	 */
	public void deletecookies() {
		_driver.manage().deleteAllCookies();
	}


	///
	/// Mouse Movement Methods
	///
	/**
	 * Drag an element and drop it in other location
	 * @param modesource
	 * @param source
	 * @param modetarget
	 * @param target
	 */
	public void dragAndDrop(By selectorsource, By selectortarget) {
		WebElement _source = null, _target = null;
		Actions builder = new Actions(_driver);
		builder.keyDown(Keys.CONTROL)
		.click(_source)
		.click(_target)
		.keyUp(Keys.CONTROL);
		Action dragAndDrop = builder.build();
		_source = getElement(selectorsource);
		_target = getElement(selectortarget);
		dragAndDrop.perform();
	}

	public void moveViewToElement(By selector) {
		WebElement _where;
		Actions builder = new Actions(_driver);
		_where = getElement(selector);
		builder.moveToElement(_where).perform();
	}

	public void scrollToElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) _driver;       			
			js.executeScript("arguments[0].scrollIntoView();", element);
		}
		catch (ElementNotFoundException e) {
			_log("Element not found");
		}
	}

	public void scrollDownByPixel(int pixelnum) {
		JavascriptExecutor js = (JavascriptExecutor) _driver;	
		js.executeScript("window.scrollBy(0,"+pixelnum+")");
	}

	public void scrollUpByPixel(int pixelnum) {
		pixelnum = pixelnum - (pixelnum*2);
		JavascriptExecutor js = (JavascriptExecutor) _driver;	
		js.executeScript("window.scrollBy(0,"+pixelnum+")");
	}

	public static String dateGetCurrentDate() {
		String strCurrentTime = null;
		try {
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm:ss");
			strCurrentTime = dateFormat.format(date);

		} catch (Exception e) {
			e.getMessage();
		}
		return strCurrentTime;
	}


	///
	///Alerts
	///
	public String alertGetText() {
		String strValue = null;
		try {
			waitAlertIsPresent();
			Alert alt = _driver.switchTo().alert();
			strValue = alt.getText();
		} catch (NoAlertPresentException e) {
			fail("Alert is not present");
		} catch (Exception e) {
			fail("Alert pop-up text could not be retrieved");
		}
		return strValue;
	}

	public void alertDismiss() {
		try {
			waitAlertIsPresent();
			Alert alt = _driver.switchTo().alert();
			alt.dismiss();
		} catch (NoAlertPresentException e) {
			fail("Alert is not present");
		} catch (Exception e) {
			fail("Alert can not be dismissed");
		}
	}

	public void alertAccept() {
		try {
			waitAlertIsPresent();
			Alert alt = _driver.switchTo().alert();
			alt.accept();
		} catch (NoAlertPresentException e) {
			fail("Alert is not present");
		} catch (Exception e) {
			fail("Alert can not be accepted");
		}
	}

	public boolean alertIsPresent() {
		waitAlertIsPresent();
		boolean blnValue = false;
		try{
			_driver.switchTo().alert();
			blnValue =  true;
		}catch (NoAlertPresentException e) {
			blnValue =  false;
		}
		return blnValue;
	}

	private void waitAlertIsPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(_driver, 20);
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			_log("Wait for the element is not working");
		}
	}


	///
	///Highlight
	///
	public void highlightLocator(WebElement locator){
		try{
			JavascriptExecutor js = (JavascriptExecutor) _driver;
			js.executeScript("arguments[0].style.border='3px solid red'",locator);
		}catch(NoSuchElementException e){
			fail("The webelement to be highlighted is not present");
		}catch (Exception e){
			fail("Highlighting can not be performmed");
		}
	}


	///
	/// Wait and hover
	///
	/**
	 * Simulates the Overpass of the mouse over a element.
	 * @param mode
	 * @param key
	 */
	public void hover(By selector) {
		WebElement elemento;
		Actions action = new Actions(_driver);
		elemento = getElement(selector);
		action.moveToElement(elemento).build().perform();
	}

	/**
	 * Simulates the Overpass of the mouse over a element.
	 * @param elemento
	 */
	public void hover(WebElement elemento) {
		Actions action = new Actions(_driver);
		action.moveToElement(elemento).build().perform();
	}

	/**
	 * Close the browser
	 */
	public void closeBrowser() {
		_driver.close();
	}

	/**
	 * Quit the Browser
	 */
	public void quitBrowser() {
		_driver.quit();
	}

	///
	/// Download methods
	///

	/**
	 * Check if a file is in a folder, Else it will click and wait until the file appears in the path.
	 * @param filename
	 * @param directory
	 * @param timemax
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void waitForFileDownloaded(String filename, String directory, int timemaxsec) throws IOException, InterruptedException { 
		boolean check = false;
		int timer = 0;
		if (isFileDownloaded(filename, directory) == false) {
			do {
				if ((isFileDownloaded(filename, directory)) == true) {
					check = true;
					_log("File: "+ filename+" is Downloaded on the directory "+directory);
				}
				else {
					Thread.sleep(1000);
					timer = timer + 1;
				}
			}while((check == false) && (timer <= timemaxsec));
			if ((timer == timemaxsec) || (check != true)) {
				_log("File: "+ filename+" is not Downloaded "+ directory);
				throw new FileNotFoundException();
			}
		}
		else {
			_log("File: " + filename + " is already Downloaded and avaiable on the directory "+directory);
		}
	}

	/**
	 * Check if a file is in a folder, Else it will click and wait until the file appears in the path with a max time of 120 sec.
	 * @param filename
	 * @param directory
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void waitForFileDownloaded(String filename, String directory) throws IOException, InterruptedException { 
		waitForFileDownloaded(filename, directory, 120);
	}

	/**
	 * Check if a file exist in a folder.
	 * @param fileName
	 * @param path
	 * @return
	 */
	public boolean isFileDownloaded(String fileName, String path) throws FileNotFoundException {
		boolean flag = false;
		File dir = new File(path);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				flag = true;
		}
		return flag;
	}


	///
	///PDF methods
	///

	/**
	 * Check if a String exist in a PDF with Caps/Symbols sensitivity.
	 * @param word
	 * @param pdfpath
	 * @return Boolean 
	 */
	public boolean existInPDF(String word, String pdfPath) throws IOException {
		boolean exist = false;
		PdfReader reader = new PdfReader(pdfPath);
		for (int i=1; i<=reader.getNumberOfPages(); i=i+1) {
			String page = PdfTextExtractor.getTextFromPage(reader, i);
			if (page.contains(word)) {
				_log("Word -" + word + "- exist in page: " + i);
				exist = true;   
			}
		}
		return exist;
	}


	///
	///Excel Methods
	///

	/**
	 * Takes the data from a sheet of a XLS file, throwing it into an array.
	 * @param String XLS_File_Path
	 * @param int Sheet_Number -starts from 0-
	 * @return String [][]
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public String[][] fromXLStoArray(String XLSfilepath, int SheetNumber) throws IOException, InvalidFormatException {
		// Creating a Workbook from an Excel file (.xls)
		Workbook workbook = WorkbookFactory.create(new File(XLSfilepath));
		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(SheetNumber);
		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();
		//Initialize the array length
		int row_count = 0;
		int colum_count_max = 0;
		for (Row row: sheet) {
			int colum_count_of_the_row = 0;
			for(@SuppressWarnings("unused") Cell cell: row) {
				colum_count_of_the_row = colum_count_of_the_row +1;
				if (colum_count_of_the_row > colum_count_max) {
					colum_count_max = colum_count_of_the_row;
				}
			}
			row_count=row_count + 1;
		}
		//inicialize array
		String[][] data = new  String[row_count][colum_count_max];
		// Use a for-each loop to iterate over the rows and columns
		int row_count_write = 0;
		for (Row row: sheet) {
			int colum_count_write = 0;	
			for(Cell cell: row) {
				data[row_count_write][colum_count_write]=dataFormatter.formatCellValue(cell);
				colum_count_write = colum_count_write + 1;
			}
			row_count_write = row_count_write + 1; 
		}
		_log("the array of data is: ");
		for(int i=0; i <= row_count-1; i=i+1) {
			_log("{");
			for(int j=0; j <= colum_count_max-1; j=j+1) {
				if (j == colum_count_max-1) {
					_log(data[i][j]);
				}
				else {
					_log(data[i][j]+", ");
				}
			}
			if (i == row_count-1) {
				_log("}");
			}
			else {
				_log("}, ");
			}
		}
		// Closing the workbook
		workbook.close();
		return data;
	}


	///
	/// Delete file or directory
	///
	/**
	 * Force deletion of directory or file.
	 * @param path
	 */
	public void DeleteFileOrFolder(String path) {
		File file = new File(path);
		if(file.exists()) {
			do {
				delete(file);
			}
			while(file.exists());
		}
		else {
			_log("File or Folder not found : "+path);
		}
	}

	private void delete(File file) {
		if(file.isDirectory()) {
			String fileList[] = file.list();
			if(fileList.length == 0) {
				_log("Deleting Directory : "+file.getPath());
				file.delete();
			}
			else {
				int size = fileList.length;
				for(int i = 0 ; i < size ; i++) {
					String fileName = fileList[i];
					_log("File path : "+file.getPath()+" and name :"+fileName);
					String fullPath = file.getPath()+"/"+fileName;
					File fileOrFolder = new File(fullPath);
					_log("Full Path :"+fileOrFolder.getPath());
					delete(fileOrFolder);
				}
			}
		}
		else {
			_log("Deleting file : "+file.getPath());
			file.delete();
		}
	}


	///
	/// Screenshot method
	///
	public void captureScreenshot(WebDriver browser, String path, String screenshotName, String format){
		try {
			TakesScreenshot ts=(TakesScreenshot)browser;
			File source=ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(path + screenshotName + format));
		} catch (Exception e){
			_log("Exception while taking screenshot "+e.getMessage());
		}
	}

	public void captureScreenshot(String path, String screenshotName, String format){
		try {
			TakesScreenshot ts=(TakesScreenshot)_driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(path + screenshotName + format));
		} catch (Exception e){
			_log("Exception while taking screenshot "+e.getMessage());
		}
	}

	public void captureScreenshot(String path, String screenshotName){
		Robot robot;
		try {
			robot = new Robot();
			BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG", new File(path + screenshotName + ".jpg"));
			_log(path + screenshotName + ".jpg - Has been created");
		} 
		catch (AWTException e) {
			if (log_enabled) {
				e.printStackTrace();
				_log("Error Taking the screenshot");
			}
		} 
		catch (IOException e) {
			if (log_enabled) {
				_log("Error creating the file - " + path + screenshotName + ".jpg");
				e.printStackTrace();
			}
		}
	}

	public void captureScreenshot(String path) throws Exception, FileNotFoundException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
		Calendar now = Calendar.getInstance();
		Robot robot = new Robot();
		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(screenShot, "JPG", new File(path + formatter.format(now.getTime()) + ".jpg"));
		_log(path + formatter.format(now.getTime()) + ".jpg");
	}

	public static void captureScreenshot() throws Exception, FileNotFoundException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
		Calendar now = Calendar.getInstance();
		Robot robot = new Robot();
		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(screenShot, "JPG", new File("./pruebas/screenshot" + formatter.format(now.getTime()) + ".jpg"));
		_log("./pruebas/screenshot" + formatter.format(now.getTime()) + ".jpg");
	}
}
