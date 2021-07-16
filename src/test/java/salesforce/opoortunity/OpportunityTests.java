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

    private PageTransporter pageTransporter = new PageTransporter();
    private OpportunityPage opportunityPage;
    private HomePage homePage;
    private NewOpportunityPage formOpportunity;

    @Test
    public void testCreateCaseWithRequiredValues() {
        String username = ConfigEnvVar.getInstance().getUserName();
        String password = ConfigEnvVar.getInstance().getPassword();
        loginPage = new LoginPage();
        homePage = loginPage.successfulLogin(username, password);
        opportunityPage = pageTransporter.goToOpportunityPage();
        formOpportunity = opportunityPage.openNewOpportunityForm();
        formOpportunity.setOpportunityName("New opportunity name");
        formOpportunity.setStage();
        formOpportunity.setCloseDate("7/31/2021");
    }
}

