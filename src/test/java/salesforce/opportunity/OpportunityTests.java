package salesforce.opportunity;

import api.salesforce.entities.Opportunity;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import salesforce.base.OpportunityBaseTest;
import salesforce.config.ConfigEnvVar;
import salesforce.ui.pages.LoginPage;
import salesforce.ui.pages.opportunity.CreatedOpportunity;
import salesforce.ui.pages.opportunity.NewOpportunityPage;
import salesforce.ui.pages.opportunity.OpportunityPage;

public class OpportunityTests extends OpportunityBaseTest {

    private NewOpportunityPage formOpportunity;
    private CreatedOpportunity createdForm;
    private OpportunityPage opportunityPageBack;
    SoftAssert softAssert = new SoftAssert();

    /**
     * Login and navigate to Opportunity Feature.
     */
    @BeforeMethod
    public void goToOpportunityPage() {
        String username = ConfigEnvVar.getInstance().getUserName();
        String password = ConfigEnvVar.getInstance().getPassword();
        loginPage = new LoginPage();
        homePage = loginPage.successfulLogin(username, password);
    }

    @Test
    public void testCreateOpportunityWithRequiredValuesOnly() {
        String opportunityName = "New opportunity name";
        String opportunityCloseDate = "7/31/2021";
        String opportunityStage = "Prospecting";

        opportunityPage = pageTransporter.goToOpportunityPage();
        formOpportunity = opportunityPage.openNewOpportunityForm();
        formOpportunity.setDropdown("Stage", opportunityStage);
        formOpportunity.setInputField("Name", opportunityName);
        createdForm = formOpportunity.clickSaveOpportunity();
        //Assert with Alert when creating
        softAssert.assertEquals(createdForm.getSuccessfulAlert(), "\"" + opportunityName + "\"");
        //Assert with opportunity created title
        softAssert.assertEquals(createdForm.getTitleHeader(), opportunityName);
        //Assert with Header of Opportunity created
        softAssert.assertEquals(createdForm.getHeaderString("Close Date", "lightning-formatted-text"),
                opportunityCloseDate);
        //Assert with current Stage
        softAssert.assertEquals(createdForm.getCurrentStage(), opportunityStage);
        //Assert with Details
        createdForm.clickDetails();
        softAssert.assertAll();
    }

    @Test(groups = "OpportunityAllFields")
    public void createOpportunityWithAllValues() {
        Opportunity newOpportunity = new Opportunity();
        newOpportunity.setAmount("123");
        newOpportunity.setOpportunityName("New opportunity name");
        newOpportunity.setCloseDate("7/31/2021");
        newOpportunity.setNextStep("Talking with client");
        newOpportunity.setOpportunityStage("Prospecting");
        newOpportunity.setTypeOption("New Customer");
        newOpportunity.setLeadSource("Web");
        newOpportunity.setDeliveryOption("Completed");
        newOpportunity.setProbability("20");
        newOpportunity.setOrderNumber("Order#12");
        newOpportunity.setCurrentGenerator("Number#34-TB");
        newOpportunity.setTrackingNumber("track#45-TSD");
        newOpportunity.setMainComp("Sony company");
        newOpportunity.setDescription("Description for New opportunity");
        newOpportunity.setSearchAccount(account.getName());
        newOpportunity.setSearchCampaign(campaign.getName());
        newOpportunity.setPrivate(true);

        opportunityPage = pageTransporter.goToOpportunityPage();
        formOpportunity = opportunityPage.openNewOpportunityForm();

        formOpportunity.setDropdown("Stage", newOpportunity.getOpportunityStage())
                .setDropdown("Type", newOpportunity.getTypeOption())
                .setDropdown("Lead Source", newOpportunity.getLeadSource())
                .setDropdown("Delivery/Installation Status", newOpportunity.getDeliveryOption());
        formOpportunity.setInputField("Amount", newOpportunity.getAmount().toString())
                .setInputField("Name", newOpportunity.getOpportunityName())
                .setInputField("Next Step", newOpportunity.getNextStep())
                .setInputField("Probability", newOpportunity.getProbability())
                .setInputField("OrderNumber__c", newOpportunity.getOrderNumber())
                .setInputField("CurrentGenerators__c", newOpportunity.getCurrentGenerator())
                .setInputField("TrackingNumber__c", newOpportunity.getTrackingNumber())
                .setInputField("MainCompetitors__c", newOpportunity.getMainComp())
                .setInputField("Close Date", newOpportunity.getCloseDate());
        formOpportunity.setDescription("Description", newOpportunity.getDescription());
        formOpportunity.selectCheckbox("Private", newOpportunity.isPrivate());
        formOpportunity.setSearchDown("Accounts", newOpportunity.getSearchAccount())
                .setSearchDown("Campaigns", newOpportunity.getSearchCampaign());
        createdForm = formOpportunity.clickSaveOpportunity();
        softAssert.assertEquals(createdForm.getSuccessfulAlert(), "\"" + newOpportunity.getOpportunityName() + "\"");
        softAssert.assertEquals(createdForm.getTitleHeader(), newOpportunity.getOpportunityName());
        softAssert.assertEquals(createdForm.getHeaderString("Close Date", "lightning-formatted-text"),
                newOpportunity.getCloseDate());
        softAssert.assertEquals(createdForm.getCurrentStage(), newOpportunity.getOpportunityStage());
        createdForm.clickDetails();
        softAssert.assertAll();
    }

    /**
     * Delete the created opportunity.
     */
    @AfterMethod
    public void deleteCreatedOpportunity() {
        opportunityPageBack = createdForm.deleteCreatedOpportunity();
    }
}
