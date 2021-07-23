package salesforce.base;

import core.selenium.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import salesforce.config.ConfigEnvVar;
import salesforce.ui.pages.LoginPage;

public class BaseTest {

    protected LoginPage loginPage;
    protected WebDriver driver;
    protected DriverManager driverManager;
    protected WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driverManager = DriverManager.getInstance();
        driver = driverManager.getDriver();
        driver.get(ConfigEnvVar.getInstance().getLoginUrl());
        wait = driverManager.getWait();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
