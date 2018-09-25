package org.superdriver;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.internal.ArrayComparisonFailure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.How;


/**
 * 
 * @author Adrian Nicolas Conesa,...
 *
 */
@SuppressWarnings({"finally", "unused", "incomplete-switch"})
public class SuperChecker {
	private SuperDriver _sd;
	private Assert _checker;
	private SuperFilter _sf;
	
	/**
	 * Constructor
	 * @param sd
	 */
	public SuperChecker(SuperDriver sd) {
		_sd = sd;
	}
	/**
	 * Check the presence of a WebElement.
	 * @param mode
	 * @param elem
	 * @return boolean
	 */
	public boolean elementPresent (How mode, String elem) {
		WebElement elemento;
		switch(mode) {
		case XPATH:
			elemento = _sd.getByXpath(elem);
			break;
		case ID:
			elemento = _sd.getByID(elem);
			break;
		case TAG_NAME:
			elemento = _sd.getByTagName(elem);
			break;
		case NAME:
			elemento = _sd.getByName(elem);
			break;
		case CSS:
			elemento = _sd.getByCssSelector(elem);
			break;
		case LINK_TEXT:
			elemento = _sd.getByLinkText(elem);
			break;
		case PARTIAL_LINK_TEXT:
			elemento = _sd.getByPartialLinkText(elem);
			break;
		case CLASS_NAME:
			elemento = _sd.getByClass(elem);
		}
		try {
			Assert.assertNotNull(elem);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	/**
	 * Check the presence of a WebElement by Xpath.
	 * @param mode
	 * @param elem
	 * @return boolean
	 */
	public boolean elementPresentByXpath(String elem) {
		return elementPresent(How.XPATH, elem);
	}
	/**
	 * Check the presence of a WebElement by Id.
	 * @param mode
	 * @param elem
	 * @return boolean
	 */
	public boolean elementPresentByID(String elem) {
		return elementPresent(How.ID, elem);
	}
	/**
	 * Check the presence of a WebElement by TagName.
	 * @param mode
	 * @param elem
	 * @return boolean
	 */
	public boolean elementPresentByTagName(String elem) {
		return elementPresent(How.TAG_NAME, elem);
	}
	/**
	 * Check the presence of a WebElement by Name.
	 * @param mode
	 * @param elem
	 * @return boolean
	 */
	public boolean elementPresentByName(String elem) {
		return elementPresent(How.NAME, elem);
	}
	/**
	 * Check the presence of a WebElement by Css.
	 * @param mode
	 * @param elem
	 * @return boolean
	 */
	public boolean elementPresentByCss(String elem) {
		return elementPresent(How.CSS, elem);
	}
	/**
	 * Check the presence of a WebElement by LinkText.
	 * @param mode
	 * @param elem
	 * @return boolean
	 */
	public boolean elementPresentByLinkText(String elem) {
		return elementPresent(How.LINK_TEXT, elem);
	}
	/**
	 * Check the presence of a WebElement by PartialLinkText.
	 * @param mode
	 * @param elem
	 * @return boolean
	 */
	public boolean elementPresentByPartialLinkText(String elem) {
		return elementPresent(How.PARTIAL_LINK_TEXT, elem);
	}
	/**
	 * Check the presence of a WebElement by Class.
	 * @param mode
	 * @param elem
	 * @return boolean
	 */
	public boolean elementPresentByClass(String elem) {
		return elementPresent(How.CLASS_NAME, elem);
	}
	/**
	 * Gets a screenshot.
	 * @param driver
	 * @return void
	 */
	public static void captureScreenShot(WebDriver ldriver){
		// Take screenshot and store as a file format             
		File src=((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE);           
		try {
			// now copy the  screenshot to desired location using copyFile method

			FileUtils.copyFile(src, new File("C:\\Users\\SAMUEL\\Desktop\\/"+System.currentTimeMillis()+".png"));                             
		} 
		catch (IOException e){
			System.out.println(e.getMessage()); 
		}
	}
	/**
	 * Asserts that a condition is true. If it isn't it throws an
	 * {@link AssertionError} with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param condition condition to be checked
	 */
	public void assertTrue(String message, boolean condition, WebDriver ldriver) {
		try {
			org.junit.Assert.assertTrue(message, condition);
		} catch (AssertionError err) {
			try{
				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	/**
	 * Asserts that a condition is true. If it isn't it throws an
	 * {@link AssertionError} without a message.
	 *
	 * @param condition condition to be checked
	 */
	public void assertTrue(boolean condition, WebDriver ldriver) {
		try {
			org.junit.Assert.assertTrue(condition);
		} catch (AssertionError err) {
			try{
				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	/**
	 * Asserts that a condition is false. If it isn't it throws an
	 * {@link AssertionError} with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param condition condition to be checked
	 */
	public void assertFalse(String message, boolean condition, WebDriver ldriver) {
		try {
			org.junit.Assert.assertFalse(message, condition);
		} catch (AssertionError err) {
			try{
				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	/**
	 * Asserts that a condition is false. If it isn't it throws an
	 * {@link AssertionError} without a message.
	 *
	 * @param condition condition to be checked
	 */
	public void assertFalse(boolean condition, WebDriver ldriver) {
		try {
			org.junit.Assert.assertFalse(condition);
		} catch (AssertionError err) {
			try{
				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	/**
	 * Fails a test with the given message.
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @see AssertionError
	 */
	public void fail(String message, WebDriver ldriver) {
		try {
			org.junit.Assert.fail(message);
		} catch (AssertionError err) {
			try{
				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	/**
	 * Fails a test with no message.
	 */
	public void fail(WebDriver ldriver) {
		try {
			org.junit.Assert.fail();
		} catch (AssertionError err) {
			try{
				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	/**
	 * Asserts that two objects are equal. If they are not, an
	 * {@link AssertionError} is thrown with the given message. If
	 * <code>expected</code> and <code>actual</code> are <code>null</code>,
	 *
	 * @param message the identifying message for the {@link AssertionError} (<code>null</code>
	 * okay)
	 * @param expected expected value
	 * @param actual actual value
	 */
	public void assertEquals(String message, Object expected, Object actual, WebDriver ldriver) {
		try {
			org.junit.Assert.assertEquals(message, expected, actual);
		} catch (AssertionError err) {
			try{
				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	/**
	 * Asserts that two objects are equal. If they are not, an
	 * {@link AssertionError} without a message is thrown. If
	 * <code>expected</code> and <code>actual</code> are <code>null</code>,
	 * they are considered equal.
	 *
	 * @param expected expected value
	 * @param actual the value to check against <code>expected</code>
	 */
	public void assertEquals(Object expected, Object actual, WebDriver ldriver) {
		try {
			org.junit.Assert.assertEquals(expected, actual);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertArrayEquals(String message, Object[] expecteds, Object[] actuals, WebDriver ldriver) throws ArrayComparisonFailure {
		try {
			org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	/**
	 * Asserts that two object arrays are equal. If they are not, an
	 * {@link AssertionError} is thrown. If <code>expected</code> and
	 * <code>actual</code> are <code>null</code>, they are considered
	 * equal.
	 *
	 * @param expecteds Object array or array of arrays (multi-dimensional array) with
	 * expected values
	 * @param actuals Object array or array of arrays (multi-dimensional array) with
	 * actual values
	 */
	public void assertArrayEquals(Object[] expecteds, Object[] actuals, WebDriver ldriver) throws ArrayComparisonFailure {
		try {
			org.junit.Assert.assertArrayEquals(expecteds, actuals);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertArrayEquals(String message, byte[] expecteds, byte[] actuals, WebDriver ldriver) throws ArrayComparisonFailure {
		try {
			org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertArrayEquals(byte[] expecteds, byte[] actuals, WebDriver ldriver) {
		try {
			org.junit.Assert.assertArrayEquals(expecteds, actuals);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	protected void assertArrayEquals(String message, char[] expecteds, char[] actuals, WebDriver ldriver) throws ArrayComparisonFailure {
		try {
			org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertArrayEquals(char[] expecteds, char[] actuals, WebDriver ldriver) {
		try {
			org.junit.Assert.assertArrayEquals(expecteds, actuals);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertArrayEquals(String message, short[] expecteds, short[] actuals, WebDriver ldriver) throws ArrayComparisonFailure {
		try {
			org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertArrayEquals(short[] expecteds, short[] actuals, WebDriver ldriver) {
		try {
			org.junit.Assert.assertArrayEquals(expecteds, actuals);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertArrayEquals(String message, int[] expecteds, int[] actuals, WebDriver ldriver) throws ArrayComparisonFailure {
		try {
			org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertArrayEquals(int[] expecteds, int[] actuals, WebDriver ldriver) {
		try {
			org.junit.Assert.assertArrayEquals(expecteds, actuals);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertNotNull(String message, Object object, WebDriver ldriver) {
		try {
			org.junit.Assert.assertNotNull(message, object);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertNotNull(Object object, WebDriver ldriver) {
		try {
			org.junit.Assert.assertNotNull(object);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	protected void assertNull(String message, Object object, WebDriver ldriver) {
		try {
			org.junit.Assert.assertNull(message, object);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	protected void assertNull(Object object, WebDriver ldriver) {
		try {
			org.junit.Assert.assertNull(object);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertSame(String message, Object expected, Object actual, WebDriver ldriver) {
		try {
			org.junit.Assert.assertSame(message, expected, actual);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	public void assertSame(Object expected, Object actual, WebDriver ldriver) {
		try {
			org.junit.Assert.assertSame(expected, actual);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	protected void assertNotSame(String message, Object unexpected, Object actual, WebDriver ldriver) {
		try {
			org.junit.Assert.assertNotSame(message, unexpected, actual);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
	protected void assertNotSame(Object unexpected, Object actual, WebDriver ldriver) {
		try {
			org.junit.Assert.assertNotSame(unexpected, actual);
		} catch (AssertionError err) {
			try{

				captureScreenShot(ldriver);
			} finally {
				throw err;
			}
		}
	}
}