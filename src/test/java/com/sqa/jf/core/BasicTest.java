package com.sqa.jf.core;

import java.util.concurrent.*;

import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;

public class BasicTest {
	private static String baseURL = "http://mtv.com";
	private static WebDriver driver;
	private static Logger log = Logger.getLogger(BasicTest.class);

	/**
	 * @return the baseURL
	 */
	public static String getBaseURL() {
		return baseURL;
	}

	/**
	 * @return the driver
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	@BeforeClass(enabled = false)
	public static void setupChrome() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseURL);
	}

	@BeforeClass(enabled = true)
	public static void setupFirefox() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseURL);
	}

	@BeforeClass(enabled = false)
	public static void setupNewFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		driver = new MarionetteDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseURL);
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	private boolean acceptNextAlert = true;

	/**
	 * Constructor
	 */
	public BasicTest(String baseURL) {
		super();
		BasicTest.baseURL = baseURL;
	}

	@BeforeMethod
	public void setupTest() {
		// Delete all saved cookies
		getDriver().manage().deleteAllCookies();
		// Go to base URL
		getDriver().get(getBaseURL());

	}

	@SuppressWarnings("unused")
	protected String closeAlertAndGetItsText() {
		try {
			Alert alert = this.driver.switchTo().alert();
			String alertText = alert.getText();
			if (this.acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			this.acceptNextAlert = true;
		}
	}

	@SuppressWarnings("unused")
	protected boolean isAlertPresent() {
		try {
			this.driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	@SuppressWarnings("unused")
	protected boolean isElementPresent(By by) {
		try {
			this.driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
