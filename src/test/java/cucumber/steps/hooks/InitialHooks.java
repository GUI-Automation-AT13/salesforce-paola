package cucumber.steps.hooks;

import static core.selenium.DriverManager.getInstance;

import core.selenium.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import salesforce.config.ConfigEnvVar;

public class InitialHooks {
    private WebDriver driver;

    @Before(order = 1)
    public void setUp() {
        driver = DriverManager.getInstance().getDriver();
        driver.get(ConfigEnvVar.getInstance().getLoginUrl());
    }

    @After(order = 1)
    public void tearDown() {
        getInstance().quitDriver();
    }
}
