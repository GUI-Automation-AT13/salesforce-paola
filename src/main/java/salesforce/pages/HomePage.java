package salesforce.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

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
