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

    /**
     * Logins in Salesforce.
     */
    @Given("I login to Salesforce site as a(n) developer user")
    public void iLoginToSalesforceSiteAsAnUser() {
        String usernameLogin = ConfigEnvVar.getInstance().getUserName();
        String passwordLogin = ConfigEnvVar.getInstance().getPassword();
        loginPage.successfulLogin(usernameLogin, passwordLogin);
    }

    /**
     * Creates a new opportunity.
     * @param dataTable opportunity fields given from scenario.
     * @throws IOException exception for error conversion from json to object.
     */
    @When("I create a new (.*?) with fields")
    public void iCreateAFeatureWithFields(final Map<String, String> dataTable) throws IOException {
        opportunity = new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(dataTable), Opportunity.class);
        opportunity.setOpportunityDetailField();
        OpportunityPage opportunityPage = new OpportunityPage();
        formOpportunity = opportunityPage.openNewOpportunityForm();
        fields = dataTable.keySet();
        createdForm = formOpportunity.createNewOpportunity(fields, opportunity);
    }

    /**
     *Validates that the opportunity name in alert matches.
     */
    @Then("A Successful message appear with (.*?) name")
    public void successfulMessageAppearAndMatches() {
        softAssert.assertEquals(createdForm.getSuccessfulAlert(), "\"" + opportunity.getOpportunityName() + "\"");
    }

    /**
     *
     */
    @Then("All (.*?) headers should match with previous fields")
    public void headersMatchWithPreviousFields() {
        for(String field: fields) {
            if( createdForm.getHeadersFields().containsKey(field)) {
                softAssert.assertEquals(opportunity.getMapFields().get(field), createdForm.getHeadersFields().get(field));
            }
        }
    }

    /**
     *Validates if all detail fields match with previous data.
     */
    @And("(.*?) details should match with previous fields")
    public void detailsMatchWithPreviousFields() {
        createdForm.clickDetails();
        for(String field: fields) {
            if( createdForm.getDetailMap().containsKey(field)) {
                softAssert.assertEquals(opportunity.getMapFields().get(field), createdForm.getDetailMap().get(field));
            }
        }
    }

    @After(order = 0)
    public void assertAll() {
        softAssert.assertAll();
    }
}
