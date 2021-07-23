package salesforce.ui.pages;

import core.selenium.DriverManager;
import core.selenium.WebElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebElementActions webElementAction;
    protected DriverManager driverManager;

    /**
     * Sets the basic properties for driver.
     */
    public BasePage() {
        driverManager = DriverManager.getInstance();
        driver = driverManager.getDriver();
        wait = driverManager.getWait();
        webElementAction = new WebElementActions();
        PageFactory.initElements(driver, this);
        waitForPageLoaded();
    }

    /**
     * Waits for the page to load completely.
     */
    protected abstract void waitForPageLoaded();
}
