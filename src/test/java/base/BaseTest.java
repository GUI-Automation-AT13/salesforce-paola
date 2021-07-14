package base;

import core.selenium.DriverManager;
import core.selenium.DriverManagerFactory;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import salesforce.pages.LoginPage;
import static core.config.EnvConfiguration.envVariables;

public class BaseTest {

    protected LoginPage loginPage;
    protected DriverManager driverManager;
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int WAIT_TIME = 30;

    @BeforeClass
    public void setUp() {
        driverManager = DriverManagerFactory.getManager(DriverManagerType.FIREFOX);
        driver = driverManager.getDriver();
        driver.get(envVariables("LOGIN_URL"));
        wait = new WebDriverWait(driver, WAIT_TIME);
    }

    @AfterClass
    public void tearDown() {
        driverManager.quitDriver();
    }
}
