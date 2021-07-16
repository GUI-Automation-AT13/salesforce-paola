package salesforce.base;

import core.selenium.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import salesforce.config.ConfigEnvVar;
import salesforce.ui.pages.LoginPage;

public class BaseTest {

    protected LoginPage loginPage;
    protected WebDriver driver;
    protected DriverManager driverManager;

    @BeforeClass
    public void setUp() {
        driverManager = DriverManager.getInstance();
        driver = driverManager.getDriver();
        driver.get(ConfigEnvVar.getInstance().getLoginUrl());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
