package org.superdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
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
	/// Wait and get
	///

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

	public WebElement waitAndGetById(String key) {
		ById objetoBusqueda = new ById(key);
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
		if (mode == How.XPATH) {
			elemento = waitAndGetByXpath(key);
			elemento.click();
		}
		if (mode == How.ID) {
			elemento = waitAndGetById(key);
			elemento.click();
		}
		if (mode == How.TAG_NAME) {
			elemento = waitAndGetByTagName(key);
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

	///
	/// Wait and Select option
	///

	public void waitAndSelectOption(How mode, String key, int option) {

		WebElement elemento;

		if (mode == How.XPATH) {
			elemento = waitAndGetByXpath(key);
			Select sel = new Select(elemento);
			sel.selectByIndex(option);
		}
		if (mode == How.ID) {
			elemento = waitAndGetById(key);
			Select sel = new Select(elemento);
			sel.selectByIndex(option);
		}
		if (mode == How.TAG_NAME) {
			elemento = waitAndGetByTagName(key);
			Select sel = new Select(elemento);
			sel.selectByIndex(option);
		}
	}

	public void selectOptionByID(String key, int option) {
		waitAndSelectOption(How.ID, key, option);
	}

	///
	/// wait and sendkey
	///

	private void waitAndSendKeys(How mode, String key, String sendKey) {
		WebElement elemento;
		if (mode == How.XPATH) {
			elemento = waitAndGetByXpath(key);
			elemento.sendKeys(sendKey);
		}
		if (mode == How.ID) {
			elemento = waitAndGetById(key);
			elemento.sendKeys(sendKey);
		}
		if (mode == How.TAG_NAME) {
			elemento = waitAndGetByTagName(key);
			elemento.sendKeys(sendKey);
		}
	}

	public void sendKeysByID(String elem, String sendKey) {
		waitAndSendKeys(How.ID, elem, sendKey);
	}

	///
	/// Special implementaion for By.Angular
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
