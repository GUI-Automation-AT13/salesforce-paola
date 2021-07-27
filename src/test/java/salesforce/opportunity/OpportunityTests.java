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
    private final Long amount = 123L;
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
        formOpportunity.setCloseDate(opportunityCloseDate);
        createdForm = formOpportunity.clickSaveOpportunity();
        //Assert with Alert when creating
        softAssert.assertEquals(createdForm.getSuccessfulAlert(), "\"" + opportunityName + "\"");
        //Assert with opportunity created title
        softAssert.assertEquals(createdForm.getTitleHeader(), opportunityName);
        //Assert with Header of Opportunity created
        softAssert.assertEquals(createdForm.getHeaderString("Close Date"), opportunityCloseDate);
        //Assert with current Stage
        softAssert.assertEquals(createdForm.getCurrentStage(), opportunityStage);
        //Assert with Details
        createdForm.clickDetails();
        softAssert.assertEquals(createdForm.getDetailTextElement("Opportunity Name"), opportunityName);
        softAssert.assertEquals(createdForm.getDetailTextElement("Close Date"), opportunityCloseDate);
        softAssert.assertEquals(createdForm.getDetailTextElement("Stage"), opportunityStage);
        softAssert.assertAll();
    }

    @Test(groups = "OpportunityAllFields")
    public void createOpportunityWithAllValues() {
        Opportunity newOpportunity = new Opportunity();
        newOpportunity.setAmount(amount);
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
                .setInputField("MainCompetitors__c", newOpportunity.getMainComp());
        formOpportunity.setCloseDate(newOpportunity.getCloseDate());
        formOpportunity.setDescription(newOpportunity.getDescription());
        formOpportunity.selectPrivateCheckbox(newOpportunity.isPrivate());
        formOpportunity.setSearchDown("Accounts", newOpportunity.getSearchAccount())
                .setSearchDown("Campaigns", newOpportunity.getSearchCampaign());
        createdForm = formOpportunity.clickSaveOpportunity();
        //Assert with Alert when creating
        softAssert.assertEquals(createdForm.getSuccessfulAlert(), "\"" + newOpportunity.getOpportunityName() + "\"");
        //Assert with opportunity created title
        softAssert.assertEquals(createdForm.getTitleHeader(), newOpportunity.getOpportunityName());
        //Assert with Header of Opportunity created
        softAssert.assertEquals(createdForm.getHeaderString("Close Date"), newOpportunity.getCloseDate());
        //Assert with current Stage
        softAssert.assertEquals(createdForm.getCurrentStage(), newOpportunity.getOpportunityStage());
        //Assert with Details
        createdForm.clickDetails();
        softAssert.assertEquals(createdForm.getDetailTextElement("Opportunity Name"), newOpportunity.getOpportunityName());
        softAssert.assertEquals(createdForm.getDetailTextElement("Next Step"), newOpportunity.getNextStep());
        softAssert.assertEquals(createdForm.getDetailTextElement("Lead Source"), newOpportunity.getLeadSource());
        softAssert.assertEquals(createdForm.getDetailTextElement("Type"), newOpportunity.getTypeOption());
        //softAssert.assertEquals(createdForm.getDetailTextElement("Account Name"), searchAccount);
        softAssert.assertEquals(createdForm.getDetailTextElement("Close Date"), newOpportunity.getCloseDate());
        softAssert.assertEquals(createdForm.getDetailTextElement("Stage"), newOpportunity.getOpportunityStage());
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
