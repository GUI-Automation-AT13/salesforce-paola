package salesforce.base;

import core.selenium.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import salesforce.config.ConfigEnvVar;
import salesforce.ui.PageTransporter;
import salesforce.ui.pages.HomePage;
import salesforce.ui.pages.LoginPage;
import salesforce.ui.pages.opportunity.OpportunityPage;

public class BaseTest {

    protected LoginPage loginPage;
    protected WebDriver driver;
    protected DriverManager driverManager;
    protected WebDriverWait wait;
    protected HomePage homePage;
    protected PageTransporter pageTransporter = new PageTransporter();
    protected OpportunityPage opportunityPage;


    @BeforeClass
    public void setUp() {
        driverManager = DriverManager.getInstance();
        driver = driverManager.getDriver();
        driver.get(ConfigEnvVar.getInstance().getLoginUrl());
        wait = driverManager.getWait();
    }

    @BeforeMethod
    public void goToOpportunityPage() {
        String username = ConfigEnvVar.getInstance().getUserName();
        String password = ConfigEnvVar.getInstance().getPassword();
        loginPage = new LoginPage();
        homePage = loginPage.successfulLogin(username, password);
        opportunityPage = pageTransporter.goToOpportunityPage();
    }

    @AfterClass
    public void tearDown() {
        driverManager.getDriver().quit();
        driver.quit();
    }
}
