package org.superdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.ByAngular;

public class SuperDriver {

	// Superdriver class that enhances the functionality of the Selenium driver
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

	public void loadURL(String url) {
		_driver.get(url);	
	}

	///
	/// Wait and get
	///
	public String waitAndGetTitle() {
		String title;
		WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")));
		title = _driver.getTitle();
		return title;
	}
	public WebElement waitAndGet(By how) {
		try {
			// return WebDriverWait(self._driver,
			// self.WAIT_TIMEOUT).until(EC.visibility_of_element_located((mode, key)))

			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			wait.until(ExpectedConditions.presenceOfElementLocated(how)); // throws a timeout exception if element not
			// present after waiting <timeoutInSeconds>
			// seconds
			return _driver.findElement(how);
		} catch (Exception e) {
			return null;
		}
	}

	// Using the driver methods, searches for an element (key) using a pattern 
	// (how) All methods return a WebElement type object.
	public WebElement waitAndGetByXpath(String key) {

		ByXPath objetoBusqueda = new ByXPath(key);
		return waitAndGet(objetoBusqueda);

		//  Using the driver methods, searches for an 
		// element by XPATH (key) and returns it.
	}

	public WebElement waitAndGetByID(String key) {
		ById objetoBusqueda = new ById(key);
		return waitAndGet(objetoBusqueda);

		// Using the driver methods, searches for an element by ID (key) and returns it.
	}
	
	public WebElement waitAndGetByName(String key) {
		ByName objetoBusqueda = new ByName(key);
		return waitAndGet(objetoBusqueda);
		
		// Using the driver methods, searches for an element by ID (key) and returns it.
	}
	
	public WebElement waitAndGetByCssSelector(String key) {
		ByCssSelector objetoBusqueda = new ByCssSelector(key);
		return waitAndGet(objetoBusqueda);

		// Using the driver methods, searches for an element by ID (key) and returns it.
	}
	
	public WebElement waitAndGetByLinkText(String key) {
		ByLinkText objetoBusqueda = new ByLinkText(key);
		return waitAndGet(objetoBusqueda);

		// Using the driver methods, searches for an element by ID (key) and returns it.
	}
	
	public WebElement waitAndGetByPartialLinkText(String key) {
		ByPartialLinkText objetoBusqueda = new ByPartialLinkText(key);
		return waitAndGet(objetoBusqueda);

		// Using the driver methods, searches for an element by ID (key) and returns it.
	}

	public WebElement waitAndGetByTagName(String key) {
		ByTagName objetoBusqueda = new ByTagName(key);
		return waitAndGet(objetoBusqueda);
		// Using the driver methods, searches for an element by ID 
		// (key) and returns it.
	}

	///
	/// Wait and Click
	///

	public void waitAndClick(How mode, String key) {

		WebElement elemento;
		switch(mode) {
			case XPATH:
				elemento = waitAndGetByXpath(key);
				elemento.click();
				break;
			case ID:
				elemento = waitAndGetByXpath(key);
				elemento.click();
				break;
			case TAG_NAME:
				elemento = waitAndGetByXpath(key);
				elemento.click();
				break;
			case NAME:
				elemento = waitAndGetByXpath(key);
				elemento.click();
				break;
			case CSS:
				elemento = waitAndGetByXpath(key);
				elemento.click();
				break;
			case LINK_TEXT:
				elemento = waitAndGetByXpath(key);
				elemento.click();
				break;
			case PARTIAL_LINK_TEXT:
				elemento = waitAndGetByXpath(key);
				elemento.click();
				break;
		}
	}

	public void clickByXpath(String key) {
		waitAndClick(How.XPATH, key);
	}

	public void clickByID(String key) {
		waitAndClick(How.ID, key);
	}

	public void clickByTagName(String key) {
		waitAndClick(How.TAG_NAME, key);
	}
	
	public void clickByName(String key) {
		waitAndClick(How.NAME, key);
	}
	
	public void clickByCssSelector(String key) {
		waitAndClick(How.CSS, key);
	}
	
	public void clickByLinkText(String key) {
		waitAndClick(How.LINK_TEXT, key);
	}
	
	public void clickByPartialLinkText(String key) {
		waitAndClick(How.PARTIAL_LINK_TEXT, key);
	}

	///
	/// Wait and Select option
	///

	public void waitAndSelectOption(How mode, String key, int option) {

		WebElement elemento;
		Select sel;
		switch(mode) {
			case XPATH:
				elemento = waitAndGetByXpath(key);
				sel = new Select(elemento);
				sel.selectByIndex(option);
				break;
			case ID:
				elemento = waitAndGetByID(key);
				sel = new Select(elemento);
				sel.selectByIndex(option);
				break;
			case TAG_NAME:
				elemento = waitAndGetByTagName(key);
				sel = new Select(elemento);
				sel.selectByIndex(option);
				break;
			case NAME:
				elemento = waitAndGetByName(key);
				sel = new Select(elemento);
				sel.selectByIndex(option);
				break;
			case CSS:
				elemento = waitAndGetByCssSelector(key);
				sel = new Select(elemento);
				sel.selectByIndex(option);
				break;
			case LINK_TEXT:
				elemento = waitAndGetByLinkText(key);
				sel = new Select(elemento);
				sel.selectByIndex(option);
				break;
			case PARTIAL_LINK_TEXT:
				elemento = waitAndGetByPartialLinkText(key);
				sel = new Select(elemento);
				sel.selectByIndex(option);
				break;
		}
	}

	public void selectOptionByXpath(String key, int option) {
		waitAndSelectOption(How.XPATH, key, option);
	}

	public void selectOptionByID(String key, int option) {
		waitAndSelectOption(How.ID, key, option);
	}

