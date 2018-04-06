package org.superdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


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
	
	public WebElement waitAndGetByClass(String key) {
		ByClassName objetoBusqueda = new ByClassName(key);
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
				elemento = waitAndGetByID(key);
				elemento.click();
				break;
			case TAG_NAME:
				elemento = waitAndGetByTagName(key);
				elemento.click();
				break;
			case NAME:
				elemento = waitAndGetByName(key);
				elemento.click();
				break;
			case CSS:
				elemento = waitAndGetByCssSelector(key);
				elemento.click();
				break;
			case LINK_TEXT:
				elemento = waitAndGetByLinkText(key);
				elemento.click();
				break;
			case PARTIAL_LINK_TEXT:
				elemento = waitAndGetByPartialLinkText(key);
				elemento.click();
				break;
			case CLASS_NAME:
				elemento = waitAndGetByClass(key);
				elemento.click();
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
			case CLASS_NAME:
				elemento = waitAndGetByClass(key);
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
	
	public void selectOptionByClass(String key, int option) {
		waitAndSelectOption(How.CLASS_NAME, key, option);
	}
	

	///
	/// wait and send keys
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
			case CLASS_NAME:
				elemento = waitAndGetByClass(key);
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
	
	public void sendKeysByClass(String elem, String sendKey) {
		waitAndSendKeys(How.CLASS_NAME, elem, sendKey);
	}
	
	///
	/// Browser and Window Methods
	///
	
	// Change between tabs
	public void switchTab() {
		_driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL,Keys.TAB));
	}
	
	// Create a new empty tab
	public void newTab() {
		_driver.findElement(By.cssSelector("body")).sendKeys(Keys.chord(Keys.CONTROL,"t"));
	}
	
	// Maximize window
	public void maximize() {
		_driver.manage().window().maximize();
	}
	
	// Switch to last window and maximize it
	
	public void switchToNewWindow() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
		wait.wait(WAIT_TIMEOUT);
		// _driver.close(); uncomment if you need to close last window
		for(String window : _driver.getWindowHandles()) {
			_driver.switchTo().window(window);
		}
		this.maximize();
	}
	
	// Switch to main window and maximize it
	
	public void switchToMainWindow() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIMEOUT);
		wait.wait(WAIT_TIMEOUT);
		_driver.switchTo().window(_driver.getWindowHandles().iterator().next());
	}
	
	// Load a cookie inserting cookie name and token
	
	public void loadCookie(String name, String token) {
		Cookie cookie = new Cookie(name, token);
		_driver.manage().addCookie(cookie);
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
//		org.openqa.selenium.interactions.Action dragAndDrop = builder.clickAndHold(_source)
//				.moveToElement(_target)
//				.release(_source)
//				.build();
		switch(mode)
		{
		case XPATH:
			_source = waitAndGetByXpath(source);
			_target = waitAndGetByXpath(target);
			dragAndDrop.perform();
			break;
		case ID:
			_source = waitAndGetByID(source);
			_target = waitAndGetByID(target);
			dragAndDrop.perform();
			break;
		case TAG_NAME:
			_source = waitAndGetByTagName(source);
			_target = waitAndGetByTagName(target);
			dragAndDrop.perform();
			break;
		case NAME:
			_source = waitAndGetByName(source);
			_target = waitAndGetByName(target);
			dragAndDrop.perform();
			break;
		case CSS:
			_source = waitAndGetByCssSelector(source);
			_target = waitAndGetByCssSelector(target);
			dragAndDrop.perform();
			break;
		case LINK_TEXT:
			_source = waitAndGetByLinkText(source);
			_target = waitAndGetByLinkText(target);
			dragAndDrop.perform();
			break;
		case PARTIAL_LINK_TEXT:
			_source = waitAndGetByPartialLinkText(source);
			_target = waitAndGetByPartialLinkText(target);
			dragAndDrop.perform();
			break;
		case CLASS_NAME:
			_source = waitAndGetByClass(source);
			_target = waitAndGetByClass(target);
			dragAndDrop.perform();
			break;
		}
	}
	
	public void waitAndMoveTo(How mode, String where) {
		WebElement _where;
		Actions builder = new Actions(_driver);
		switch(mode) {
			case XPATH:
				_where = waitAndGetByXpath(where);
				builder.moveToElement(_where).perform();
				break;
			case ID:
				_where = waitAndGetByID(where);
				builder.moveToElement(_where).perform();
				break;
			case TAG_NAME:
				_where = waitAndGetByTagName(where);
				builder.moveToElement(_where).perform();
				break;
			case NAME:
				_where = waitAndGetByName(where);
				builder.moveToElement(_where).perform();
				break;
			case CSS:
				_where = waitAndGetByCssSelector(where);
				builder.moveToElement(_where).perform();
				break;
			case LINK_TEXT:
				_where = waitAndGetByLinkText(where);
				builder.moveToElement(_where).perform();
				break;
			case PARTIAL_LINK_TEXT:
				_where = waitAndGetByPartialLinkText(where);
				builder.moveToElement(_where).perform();
				break;
			case CLASS_NAME:
				_where = waitAndGetByClass(where);
				builder.moveToElement(_where).perform();
				break;
		}
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
	
	public void moveBetweenYAxis(How mode, WebElement from, WebElement where) {
		Point location;
		int x,y;
		Actions builder = new Actions(_driver);
		location = from.getLocation();
		x= location.getX();
		location = where.getLocation();
		y= location.getY();
		builder.moveByOffset(x,y).moveToElement(where).perform();
	}
	
	public void waitAndMoveBetweenXAxis(How mode, String from, String where) {
		WebElement _from, _where;
		
		switch(mode){
			case XPATH:
				_where = waitAndGetByXpath(where);
				_from = waitAndGetByXpath(from);
				this.moveBetweenXAxis(mode, _from, _where);
				break;
			case ID:
				_where = waitAndGetByID(where);
				_from = waitAndGetByID(from);
				this.moveBetweenXAxis(mode, _from, _where);
				break;
			case TAG_NAME:
				_where = waitAndGetByTagName(where);
				_from = waitAndGetByTagName(from);
				this.moveBetweenXAxis(mode, _from, _where);
				break;
			case NAME:
				_where = waitAndGetByName(where);
				_from = waitAndGetByName(from);
				this.moveBetweenXAxis(mode, _from, _where);
				break;
			case CSS:
				_where = waitAndGetByCssSelector(where);
				_from = waitAndGetByCssSelector(from);
				this.moveBetweenXAxis(mode, _from, _where);
				break;
			case LINK_TEXT:
				_where = waitAndGetByLinkText(where);
				_from = waitAndGetByLinkText(from);
				this.moveBetweenXAxis(mode, _from, _where);
				break;
			case PARTIAL_LINK_TEXT:
				_where = waitAndGetByPartialLinkText(where);
				_from = waitAndGetByPartialLinkText(from);
				this.moveBetweenXAxis(mode, _from, _where);
				break;
			case CLASS_NAME:
				_where = waitAndGetByClass(where);
				_from = waitAndGetByClass(from);
				this.moveBetweenXAxis(mode, _from, _where);
				break;
		}
	}
	
	public void waitAndMoveBetweenYAxis(How mode, String from, String where) {
		WebElement _from, _where;
		
		switch(mode){
			case XPATH:
				_where = waitAndGetByXpath(where);
				_from = waitAndGetByXpath(from);
				this.moveBetweenYAxis(mode, _from, _where);
				break;
			case ID:
				_where = waitAndGetByID(where);
				_from = waitAndGetByID(from);
				this.moveBetweenYAxis(mode, _from, _where);
				break;
			case TAG_NAME:
				_where = waitAndGetByTagName(where);
				_from = waitAndGetByTagName(from);
				this.moveBetweenYAxis(mode, _from, _where);
				break;
			case NAME:
				_where = waitAndGetByName(where);
				_from = waitAndGetByName(from);
				this.moveBetweenYAxis(mode, _from, _where);
				break;
			case CSS:
				_where = waitAndGetByCssSelector(where);
				_from = waitAndGetByCssSelector(from);
				this.moveBetweenYAxis(mode, _from, _where);
				break;
			case LINK_TEXT:
				_where = waitAndGetByLinkText(where);
				_from = waitAndGetByLinkText(from);
				this.moveBetweenYAxis(mode, _from, _where);
				break;
			case PARTIAL_LINK_TEXT:
				_where = waitAndGetByPartialLinkText(where);
				_from = waitAndGetByPartialLinkText(from);
				this.moveBetweenYAxis(mode, _from, _where);
				break;
			case CLASS_NAME:
				_where = waitAndGetByClass(where);
				_from = waitAndGetByClass(from);
				this.moveBetweenYAxis(mode, _from, _where);
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
