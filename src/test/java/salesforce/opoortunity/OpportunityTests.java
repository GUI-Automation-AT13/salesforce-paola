package salesforce.opoortunity;

import org.testng.annotations.Test;
import salesforce.base.BaseTest;
import salesforce.config.ConfigEnvVar;
import salesforce.ui.PageTransporter;
import salesforce.ui.pages.HomePage;
import salesforce.ui.pages.LoginPage;
import salesforce.ui.pages.opportunity.NewOpportunityPage;
import salesforce.ui.pages.opportunity.OpportunityPage;

public class OpportunityTests extends BaseTest {

    private OpportunityPage opportunityPage;
    private NewOpportunityPage formOpportunity;
    private HomePage homePage;
    private PageTransporter pageTransporter = new PageTransporter();

    @Test
    public void testCreateOpportunityWithRequiredValuesOnly() {
        String username = ConfigEnvVar.getInstance().getUserName();
        String password = ConfigEnvVar.getInstance().getPassword();
        loginPage = new LoginPage();
        homePage = loginPage.successfulLogin(username, password);
        opportunityPage = pageTransporter.goToOpportunityPage();
        formOpportunity = opportunityPage.openNewOpportunityForm();
        formOpportunity.setOpportunityName("New opportunity name");
        formOpportunity.setCloseDate("7/31/2021");
    }

    @Test
    public void createOpportunityWithAllValues() {
        String username = ConfigEnvVar.getInstance().getUserName();
        String password = ConfigEnvVar.getInstance().getPassword();
        loginPage = new LoginPage();
        homePage = loginPage.successfulLogin(username, password);
        opportunityPage = pageTransporter.goToOpportunityPage();
        formOpportunity = opportunityPage.openNewOpportunityForm();
        formOpportunity.setOpportunityName("New opportunity name");
        formOpportunity.setCloseDate("7/31/2021");
        formOpportunity.setAmount("14,243.45");
        formOpportunity.setNextStep("Talking with client");
        formOpportunity.setOrderNumber("Order#12");
        formOpportunity.setCurrentGenerator("Number#34-TB");
        formOpportunity.setTrackingNumber("track#45-TSD");
        formOpportunity.setMainCompetitor("Sony company");
        formOpportunity.setDescription("Creating a new Opportunity with all values");
        formOpportunity.setProbability("20");
    }
}

