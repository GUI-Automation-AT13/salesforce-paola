package core.selenium.browser.driver;

import core.selenium.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManager extends DriverManager {

    /**
     * Sets the driver for Chrome browser.
     */
    @Override
    protected void setDriver() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        WebDriverManager.chromedriver().setup();
        driver =  new ChromeDriver(options);
    }
}
