package cucumber.steps;

import api.salesforce.entities.Opportunity;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.utils.Translate;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;
import salesforce.config.ConfigEnvVar;
import salesforce.ui.PageTransporter;
import salesforce.ui.pages.LoginPage;
import salesforce.ui.pages.opportunity.CreatedOpportunity;
import salesforce.ui.pages.opportunity.NewOpportunityPage;
import salesforce.ui.pages.opportunity.OpportunityPage;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class OpportunitySteps {
    LoginPage loginPage = new LoginPage();
    Opportunity opportunity;
    NewOpportunityPage formOpportunity;
    CreatedOpportunity createdForm;
    SoftAssert softAssert = new SoftAssert();
    Set<String> fields;
    protected PageTransporter pageTransporter = new PageTransporter();

    @Given("I login to Salesforce site as a developer user")
    public void iLoginToSalesforceSiteAsAnUser() {
        String usernameLogin = ConfigEnvVar.getInstance().getUserName();
        String passwordLogin = ConfigEnvVar.getInstance().getPassword();
        loginPage.successfulLogin(usernameLogin, passwordLogin);
    }

    @When("I create a new Opportunity with fields")
    public void iCreateAFeatureWithFields(final Map<String, String> dataTable) throws IOException {
        opportunity = new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(dataTable), Opportunity.class);
        opportunity.setOpportunityDetailField();
        OpportunityPage opportunityPage = new OpportunityPage();
        formOpportunity = opportunityPage.openNewOpportunityForm();
        fields = dataTable.keySet();
        createdForm = formOpportunity.createNewOpportunity(fields, opportunity);
    }

    @Then("Successful message appear with Opportunity name")
    public void successfulMessageAppearAndMatches() {
        softAssert.assertEquals(createdForm.getSuccessfulAlert(), "\"" + opportunity.getOpportunityName() + "\"");
    }

    @Then("All Opportunity headers match with previous fields")
    public void headersMatchWithPreviousFields() {
        softAssert.assertEquals(createdForm.getTitleHeader(), opportunity.getOpportunityName());
        softAssert.assertEquals(createdForm.getHeaderString(
                        Translate.translateField("accountName"), "span[@class='slds-assistive-text']"),
                "Open " + opportunity.getSearchAccount() + " Preview");
        softAssert.assertEquals(createdForm.getHeaderString(
                        Translate.translateField("closeDate"), "lightning-formatted-text"),
                opportunity.getCloseDate());
        //softAssert.assertEquals(createdForm.getHeaderString(
        // Translate.translateField("amount"), "lightning-formatted-text"),
        // opportunity.getAmount() + " ¤");
        softAssert.assertEquals(createdForm.getCurrentStage(), opportunity.getOpportunityStage());
    }

    @And("Opportunity details match with previous fields")
    public void detailsMatchWithPreviousFields() {
        createdForm.clickDetails();
        softAssert.assertTrue(opportunity.getMapFields().equals(createdForm.getDetailMap()));
    }

    @After(value = "DeleteOpportunity", order = 0)
    public void deleteOpportunity() {
        createdForm.deleteCreatedOpportunity();
        softAssert.assertAll();
    }
}
