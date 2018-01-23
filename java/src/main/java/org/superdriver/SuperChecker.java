package org.superdriver;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.remote.RemoteWebDriver;



public class SuperChecker {

	private SuperDriver _sd;
	private Assert _checker;



	public SuperChecker(SuperDriver sd) {
		_sd = sd;
	}


	public boolean assertElementPresent (String elem)
	{
		try {
			WebElement elemento = _sd.waitAndGetByXpath(elem);
			Assert.assertNotNull(elemento);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	public boolean assertTitle(String title)
	{
		try {
			String _title = this._sd.waitAndGetTitle();
			Assert.assertEquals(title, _title);
			return true;
		}catch (Exception e) {
			return false;
		}
	}


	//Assert.assertTrue(elemento.isDisplayed());
}
