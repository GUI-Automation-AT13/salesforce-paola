package salesforce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int WAIT_TIME = 30;

    /**
     * Sets the basic properties for driver.
     * @param selectedDriver
     */
    public BasePage(final WebDriver selectedDriver) {
        this.driver = selectedDriver;
        wait = new WebDriverWait(driver, WAIT_TIME);
        PageFactory.initElements(driver, this);
        waitForPageLoaded();
    }

    /**
     * Waits for the page to load completely.
     */
    protected abstract void waitForPageLoaded();
}
