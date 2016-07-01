package com.sqa.jf.core;

import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class BasicTest {
	private static String baseURL;
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

	@BeforeClass(enabled = true)
	public static void setupChrome() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeClass(enabled = false)
	public static void setupFirefox() {
		driver = new FirefoxDriver();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeClass(enabled = false)
	public static void setupNewFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		driver = new MarionetteDriver();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
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
		// Go to base URL
		getDriver().get(getBaseURL());
		// Delete all saved cookies
		// getDriver().manage().deleteAllCookies();
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
			WebElement element = (new WebDriverWait(getDriver(), 20))
					.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

}
