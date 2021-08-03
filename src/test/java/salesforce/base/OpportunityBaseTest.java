package salesforce.base;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import api.salesforce.ApiEndPoints;
import api.salesforce.Authentication;
import api.salesforce.entities.Account;
import api.salesforce.entities.Campaign;
import api.salesforce.entities.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import salesforce.config.ConfigEnvVar;
import salesforce.ui.PageTransporter;
import salesforce.ui.pages.HomePage;
import salesforce.ui.pages.LoginPage;
import salesforce.ui.pages.opportunity.OpportunityPage;

import static api.salesforce.Authentication.token;

public class OpportunityBaseTest extends BaseTest {
    private ApiRequest apiRequest;
    private ApiResponse apiResponse;
    private Authentication authentication = new Authentication();
    protected Account account;
    protected Campaign campaign;
    protected HomePage homePage;
    protected PageTransporter pageTransporter = new PageTransporter();
    protected OpportunityPage opportunityPage;
    protected LoginPage loginPage;

    @BeforeClass
    public void loginAndSetupAPI() {
        apiRequest = new ApiRequest();
        authentication.getAuth();
        apiRequest.addHeader("Content-Type", "application/json")
                .addHeader(HttpHeaders.AUTHORIZATION,  token.getTokenType() + " " + token.getAccessToken())
                .setBaseUri(token.getInstanceUrl() + ConfigEnvVar.getInstance().getService()
                        + ConfigEnvVar.getInstance().getVersion());
    }

    /**
     * Creates an Account needed for Opportunity with all fields.
     * @throws JsonProcessingException
     */
    @BeforeMethod(onlyForGroups = {"OpportunityAllFields"})
    public void beforeCreateAccount() throws JsonProcessingException {
        account = new Account();
        account.setName("Opportunity Account");
        apiRequest.method(ApiMethod.POST)
                .endpoint(ApiEndPoints.ACCOUNT)
                .body(new ObjectMapper().writeValueAsString(account));

        apiResponse = new ApiResponse();
        ApiManager.execute(apiRequest, apiResponse);
        account.setId(apiResponse.getBody(Response.class).getId());
    }

    /**
     * Deletes the created Account.
     */
    @AfterMethod(onlyForGroups = {"OpportunityAllFields"})
    public void afterDeleteAccount() {
        apiRequest.clearPathParam();
        apiRequest.method(ApiMethod.DELETE)
                .endpoint(ApiEndPoints.ACCOUNT_ID)
                .addPathParam("ACCOUNT_ID", account.getId());

        apiResponse = new ApiResponse();
        ApiManager.execute(apiRequest, apiResponse);
    }

    /**
     * Creates a Campaign needed for Opportunity with all fields.
     * @throws JsonProcessingException
     */
    @BeforeMethod(onlyForGroups = {"OpportunityAllFields"})
    public void beforeCreateCampaign() throws JsonProcessingException {
        apiRequest.clearPathParam();
        campaign = new Campaign();
        campaign.setName("Opportunity Campaign");
        apiRequest.method(ApiMethod.POST)
                .endpoint(ApiEndPoints.CAMPAIGN)
                .body(new ObjectMapper().writeValueAsString(campaign));
        apiResponse = new ApiResponse();
        ApiManager.execute(apiRequest, apiResponse);
        campaign.setId(apiResponse.getBody(Response.class).getId());
    }

    /**
     * Deletes the created Campaign.
     */
    @AfterMethod(onlyForGroups = {"OpportunityAllFields"})
    public void afterDeleteCampaign() {
        apiRequest.clearPathParam();
        apiRequest.method(ApiMethod.DELETE)
                .endpoint(ApiEndPoints.CAMPAIGN_ID)
                .addPathParam("CAMPAIGN_ID", campaign.getId());

        apiResponse = new ApiResponse();
        ApiManager.execute(apiRequest, apiResponse);
    }

    @AfterClass
    public void afterAPIRequest() {
        apiRequest = new ApiRequest();
    }
}
