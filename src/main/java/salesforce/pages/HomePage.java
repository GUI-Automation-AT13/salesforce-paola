package salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By CREATE_BTN_BY = By.cssSelector("div[class*='CreateMenu']");

    /**
     * Goes to the Homepage after Login.
     * @param driver the webdriver selected.
     */
    public HomePage(final WebDriver driver) {
        super(driver);
    }

    @Override
    protected void waitForPageLoaded() {

    }
}
