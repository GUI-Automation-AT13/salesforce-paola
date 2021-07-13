package core.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {

    /**
     * Sets the Driver for Firefox browser.
     */
    @Override
    protected void setDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver =  new FirefoxDriver();
    }
}
