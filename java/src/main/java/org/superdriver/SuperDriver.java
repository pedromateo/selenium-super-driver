package org.superdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.management.AttributeNotFoundException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

/**
 * 
 * @author Adrian Nicolas Conesa,...
 *
 */
@SuppressWarnings("incomplete-switch")
public class SuperDriver {
	private RemoteWebDriver _driver;
	private static final int WAIT_TIMEOUT = 10;
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
	/// URL methods
	///
	/**
	 * Load a URL in the browser.
	 * @param String url
	 */
	public void loadURL(String url) {
		_driver.get(url);	
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
	public void waitForElementPresenceBy(How mode, String key) throws NoSuchElementException{
		try {
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			switch(mode) {
			case XPATH:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(key)));
				break;
			case ID:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(key)));
				break;
			case TAG_NAME:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(key)));
				break;
			case NAME:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(key)));
				break;
			case CSS:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(key)));
				break;
			case LINK_TEXT:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(key)));
				break;
			case PARTIAL_LINK_TEXT:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(key)));
				break;
			case CLASS_NAME:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(key)));
			}
		} catch(Exception e) {e.printStackTrace();
		throw new NotFoundException("Element "+key+" is not found" );
		}
	}
	/**
	 * Makes a wait until the WebElement is visible on the WebPage.
	 * @param mode
	 * @param key
	 * @throws ElementNotVisibleException
	 */
	public void waitForElementVisibilityBy(How mode, String key) throws ElementNotVisibleException{
		try {
			WebElement elemento;
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			elemento = get(mode, key);
			wait.until(ExpectedConditions.visibilityOf(elemento));
		}catch(Exception e) {e.printStackTrace();
		throw new  ElementNotVisibleException("Element "+key+" is not visible" );
		}
	}
	/**
	 * Makes a wait until the WebElement is visible on the WebPage.
	 * @param Webelement
	 * @throws ElementNotVisibleException
	 */
	public void waitForElementVisibility(WebElement elemento)throws ElementNotVisibleException{
		try {
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOf(elemento));
		}catch(Exception e) {e.printStackTrace();
		throw new ElementNotVisibleException("Element is not visible" );
		}
	}
	/**
	 * Makes a wait a determined time.
	 * @param Int Time in ms
	 * @throws TimeoutException
	 */
	public void waitTime(int time)throws TimeoutException{
		try {
			TimeUnit.MILLISECONDS.sleep(time);
		}catch (Exception e){e.printStackTrace();
		throw new TimeoutException();
		}
	}
	///
	/// Wait and get
	///
	/**
	 * Get the title of the WebPage
	 * @return
	 */
	public String getTitle() {
		String title;
		waitForElementPresenceBy(How.TAG_NAME, "title");
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
			e.printStackTrace();
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
	public String getAttribute(How mode, String key, String Attribute) throws AttributeNotFoundException {
		try {
			WebElement element = get(mode,key);
			String att = null;
			att = element.getAttribute(Attribute);
			if (att == null) {
				throw new AttributeNotFoundException();
			}
			else
				return att;
		} catch (Exception e) {e.printStackTrace();
		throw new AttributeNotFoundException();
		}
	}
	/**
	 * Get a WebElement
	 * @param how
	 * @return WebElement
	 * @throws NotFoundException
	 */
	private WebElement get(By how) throws NotFoundException {
		try {
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			wait.until(ExpectedConditions.presenceOfElementLocated(how)); 
			WebElement elem = null;
			elem = _driver.findElement(how);
			if (elem == null)
				throw new NotFoundException(how.toString());
			else
				return elem;
		} catch (Exception e) {e.printStackTrace();
		throw new NotFoundException("Element not found " + how.toString());
		}
	}
	/**
	 * Get a WebElement
	 * @param How mode
	 * @param String key
	 * @return WebElement
	 * @throws NotFoundException
	 */
	public WebElement get(How mode, String key)  throws NotFoundException {
		try {
			switch(mode) {
			case XPATH:
				return getByXpath(key);
			case ID:
				return getByID(key);
			case TAG_NAME:
				return getByTagName(key);
			case NAME:
				return getByName(key);
			case CSS:
				return getByCssSelector(key);
			case LINK_TEXT:
				return getByLinkText(key);
			case PARTIAL_LINK_TEXT:
				return getByPartialLinkText(key);
			case CLASS_NAME:
				return getByClass(key);
			default:
				throw new NotFoundException("Mode not found");
			}
		} catch (Exception e) {e.printStackTrace();
		throw new NotFoundException("Element " + key + " not found");
		}
	}

	// Using the driver methods, searches for an element (key) using a pattern 
	// (how) All methods return a WebElement type object.
	/**
	 * Get a WebElement by Xpath locator.
	 * @param key
	 * @return WebElement
	 */
	public WebElement getByXpath(String key) {
		ByXPath objetoBusqueda = new ByXPath(key);
		return get(objetoBusqueda);
		//  Using the driver methods, searches for an 
		// element by XPATH (key) and returns it.
	}
	/**
	 * Get a WebElement by Id locator.
	 * @param key
	 * @return WebElement
	 */
	public WebElement getByID(String key) {
		ById objetoBusqueda = new ById(key);
		return get(objetoBusqueda);
		// Using the driver methods, searches for an element by ID (key) and returns it.
	}
	/**
	 * Get a WebElement by Name locator.
	 * @param key
	 * @return WebElement
	 */
	public WebElement getByName(String key) {
		ByName objetoBusqueda = new ByName(key);
		return get(objetoBusqueda);

		// Using the driver methods, searches for an element by ID (key) and returns it.
	}
	/**
	 * Get a WebElement by CssSelector locator.
	 * @param key
	 * @return WebElement
	 */
	public WebElement getByCssSelector(String key) {
		ByCssSelector objetoBusqueda = new ByCssSelector(key);
		return get(objetoBusqueda);
		// Using the driver methods, searches for an element by ID (key) and returns it.
	}
	/**
	 * Get a WebElement by LinkText locator.
	 * @param key
	 * @return WebElement
	 */
	public WebElement getByLinkText(String key) {
		ByLinkText objetoBusqueda = new ByLinkText(key);
		return get(objetoBusqueda);
		// Using the driver methods, searches for an element by ID (key) and returns it.
	}
	/**
	 * Get a WebElement by PartialLinkText locator.
	 * @param key
	 * @return WebElement
	 */
	public WebElement getByPartialLinkText(String key) {
		ByPartialLinkText objetoBusqueda = new ByPartialLinkText(key);
		return get(objetoBusqueda);
		// Using the driver methods, searches for an element by ID (key) and returns it.
	}
	/**
	 * Get a WebElement by TagName locator.
	 * @param key
	 * @return WebElement
	 */
	public WebElement getByTagName(String key) {
		ByTagName objetoBusqueda = new ByTagName(key);
		return get(objetoBusqueda);
		// Using the driver methods, searches for an element by ID 
		// (key) and returns it.
	}
	/**
	 * Get a WebElement by Class locator.
	 * @param key
	 * @return WebElement
	 */
	public WebElement getByClass(String key) {
		ByClassName objetoBusqueda = new ByClassName(key);
		return get(objetoBusqueda);
		// Using the driver methods, searches for an element by ID 
		// (key) and returns it.
	}
	/**
	 * Using the driver methods, searches for an List<WebElements>.
	 * @param how
	 * @return
	 * @throws NotFoundException
	 */
	private List<WebElement> getListOfElements(By how) throws NotFoundException {
		try { 
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			wait.until(ExpectedConditions.presenceOfElementLocated(how)); 
			List <WebElement> lista = null;
			lista = _driver.findElements(how);
			if (lista == null)
				throw new NotFoundException(how.toString());
			else 
				return lista;
		} catch (Exception e) {e.printStackTrace();
		throw new NotFoundException("Element not found " + how.toString());
		}
	}
	/**
	 * Searches for an List<WebElements>.
	 * @param mode
	 * @param key
	 * @return
	 * @throws NotFoundException
	 */
	public List<WebElement> getListOfElements(How mode, String key) throws NotFoundException {
		try {
			switch(mode) {
			case XPATH:
				return getListOfElementsByXpath(key);
			case ID:
				return getListOfElementsByID(key);
			case TAG_NAME:
				return getListOfElementsByTagName(key);
			case NAME:
				return getListOfElementsByName(key);
			case CSS:
				return getListOfElementsByCssSelector(key);
			case LINK_TEXT:
				return getListOfElementsByLinkText(key);
			case PARTIAL_LINK_TEXT:
				return getListOfElementsByPartialLinkText(key);
			case CLASS_NAME:
				return getListOfElementsByClass(key);
			default:
				throw new NotFoundException();
			}
		} catch (Exception e) {e.printStackTrace();
		throw new NotFoundException();
		}
	}
	/**
	 * Search a List<WebElements> using a Xpath locator.
	 * @param key
	 * @return
	 */
	public List<WebElement> getListOfElementsByXpath(String key) {
		ByXPath objetoBusqueda = new ByXPath(key);
		return getListOfElements(objetoBusqueda);
	}
	/**
	 * Search a List<WebElements> using a ID locator.
	 * @param key
	 * @return
	 */
	public List<WebElement> getListOfElementsByID(String key) {
		ById objetoBusqueda = new ById(key);
		return getListOfElements(objetoBusqueda);
	}
	/**
	 * Search a List<WebElements> using a Name locator.
	 * @param key
	 * @return
	 */
	public List<WebElement> getListOfElementsByName(String key) {
		ByName objetoBusqueda = new ByName(key);
		return getListOfElements(objetoBusqueda);
	}
	/**
	 * Search a List<WebElements> using a CssSelector locator.
	 * @param key
	 * @return
	 */
	public List<WebElement> getListOfElementsByCssSelector(String key) {
		ByCssSelector objetoBusqueda = new ByCssSelector(key);
		return getListOfElements(objetoBusqueda);
	}
	/**
	 * Search a List<WebElements> using a LinkText locator.
	 * @param key
	 * @return
	 */
	public List<WebElement> getListOfElementsByLinkText(String key) {
		ByLinkText objetoBusqueda = new ByLinkText(key);
		return getListOfElements(objetoBusqueda);
	}
	/**
	 * Search a List<WebElements> using a PartialLinkText locator.
	 * @param key
	 * @return
	 */
	public List<WebElement> getListOfElementsByPartialLinkText(String key) {
		ByPartialLinkText objetoBusqueda = new ByPartialLinkText(key);
		return getListOfElements(objetoBusqueda);
	}
	/**
	 * Search a List<WebElements> using a TagName locator. 
	 * @param key
	 * @return
	 */
	public List<WebElement> getListOfElementsByTagName(String key) {
		ByTagName objetoBusqueda = new ByTagName(key);
		return getListOfElements(objetoBusqueda);
	}
	/**
	 * Search a List<WebElements> using a ClassName locator.
	 * @param key
	 * @return
	 */
	public List<WebElement> getListOfElementsByClass(String key) {
		ByClassName objetoBusqueda = new ByClassName(key);
		return getListOfElements(objetoBusqueda);
	}

	///
	/// Wait and Click
	///
	/**
	 * Simulates a click over a WebElement.
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
	 * Simulates a click over a WebElement.
	 * @param mode
	 * @param key
	 * @throws NotFoundException
	 */
	public void click(How mode, String key)throws NotFoundException {
		WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT); 
		WebElement elemento =wait.until(ExpectedConditions.elementToBeClickable(get(mode,key)));
		if (elemento != null) {
			click(elemento);
		}
		else {throw new NoSuchElementException("WebElement not found.");}
	}

	public void clickByXpath(String key) {
		click(How.XPATH, key);
	}

	public void clickByID(String key) {
		click(How.ID, key);
	}

	public void clickByTagName(String key) {
		click(How.TAG_NAME, key);
	}

	public void clickByName(String key) {
		click(How.NAME, key);
	}

	public void clickByCssSelector(String key) {
		click(How.CSS, key);
	}

	public void clickByLinkText(String key) {
		click(How.LINK_TEXT, key);
	}

	public void clickByPartialLinkText(String key) {
		click(How.PARTIAL_LINK_TEXT, key);
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
	public void selectOptionByIndex(How mode, String key, int option) {
		WebElement elemento = get(mode,key);
		selectOptionByIndex(elemento, option);
	}
	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param elemento
	 * @param option 
	 */
	public void selectOptionByIndex(WebElement elemento, int option) {
		waitForElementVisibility(elemento);
		Select sel = new Select(elemento);
		sel.selectByIndex(option);
	}

	public void selectOptionByXpathByIndex(String key, int option) {
		selectOptionByIndex(How.XPATH, key, option);
	}

	public void selectOptionByIDByIndex(String key, int option) {
		selectOptionByIndex(How.ID, key, option);
	}

	public void selectOptionByTagNameByIndex(String key, int option) {
		selectOptionByIndex(How.TAG_NAME, key, option);
	}

	public void selectOptionByNameByIndex(String key, int option) {
		selectOptionByIndex(How.NAME, key, option);
	}

	public void selectOptionCssSelectorByIndex(String key, int option) {
		selectOptionByIndex(How.CSS, key, option);
	}

	public void selectOptionLinkTextByIndex(String key, int option) {
		selectOptionByIndex(How.LINK_TEXT, key, option);
	}

	public void selectOptionPartialLinkTextByIndex(String key, int option) {
		selectOptionByIndex(How.PARTIAL_LINK_TEXT, key, option);
	}

	public void selectOptionByClassByIndex(String key, int option) {
		selectOptionByIndex(How.CLASS_NAME, key, option);
	}

	///By VisibleText
	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param mode
	 * @param key
	 * @param option 
	 */
	public void selectOptionByVisibleText(How mode, String key, String option) {
		WebElement elemento = get(mode,key);
		selectOptionByVisibleText(elemento, option);
	}
	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param elemento
	 * @param option 
	 */
	public void selectOptionByVisibleText(WebElement elemento, String option) {
		waitForElementVisibility(elemento);
		Select sel = new Select(elemento);
		sel.selectByVisibleText(option);
	}

	public void selectOptionByXpathByVisibleText(String key, String option) {
		selectOptionByVisibleText(How.XPATH, key, option);
	}

	public void selectOptionByIDByVisibleText(String key, String option) {
		selectOptionByVisibleText(How.ID, key, option);
	}

	public void selectOptionByTagNameByVisibleText(String key, String option) {
		selectOptionByVisibleText(How.TAG_NAME, key, option);
	}

	public void selectOptionByNameByVisibleText(String key, String option) {
		selectOptionByVisibleText(How.NAME, key, option);
	}

	public void selectOptionCssSelectorByVisibleText(String key, String option) {
		selectOptionByVisibleText(How.CSS, key, option);
	}

	public void selectOptionLinkTextByVisibleText(String key, String option) {
		selectOptionByVisibleText(How.LINK_TEXT, key, option);
	}

	public void selectOptionPartialLinkTextByVisibleText(String key, String option) {
		selectOptionByVisibleText(How.PARTIAL_LINK_TEXT, key, option);
	}

	public void selectOptionByClassByVisibleText(String key, String option) {
		selectOptionByVisibleText(How.CLASS_NAME, key, option);
	}

	///By Value
	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param mode
	 * @param key
	 * @param option 
	 */
	public void selectOptionByValue(How mode, String key, String option) {
		WebElement elemento = get(mode,key);
		selectOptionByValue(elemento, option);
	}
	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param Elemento
	 * @param option 
	 */
	public void selectOptionByValue(WebElement elemento, String option) {
		waitForElementVisibility(elemento);
		Select sel = new Select(elemento);
		sel.selectByValue(option);
	}

	public void selectOptionByXpathByValue(String key, String option) {
		selectOptionByValue(How.XPATH, key, option);
	}

	public void selectOptionByIDByValue(String key, String option) {
		selectOptionByValue(How.ID, key, option);
	}

	public void selectOptionByTagNameByValue(String key, String option) {
		selectOptionByValue(How.TAG_NAME, key, option);
	}

	public void selectOptionByNameByValue(String key, String option) {
		selectOptionByValue(How.NAME, key, option);
	}

	public void selectOptionCssSelectorByValue(String key, String option) {
		selectOptionByValue(How.CSS, key, option);
	}

	public void selectOptionLinkTextByValue(String key, String option) {
		selectOptionByValue(How.LINK_TEXT, key, option);
	}

	public void selectOptionPartialLinkTextByValue(String key, String option) {
		selectOptionByValue(How.PARTIAL_LINK_TEXT, key, option);
	}

	public void selectOptionByClassByValue(String key, String option) {
		selectOptionByValue(How.CLASS_NAME, key, option);
	}

	///
	/// check if is selected and deSelect option
	///
	/// Deselect By index

	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param mode
	 * @param key
	 * @param option 
	 */
	public void deSelectOptionByIndex(How mode, String key, int option) {
		WebElement elemento = get(mode,key);
		deSelectOptionByIndex(elemento, option);
	}

	/**
	 * Select a option from a dropbox, radio button or checkbox.
	 * @param elemento
	 * @param option
	 */
	public void deSelectOptionByIndex(WebElement elemento, int option) {
		waitForElementVisibility(elemento);
		Select sel = new Select(elemento);
		sel.deselectByIndex(option);
	}

	public void deSelectOptionByXpathByIndex(String key, int option) {
		deSelectOptionByIndex(How.XPATH, key, option);
	}

	public void deSelectOptionByIDByIndex(String key, int option) {
		deSelectOptionByIndex(How.ID, key, option);
	}

	public void deSelectOptionByTagNameByIndex(String key, int option) {
		deSelectOptionByIndex(How.TAG_NAME, key, option);
	}

	public void deSelectOptionByNameByIndex(String key, int option) {
		deSelectOptionByIndex(How.NAME, key, option);
	}

	public void deSelectOptionByCssSelectorByIndex(String key, int option) {
		deSelectOptionByIndex(How.CSS, key, option);
	}

	public void deSelectOptionByLinkTextByIndex(String key, int option) {
		deSelectOptionByIndex(How.LINK_TEXT, key, option);
	}

	public void deSelectOptionByPartialLinkTextByIndex(String key, int option) {
		deSelectOptionByIndex(How.PARTIAL_LINK_TEXT, key, option);
	}

	public void deSelectOptionByClassByIndex(String key, int option) {
		deSelectOptionByIndex(How.CLASS_NAME, key, option);
	}

	///Deselect By VisibleText
	/**
	 * Deselect a option from a dropbox, radio button or checkbox.
	 * @param mode
	 * @param key
	 * @param option
	 */
	public void deSelectOptionByVisibleText(How mode, String key, String option) {
		WebElement elemento = get(mode,key);
		deSelectOptionByVisibleText(elemento, option);
	}
	/**
	 * Deselect a option from a dropbox, radio button or checkbox.
	 * @param elemento
	 * @param option
	 */
	public void deSelectOptionByVisibleText(WebElement elemento, String option) {
		waitForElementVisibility(elemento);
		Select sel;
		sel = new Select(elemento);
		sel.deselectByVisibleText(option);
	}

	public void deSelectOptionByXpathByVisibleText(String key, String option) {
		deSelectOptionByVisibleText(How.XPATH, key, option);
	}

	public void deSelectOptionByIDByVisibleText(String key, String option) {
		deSelectOptionByVisibleText(How.ID, key, option);
	}

	public void deSelectOptionByTagNameByVisibleText(String key, String option) {
		deSelectOptionByVisibleText(How.TAG_NAME, key, option);
	}

	public void deSelectOptionByNameByVisibleText(String key, String option) {
		deSelectOptionByVisibleText(How.NAME, key, option);
	}

	public void deSelectOptionCssSelectorByVisibleText(String key, String option) {
		deSelectOptionByVisibleText(How.CSS, key, option);
	}

	public void deSelectOptionLinkTextByVisibleText(String key, String option) {
		deSelectOptionByVisibleText(How.LINK_TEXT, key, option);
	}

	public void deSelectOptionPartialLinkTextByVisibleText(String key, String option) {
		deSelectOptionByVisibleText(How.PARTIAL_LINK_TEXT, key, option);
	}

	public void deSelectOptionByClassByVisibleText(String key, String option) {
		deSelectOptionByVisibleText(How.CLASS_NAME, key, option);
	}

	///Deselect By Value
	/**
	 * Deselect a option from a dropbox, radio button or checkbox.
	 * @param mode
	 * @param key
	 * @param option
	 */
	public void deSelectOptionByValue(How mode, String key, String option) {
		WebElement elemento = get(mode,key);
		deSelectOptionByValue(elemento, option);
	}
	/**
	 * Deselect a option from a dropbox, radio button or checkbox.
	 * @param elemento
	 * @param option
	 */
	public void deSelectOptionByValue(WebElement elemento, String option) {
		waitForElementVisibility(elemento);
		Select sel = new Select(elemento);
		sel.deselectByValue(option);
	}

	public void deSelectOptionByXpathByValue(String key, String option) {
		deSelectOptionByValue(How.XPATH, key, option);
	}

	public void deSelectOptionByIDByValue(String key, String option) {
		deSelectOptionByValue(How.ID, key, option);
	}

	public void deSelectOptionByTagNameByValue(String key, String option) {
		deSelectOptionByValue(How.TAG_NAME, key, option);
	}

	public void deSelectOptionByNameByValue(String key, String option) {
		deSelectOptionByValue(How.NAME, key, option);
	}

	public void deSelectOptionCssSelectorByValue(String key, String option) {
		deSelectOptionByValue(How.CSS, key, option);
	}

	public void deSelectOptionLinkTextByValue(String key, String option) {
		deSelectOptionByValue(How.LINK_TEXT, key, option);
	}

	public void deSelectOptionPartialLinkTextByValue(String key, String option) {
		deSelectOptionByValue(How.PARTIAL_LINK_TEXT, key, option);
	}

	public void deSelectOptionByClassByValue(String key, String option) {
		deSelectOptionByValue(How.CLASS_NAME, key, option);
	}

	///deselect all
	/**
	 * Deselect all the options from a multiple choose box, radio button or checkbox.
	 * @param mode
	 * @param key
	 */
	public void deSelectAllOptions(How mode, String key) {
		WebElement elemento = get(mode,key);
		deSelectAllOptions(elemento);
	}
	/**
	 * Deselect all the options from a multiple choose box, radio button or checkbox.
	 * @param elemento
	 */
	public void deSelectAllOptions(WebElement elemento) {
		waitForElementVisibility(elemento);
		Select sel = new Select(elemento);
		sel.deselectAll();;
	}

	///
	/// wait and send keys
	///
	/**
	 * Write in a Text box.
	 * @param How mode
	 * @param String key
	 * @param String sendKey
	 */
	public void sendKeys(How mode, String key, String sendKey) {
		WebElement elemento = get(mode,key);
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

	public void sendKeysByID(String elem, String sendKey) {
		sendKeys(How.ID, elem, sendKey);
	}

	public void sendKeysByXpath(String elem, String sendKey) {
		sendKeys(How.XPATH, elem, sendKey);
	}

	public void sendKeysByTagName(String elem, String sendKey) {
		sendKeys(How.TAG_NAME, elem, sendKey);
	}

	public void sendKeysByName(String elem, String sendKey) {
		sendKeys(How.NAME, elem, sendKey);
	}

	public void sendKeysByCssSelector(String elem, String sendKey) {
		sendKeys(How.CSS, elem, sendKey);
	}

	public void sendKeysByLinkText(String elem, String sendKey) {
		sendKeys(How.LINK_TEXT, elem, sendKey);
	}

	public void sendKeysByPartialLinkText(String elem, String sendKey) {
		sendKeys(How.PARTIAL_LINK_TEXT, elem, sendKey);
	}

	public void sendKeysByClass(String elem, String sendKey) {
		sendKeys(How.CLASS_NAME, elem, sendKey);
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
	 * Delete all the cockies of the browser.
	 */
	public void deletecookies() {
		_driver.manage().deleteAllCookies();
	}

	///
	/// Mouse Movement Methods
	///
	public void dragAndDrop(How mode, String source, String target) {
		WebElement _source = null, _target = null;
		Actions builder = new Actions(_driver);
		builder.keyDown(Keys.CONTROL)
		.click(_source)
		.click(_target)
		.keyUp(Keys.CONTROL);
		Action dragAndDrop = builder.build();
		_source = get(mode, source);
		_target = get(mode, target);
		dragAndDrop.perform();
	}

	public void MoveTo(How mode, String where) {
		WebElement _where;
		Actions builder = new Actions(_driver);
		_where = get(mode, where);
		builder.moveToElement(_where).perform();
	}

	public void moveBetweenXAxis(How mode, WebElement from, WebElement where) {
		Point location;
		int x,y;
		Actions builder = new Actions(_driver);
		location = where.getLocation();
		x = location.getX();
		location = from.getLocation();
		y = location.getY();
		builder.moveByOffset(x,y).moveToElement(where).perform();
	}

	public void moveBetweenAxis(How mode, WebElement from, WebElement where) {
		Point location;
		int x,y;
		Actions builder = new Actions(_driver);
		location = from.getLocation();
		x= location.getX();
		location = where.getLocation();
		y= location.getY();
		builder.moveByOffset(x,y).moveToElement(where).perform();
	}
	///
	/// Wait and hover
	///
	/**
	 * Simulates the Overpass of the mouse over a element.
	 * @param mode
	 * @param key
	 */
	public void hover(How mode, String key) {
		WebElement elemento;
		Actions action = new Actions(_driver);
		elemento = get(mode, key);
		action.moveToElement(elemento).build().perform();
	}
	/**
	 * Close the browser
	 */
	public void closeBrowser() {
		_driver.close();
	}

	///
	/// Download methods
	///
	/**
	 * Check if a file is in a folder, Else it will click and wait until the file apears in the path.
	 * @param filename
	 * @param directory
	 * @param element
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void waitForFileDownload(String filename, String directory, WebElement element) throws IOException, InterruptedException { 
		if (isFileDownloaded(filename, directory) == false) {
			click(element);
			boolean check = false;
			do {
				if (isFileDownloaded(filename, directory) == true) {
					check = true;
				}
				else {
					Thread.sleep(500);
				}
			}while(check != true);
			System.out.println("File: "+ filename+" is in the path "+ directory);	
		}
		else {
			System.out.println("File: "+ filename+" is already Downloaded and avaiable on the directory "+directory);
		}
	}
	/**
	 * Check if a file exist in a folder.
	 * @param fileName
	 * @param directory
	 * @return
	 */
	public boolean isFileDownloaded(String fileName, String path) {
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
	 * Check if a word exist in a PDF with Caps sensitivity.
	 * @param word
	 * @param PDF_Path
	 * @return Boolean
	 */
	public boolean existInPDF(String word, String PDF_Path) {
		boolean exist = false;
		try {
			PdfReader reader = new PdfReader(PDF_Path);
			for (int i=1; i<=reader.getNumberOfPages(); i=i+1) {
				String page = PdfTextExtractor.getTextFromPage(reader, i);
				if (page.contains(word)) {
					System.out.println("Word -" + word + "- exist in page: " + i);
					exist = true;   
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
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
	public String[][] fromXLStoArray(String XLS_File_Path, int Sheet_Number) throws IOException, InvalidFormatException {
		// Creating a Workbook from an Excel file (.xls)
		Workbook workbook = WorkbookFactory.create(new File(XLS_File_Path));
		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(Sheet_Number);
		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();
		//Initialize the array length
		int row_count = 0;
		int colum_count_max = 0;

		for (Row row: sheet) {
			int colum_count_of_the_row = 0;
			for(@SuppressWarnings("unused") Cell cell: row) {
				colum_count_of_the_row = colum_count_of_the_row +1;;
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
		System.out.print("the array of data is: ");
		for(int i=0; i <= row_count-1; i=i+1) {
			System.out.print("{");
			for(int j=0; j <= colum_count_max-1; j=j+1) {
				if (j == colum_count_max-1) {
					System.out.print(data[i][j]);
				}
				else {
					System.out.print(data[i][j]+", ");
				}
			}
			if (i == row_count-1) {
				System.out.println("}");
			}
			else {
				System.out.print("}, ");
			}
		}
		// Closing the workbook
		workbook.close();
		return data;
	}

}
