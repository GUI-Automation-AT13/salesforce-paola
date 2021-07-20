package salesforce.opportunity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import salesforce.base.BaseTest;
import salesforce.ui.pages.opportunity.CreatedOpportunity;
import salesforce.ui.pages.opportunity.NewOpportunityPage;


public class OpportunityTests extends BaseTest {

    private NewOpportunityPage formOpportunity;
    private CreatedOpportunity opportunityCreated;

    @Test
    public void testCreateOpportunityWithRequiredValuesOnly() {
        String opportunityName = "New opportunity name";
        String opportunityCloseDate = "7/31/2021";
        String opportunityStage = "Prospecting";

        SoftAssert softAssert = new SoftAssert();

        formOpportunity = opportunityPage.openNewOpportunityForm();
        formOpportunity.setStage();
        formOpportunity.setOpportunityName(opportunityName);
        formOpportunity.setCloseDate(opportunityCloseDate);
        opportunityCreated = formOpportunity.clickSaveOpportunity();
        //Assert with top menu
        softAssert.assertEquals(opportunityCreated.getOpportunityNameTitle(), opportunityName);
        softAssert.assertEquals(opportunityCreated.getCloseDate(), opportunityCloseDate);
        softAssert.assertEquals(opportunityCreated.getActiveStage(), opportunityStage);
        //Assert with generic locator
        softAssert.assertEquals(opportunityCreated.getDetailElementText("Opportunity Name"), opportunityName);
        softAssert.assertAll();
    }

    @Test
    public void createOpportunityWithAllValues() {
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

