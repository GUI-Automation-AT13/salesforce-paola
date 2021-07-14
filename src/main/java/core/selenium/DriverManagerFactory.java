package core.selenium;

import core.selenium.browser.driver.ChromeDriverManager;
import core.selenium.browser.driver.FirefoxDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DriverManagerFactory {

    /**
     * Defines the type of Web driver.
     * @param type gets the type for web driver.
     * @return returns the driver type.
     */
    public static DriverManager getManager(final DriverManagerType type) {

        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return driverManager;

    }
}
