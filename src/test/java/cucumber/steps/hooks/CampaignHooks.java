package cucumber.steps.hooks;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import api.salesforce.ApiEndPoints;
import api.salesforce.Authentication;
import api.salesforce.entities.Campaign;
import api.salesforce.entities.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.http.HttpHeaders;
import salesforce.config.ConfigEnvVar;

import static api.salesforce.Authentication.token;

public class CampaignHooks {
    protected ApiRequest apiRequest;
    protected ApiResponse apiResponse;
    private Authentication authentication;
    protected Campaign campaign;

    @Before(order = 0)
    public void apiSetup() {
        authentication = new Authentication();
        apiRequest = new ApiRequest();
        authentication.getAuth();
        System.out.println(token.getInstanceUrl() + ConfigEnvVar.getInstance().getService()
                + ConfigEnvVar.getInstance().getVersion());
        apiRequest.addHeader("Content-Type", "application/json")
                .addHeader(HttpHeaders.AUTHORIZATION,  token.getTokenType() + " " + token.getAccessToken())
                .setBaseUri(token.getInstanceUrl() + ConfigEnvVar.getInstance().getService()
                        + ConfigEnvVar.getInstance().getVersion());
    }

    @After(order = 0)
    public void afterAPIRequest() {
        apiRequest = new ApiRequest();
    }

    @Before(value = "@CreateAccount")
    public void createAccount() throws JsonProcessingException {
        campaign = new Campaign();
        campaign.setName("Opportunity Account");
        apiRequest.method(ApiMethod.POST)
                .endpoint(ApiEndPoints.ACCOUNT)
                .body(new ObjectMapper().writeValueAsString(campaign));

        apiResponse = new ApiResponse();
        ApiManager.execute(apiRequest, apiResponse);
        campaign.setId(apiResponse.getBody(Response.class).getId());
    }

    @After(value = "@CreateAccount")
    public void deleteAccount() {
        apiRequest.clearPathParam();
        apiRequest.method(ApiMethod.DELETE)
                .endpoint(ApiEndPoints.ACCOUNT_ID)
                .addPathParam("CAMPAIGN_ID", campaign.getId());

        apiResponse = new ApiResponse();
        ApiManager.execute(apiRequest, apiResponse);
    }
}
