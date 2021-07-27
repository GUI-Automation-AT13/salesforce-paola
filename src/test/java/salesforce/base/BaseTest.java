package salesforce.base;

import core.selenium.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import salesforce.config.ConfigEnvVar;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getInstance().getDriver();
        driver.get(ConfigEnvVar.getInstance().getLoginUrl());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
