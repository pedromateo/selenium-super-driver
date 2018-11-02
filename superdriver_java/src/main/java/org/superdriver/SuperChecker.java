package org.superdriver;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

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
	public void assertElementPresent (By selector) {
		try {
			WebElement element = _sd.getElement(selector) ;
			Assert.assertNotNull(element);
		}
		catch (Exception e) {
			Assert.fail("Exception caught while checking presence of element " + selector.toString());
		}
	}

	/**
	 * Check the presence of a WebElement.
	 * @param mode
	 * @param elem
	 * @return boolean
	 */
	public void assertElementDisplayed (By selector) {
		try {
			Assert.assertTrue(_sd.isElementDisplayed(selector));
		}
		catch (Exception e) {
			Assert.fail("Exception caught while checking display of element " + selector.toString());
		}
	}

	/**
	 * Gets a screenshot.
	 * @param path
	 * @return void
	 * @throws AWTException 
	 */
	private static void captureScreenshot(String path) throws AWTException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
		Calendar now = Calendar.getInstance();
		Robot robot = new Robot();
		BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		try {
			ImageIO.write(screenShot, "JPG", new File(path + formatter.format(now.getTime()) + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(path + formatter.format(now.getTime()) + ".jpg");
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
			_takeScreenshot();
			org.junit.Assert.assertTrue(message, condition);
		} catch (AssertionError err) {
			try{
				_takeScreenshot();
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
				_takeScreenshot();
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
				_takeScreenshot();
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
				_takeScreenshot();
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
				_takeScreenshot();
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
				_takeScreenshot();
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
				_takeScreenshot();
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
				_takeScreenshot();
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

				_takeScreenshot();
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

				_takeScreenshot();
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

				_takeScreenshot();
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

				_takeScreenshot();
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
				_takeScreenshot();
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
				_takeScreenshot();
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
				_takeScreenshot();
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
				_takeScreenshot();
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
				_takeScreenshot();
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
				_takeScreenshot();
			} finally {
				throw err;
			}
		}
	}

	protected static boolean screenshot_enabled = false;
	private static String SCREENSHOT_PATH = "";
	private final static String SCREENSHOT_TAG = "[Screenshot]: ";

	public SuperChecker enableScreenshot(String path) {
		screenshot_enabled = true;
		SCREENSHOT_PATH = path;
		return this;
	}

	public SuperChecker disableScreenshot() {
		screenshot_enabled = false;
		SCREENSHOT_PATH = "";
		return this;
	}

	private static void _takeScreenshot() {
		if (screenshot_enabled)
			try {
				if (Files.isDirectory(Paths.get(SCREENSHOT_PATH))) {
					captureScreenshot(SCREENSHOT_PATH);
				}
				else {
					new File(SCREENSHOT_PATH).mkdirs();
					captureScreenshot(SCREENSHOT_PATH);
				}
			} catch (AWTException e) {
				System.out.println("Error taking the Screenshot");
				e.printStackTrace();
			}
	}

}