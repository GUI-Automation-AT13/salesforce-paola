package core.selenium;

import core.selenium.drivers.ChromeDriverOption;
import core.selenium.drivers.FirefoxDriverOption;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import salesforce.config.ConfigEnvVar;

public final class DriverManager {

    static DriverManager driverManager;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected DriverConfig driverConfig;

    private DriverManager() {
        driverConfig = DriverConfig.getInstance();
        setDriverManagerType();
        wait = new WebDriverWait(driver, driverConfig.getExplicitWaitTime());
        driver.manage().window().maximize();
    }

    /**
     * Controls initializing of driverManager.
     *
     * @return a DriverManager entity.
     */
    public static DriverManager getInstance() {
        if (driverManager == null) {
            driverManager = new DriverManager();
        }
        return driverManager;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    private void setDriverManagerType() {
        String browser = ConfigEnvVar.getInstance().getBrowser();
        DriverManagerType driverManagerType = DriverManagerType.valueOf(browser);
        WebDriverManager.getInstance(driverManagerType).setup();
        switch (driverConfig.getBrowser()) {
            case "Firefox":
                driver = new FirefoxDriverOption().setFirefoxDriver(); break;
            default: {
                driver = new ChromeDriverOption().setChromeDriver();
            }
        }
    }

    private void quitDriver() {
        driver.quit();
        driverManager = null;
    }
}
