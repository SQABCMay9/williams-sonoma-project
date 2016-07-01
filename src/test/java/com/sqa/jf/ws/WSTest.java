/**
 *   File Name: WSTest.java<br>
 *
 *   LastName, FirstName<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Jun 29, 2016
 *
 */

package com.sqa.jf.ws;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import com.sqa.jf.core.*;

/**
 * WSTest //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author LastName, FirstName
 * @version 1.0.0
 * @since 1.0
 *
 */
public class WSTest extends BasicTest {

	private static boolean popupRemoved = false;

	/**
	 * @param baseURL
	 */
	public WSTest() {
		super("http://williams-sonoma.com");
	}

	public void checkForPopupAndClose() {
		if (!popupRemoved) {
			getLog().info("Checking for Pop-up on page " + getDriver().getTitle());

			if (isPopupPresent(By.className("overlayCloseButton"))) {
				getLog().warn("Pop-up detected and will be removed.");
				WebElement popup = getDriver().findElement(By.className("overlayCloseButton"));
				popup.click();
				popupRemoved = true;
			} else {
				getLog().info("Pop-up not detected and can not be removed.");
			}
		}
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "Cookware", "Saucepans", "Calphalon Elite Nonstick 3-Piece Fry Pan", 1 },
				{ "Cutlery", "Cheese Knives", "Boska Cheese Slicer", 2 },
				{ "Bakeware", "Mixing Bowls", "Joseph Joseph Nest 100 Food Prep Set ", 3 },
				{ "Tabletop & Bar", "Plates", "Ikat Melamine Dinner Plates, Set of 4", 1 },
				{ "Bakeware", "Measuring Cups & Spoons", "Flex-It Silicone Measuring Cup Set", 3 },
				{ "Tabletop & Bar", "Baskets, Bowls & Trays", "Double-Wall Insulated Bowl", 1 },
				{ "Outdoor", "Beekeeping", "Honey Extracting Suite", 2 } };
	}

	public boolean isPopupPresent(By by) {
		try {
			WebElement element = (new WebDriverWait(getDriver(), 120))
					.until(ExpectedConditions.elementToBeClickable(by));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@BeforeMethod
	public void setupWS() {
		popupRemoved = false;
	}

	@Test(dataProvider = "getData")
	public void wsTest(String menuItemText, String subItemText, String item, int quanity) {
		getLog().warn("-----------------------------------------------------");
		getLog().warn("Starting test at " + getDriver().getTitle());

		By menuItemBy = By.linkText(menuItemText);
		By subItemBy = By.linkText(subItemText);

		checkForPopupAndClose();
		WebElement menuItem = (new WebDriverWait(getDriver(), 20))
				.until(ExpectedConditions.visibilityOfElementLocated(menuItemBy));
		menuItem.click();
		getLog().warn("Click on page navigating to: " + getDriver().getTitle());
		checkForPopupAndClose();
		WebElement subItem = (new WebDriverWait(getDriver(), 20))
				.until(ExpectedConditions.visibilityOfElementLocated(subItemBy));
		subItem.click();
		getLog().warn("Click on page navigating to: " + getDriver().getTitle());
		checkForPopupAndClose();
		getLog().warn("Finished on page " + getDriver().getTitle());
		getLog().warn("-----------------------------------------------------");
	}

}
