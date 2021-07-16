package salesforce.ui.pages;

import core.selenium.DriverManager;
import core.selenium.WebElementActions;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebElementActions webElementAction;
    protected DriverManager driverManager;
    private static final int WAIT_TIME = 30;

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
