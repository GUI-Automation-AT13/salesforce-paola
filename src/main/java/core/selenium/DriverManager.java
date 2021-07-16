package core.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import salesforce.config.ConfigEnvVar;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    static DriverManager driverManager;
    private WebDriver driver;
    private WebDriverWait wait;
    private DriverConfig driverConfig;

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
                driver = new FirefoxDriver(); break;
            default: {
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                options.setExperimentalOption("prefs", prefs);
                WebDriverManager.chromedriver().setup();
                driver =  new ChromeDriver(options);
            }
        }
    }

    private void quitDriver() {
        driver.quit();
        driverManager = null;
    }
}
