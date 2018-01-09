package stepDefinition;

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

public class SuperDriver {

	// Clase Superdriver que aumenta las funcionalidades del driver de Selenium
	private RemoteWebDriver _driver;
	private static final int WAIT_TIMEOUT = 10;

	public SuperDriver(RemoteWebDriver driver) {

		_driver = driver;
		// Constructor que recibe como argumento el driver y crea un objeto de tipo
		// SuperDriver
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
	// Usando los metodos del driver, busca un elemento (key) mediante un patron (how)
	// Todos los metodos devuelven un objeto de tipo WebElement

	public WebElement waitAndGetByXpath(String key) {

		ByXPath objetoBusqueda = new ByXPath(key);
		return waitAndGet(objetoBusqueda);

		// Usando los metodos del driver, busca un elemento por XPATH (key) y lo
		// devuelve.

	}

	public WebElement waitAndGetById(String key) {
		ById objetoBusqueda = new ById(key);
		return waitAndGet(objetoBusqueda);

		// Usando los metodos del driver, busca un elemento por ID (key) y lo devuelve.

	}

	public WebElement waitAndGetByTagName(String key) {

		ByTagName objetoBusqueda = new ByTagName(key);
		return waitAndGet(objetoBusqueda);
		// Usando los metodos del driver, busca un elemento por XPATH (key) y lo
		// devuelve.

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

	public void waitAndClickByXpath(String key) {
		waitAndClick(How.XPATH, key);
	}

	public void waitAndClickByID(String key) {
		waitAndClick(How.ID, key);
	}

	public void waitAndClickByTagName(String key) {
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

	public void waitAndSelectOptionByID(String key, int option) {
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

	public void waitAndSendKeysByID(String key, String sendKey) {
		waitAndSendKeys(How.ID, key, sendKey);
	}

}
