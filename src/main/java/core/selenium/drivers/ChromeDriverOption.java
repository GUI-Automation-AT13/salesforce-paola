package core.selenium.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverOption {

    /**
     * Sets the options for the Chrome Webdriver option.
     * @return Webdriver option.
     */
    public WebDriver setChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", preferences);
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }
}
