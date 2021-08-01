package cucumber.steps.hooks;

import core.selenium.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import salesforce.config.ConfigEnvVar;

public class InitialHooks {
    public WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.getInstance().getDriver();
        driver.get(ConfigEnvVar.getInstance().getLoginUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
