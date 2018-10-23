package org.superdriver;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.internal.ArrayComparisonFailure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.How;


/**
 * 
 * @author Adrian Nicolas Conesa, Pedro Luís Mateo Navarro, ...
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
	public boolean elementPresent (By selector) {
		WebElement element = _sd.getElement(selector) ;
		
		try {
			Assert.assertNotNull(element);
			return true;
		}
		catch (Exception e) {
			return false;
		}
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
			FileUtils.copyFile(src, new File("/superdriver/AssertScreenshots" + System.currentTimeMillis() + ".png"));                             
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
	public void assertTrue(String message, boolean condition) {
		try {
			org.junit.Assert.assertTrue(message, condition);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
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
	public void assertTrue(boolean condition) {
		try {
			org.junit.Assert.assertTrue(condition);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
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
	public void assertFalse(String message, boolean condition) {
		try {
			org.junit.Assert.assertFalse(message, condition);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
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
	public void assertFalse(boolean condition) {
		try {
			org.junit.Assert.assertFalse(condition);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
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
	public void fail(String message) {
		try {
			org.junit.Assert.fail(message);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
			} finally {
				throw err;
			}
		}
	}
	
	/**
	 * Fails a test with no message.
	 */
	public void fail() {
		try {
			org.junit.Assert.fail();
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
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
	public void assertEquals(String message, Object expected, Object actual) {
		try {
			org.junit.Assert.assertEquals(message, expected, actual);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
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
	public void assertEquals(Object expected, Object actual) {
		try {
			org.junit.Assert.assertEquals(expected, actual);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
			} finally {
				throw err;
			}
		}
	}
	
	
	public void assertArrayEquals(String message, Object[] expecteds, Object[] actuals) throws ArrayComparisonFailure {
		try {
			org.junit.Assert.assertArrayEquals(message, expecteds, actuals);
		} catch (AssertionError err) {
			try{

				SuperDriver.captureScreenshot();
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
	 * @param expected array or multi-dimensional array with expected values
	 * @param actuals Object array or multi-dimensional array with actual values
	 */
	public void assertArrayEquals(Object[] expecteds, Object[] actuals) throws ArrayComparisonFailure {
		try {
			org.junit.Assert.assertArrayEquals(expecteds, actuals);
		} catch (AssertionError err) {
			try{

				SuperDriver.captureScreenshot();
			} finally {
				throw err;
			}
		}
	}
	
	public void assertNotNull(String message, Object object) {
		try {
			org.junit.Assert.assertNotNull(message, object);
		} catch (AssertionError err) {
			try{

				SuperDriver.captureScreenshot();
			} finally {
				throw err;
			}
		}
	}
	
	public void assertNotNull(Object object) {
		try {
			org.junit.Assert.assertNotNull(object);
		} catch (AssertionError err) {
			try{

				SuperDriver.captureScreenshot();
			} finally {
				throw err;
			}
		}
	}
	
	protected void assertNull(String message, Object object) {
		try {
			org.junit.Assert.assertNull(message, object);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
			} finally {
				throw err;
			}
		}
	}
	
	protected void assertNull(Object object) {
		try {
			org.junit.Assert.assertNull(object);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
			} finally {
				throw err;
			}
		}
	}
	
	public void assertSame(String message, Object expected, Object actual) {
		try {
			org.junit.Assert.assertSame(message, expected, actual);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
			} finally {
				throw err;
			}
		}
	}
	
	public void assertSame(Object expected, Object actual) {
		try {
			org.junit.Assert.assertSame(expected, actual);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
			} finally {
				throw err;
			}
		}
	}
	
	protected void assertNotSame(String message, Object unexpected, Object actual) {
		try {
			org.junit.Assert.assertNotSame(message, unexpected, actual);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
			} finally {
				throw err;
			}
		}
	}
	
	protected void assertNotSame(Object unexpected, Object actual) {
		try {
			org.junit.Assert.assertNotSame(unexpected, actual);
		} catch (AssertionError err) {
			try{
				SuperDriver.captureScreenshot();
			} finally {
				throw err;
			}
		}
	}
	
	
}