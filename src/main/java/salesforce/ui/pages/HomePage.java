package salesforce.ui.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private static final By CREATE_BTN_BY = By.cssSelector("div[class*='CreateMenu']");

    /**
     * Goes to the Homepage after Login.
     */
    public HomePage() {
    }

    @Override
    protected void waitForPageLoaded() {

    }
}
