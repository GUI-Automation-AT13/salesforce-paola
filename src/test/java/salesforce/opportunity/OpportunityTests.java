package salesforce.opportunity;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import salesforce.base.BaseTest;
import salesforce.ui.pages.opportunity.CreatedOpportunity;
import salesforce.ui.pages.opportunity.NewOpportunityPage;
import salesforce.ui.pages.opportunity.OpportunityPage;

public class OpportunityTests extends BaseTest {

    private NewOpportunityPage formOpportunity;
    private CreatedOpportunity createdForm;
    private OpportunityPage opportunityPageBack;
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void testCreateOpportunityWithRequiredValuesOnly() {
        String opportunityName = "New opportunity name";
        String opportunityCloseDate = "7/31/2021";
        String opportunityStage = "Prospecting";

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

    @Test
    public void createOpportunityWithAllValues() {
        String amount = "123";
        String opportunityName = "New opportunity name";
        String opportunityCloseDate = "7/31/2021";
        String nextStep = "Talking with client";
        String opportunityStage = "Prospecting";
        String typeOption = "New Customer";
        String leadSource = "Web";
        String deliveryOption = "Completed";
        String probability = "20";
        String orderNumber = "Order#12";
        String currentGenerator = "Number#34-TB";
        String trackingNumber = "track#45-TSD";
        String mainComp = "Sony company";
        String desc = "Description for New opportunity";
        String searchAccount = "Opportunity Account";
        String searchCampaign = "Opportunity Campaign";
        boolean isPrivate = true;

        formOpportunity = opportunityPage.openNewOpportunityForm();
        formOpportunity.setDropdown("Stage", opportunityStage)
                .setDropdown("Type", typeOption)
                .setDropdown("Lead Source", leadSource)
                .setDropdown("Delivery/Installation Status", deliveryOption);
        formOpportunity.setInputField("Amount", amount)
                .setInputField("Name", opportunityName)
                .setInputField("Next Step", nextStep)
                .setInputField("Probability", probability)
                .setInputField("OrderNumber__c", orderNumber)
                .setInputField("CurrentGenerators__c", currentGenerator)
                .setInputField("TrackingNumber__c", trackingNumber)
                .setInputField("MainCompetitors__c", mainComp);
        formOpportunity.setCloseDate(opportunityCloseDate);
        formOpportunity.setDescription(desc);
        formOpportunity.selectPrivateCheckbox(isPrivate);
        formOpportunity.setSearchDown("Accounts", searchAccount)
                .setSearchDown("Campaigns", searchCampaign);
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
        softAssert.assertEquals(createdForm.getDetailTextElement("Next Step"), nextStep);
        softAssert.assertEquals(createdForm.getDetailTextElement("Lead Source"), leadSource);
        softAssert.assertEquals(createdForm.getDetailTextElement("Type"), typeOption);
        //softAssert.assertEquals(createdForm.getDetailTextElement("Account Name"), searchAccount);
        softAssert.assertEquals(createdForm.getDetailTextElement("Close Date"), opportunityCloseDate);
        softAssert.assertEquals(createdForm.getDetailTextElement("Stage"), opportunityStage);
        softAssert.assertAll();
    }

    @AfterMethod
    public void deleteCreatedOpportunity() {
        opportunityPageBack = createdForm.deleteCreatedOpportunity();
    }
}