	public void selectOptionByTagName(String key, int option) {
		waitAndSelectOption(How.TAG_NAME, key, option);
	}
	
	public void selectOptionByName(String key, int option) {
		waitAndSelectOption(How.NAME, key, option);
	}
	
	public void selectOptionCssSelector(String key, int option) {
		waitAndSelectOption(How.CSS, key, option);
	}
	
	public void selectOptionLinkText(String key, int option) {
		waitAndSelectOption(How.LINK_TEXT, key, option);
	}
	
	public void selectOptionPartialLinkText(String key, int option) {
		waitAndSelectOption(How.PARTIAL_LINK_TEXT, key, option);
	}
	

	///
	/// wait and sendkeys
	///

	private void waitAndSendKeys(How mode, String key, String sendKey) {
		WebElement elemento;
		switch(mode)
		{
			case XPATH:
				elemento = waitAndGetByXpath(key);
				elemento.sendKeys(sendKey);
				break;
			case ID:
				elemento = waitAndGetByID(key);
				elemento.sendKeys(sendKey);
				break;
			case TAG_NAME:
				elemento = waitAndGetByTagName(key);
				elemento.sendKeys(sendKey);
				break;
			case NAME:
				elemento = waitAndGetByName(key);
				elemento.sendKeys(sendKey);
				break;
			case CSS:
				elemento = waitAndGetByCssSelector(key);
				elemento.sendKeys(sendKey);
				break;
			case LINK_TEXT:
				elemento = waitAndGetByLinkText(key);
				elemento.sendKeys(sendKey);
				break;
			case PARTIAL_LINK_TEXT:
				elemento = waitAndGetByPartialLinkText(key);
				elemento.sendKeys(sendKey);
				break;
		}
	}

	public void sendKeysByID(String elem, String sendKey) {
		waitAndSendKeys(How.ID, elem, sendKey);
	}
	
	public void sendKeysByXpath(String elem, String sendKey) {
		waitAndSendKeys(How.XPATH, elem, sendKey);
	}
	
	public void sendKeysByTagName(String elem, String sendKey) {
		waitAndSendKeys(How.TAG_NAME, elem, sendKey);
	}

	public void sendKeysByName(String elem, String sendKey) {
		waitAndSendKeys(How.NAME, elem, sendKey);
	}
	
	public void sendKeysByCssSelector(String elem, String sendKey) {
		waitAndSendKeys(How.CSS, elem, sendKey);
	}
	
	public void sendKeysByLinkText(String elem, String sendKey) {
		waitAndSendKeys(How.LINK_TEXT, elem, sendKey);
	}
	
	public void sendKeysByPartialLinkText(String elem, String sendKey) {
		waitAndSendKeys(How.PARTIAL_LINK_TEXT, elem, sendKey);
	}
	
	///
	/// Browser and Window Methods
	///
	
	public void switchTab() {
		Actions act = new Actions(_driver);
		act.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
	}
	
	public void newTab() {
		Actions act = new Actions(_driver);
		act.keyDown(Keys.CONTROL).sendKeys("t").build().perform();
	}
	
	public void maximize() {
		_driver.manage().window().maximize();
	}
	
	///
	/// Mouse Movement Methods
	///
	
	public void dragAndDrop(How mode, String source, String target) {
		WebElement _source;
		WebElement _target;
		Actions builder = new Actions(_driver);
		switch(mode)
		{
		case XPATH:
			_source = waitAndGetByXpath(source);
			_target = waitAndGetByXpath(target);
			builder.dragAndDrop(_source, _target);
			break;
		case ID:
			_source = waitAndGetByID(source);
			_target = waitAndGetByID(target);
			builder.dragAndDrop(_source, _target);
			break;
		case TAG_NAME:
			_source = waitAndGetByTagName(source);
			_target = waitAndGetByTagName(target);
			builder.dragAndDrop(_source, _target);
			break;
		case NAME:
			_source = waitAndGetByName(source);
			_target = waitAndGetByName(target);
			builder.dragAndDrop(_source, _target);
			break;
		case CSS:
			_source = waitAndGetByCssSelector(source);
			_target = waitAndGetByCssSelector(target);
			builder.dragAndDrop(_source, _target);
			break;
		case LINK_TEXT:
			_source = waitAndGetByLinkText(source);
			_target = waitAndGetByLinkText(target);
			builder.dragAndDrop(_source, _target);
			break;
		case PARTIAL_LINK_TEXT:
			_source = waitAndGetByPartialLinkText(source);
			_target = waitAndGetByPartialLinkText(target);
			builder.dragAndDrop(_source, _target);
			break;
		}
	}
	
	///
	/// Special implementation for By.Angular
	///
	//It is necessary to have a file allowing the use of protractor in the Angular part.


	public enum ByAngularMode {
		model, binding
	}

	/*public WebElement waitAndGetAngular(String key) {
		try {
			WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ByAngular.model(key)));
			// throws a timeout exception if element not present after waiting
			// <timeoutInSeconds> seconds
			return _driver.findElement(ByAngular.model(key));
		} catch (Exception e) {
			return null;
		}
	}

	public WebElement waitAndGetByAngularModel(String key) {
		return waitAndGetAngular(key);
	}

	// function for wait and sendkey
	public void waitAndSendKeysAngularModel(String key, String sendKey) {
		waitAndSendKeysAngular(ByAngularMode.model, key, sendKey);
	}

	private void waitAndSendKeysAngular(ByAngularMode mode, String key, String sendKey) {
		WebElement element;
		if (mode == ByAngularMode.model) {
			element = waitAndGetByAngularModel(key);

			element.sendKeys(sendKey);
		}
	}*/

}