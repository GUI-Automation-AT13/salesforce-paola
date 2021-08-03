package cucumber.steps.hooks;

import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import api.salesforce.ApiEndPoints;
import api.salesforce.Authentication;
import api.salesforce.entities.Account;
import api.salesforce.entities.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.http.HttpHeaders;
import salesforce.config.ConfigEnvVar;

import static api.salesforce.Authentication.token;

public class AccountHooks {
    protected ApiRequest apiRequest;
    protected ApiResponse apiResponse;
    private Authentication authentication;
    protected Account account;

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
        account = new Account();
        account.setName("Opportunity Account");
        apiRequest.method(ApiMethod.POST)
                .endpoint(ApiEndPoints.ACCOUNT)
                .body(new ObjectMapper().writeValueAsString(account));

        apiResponse = new ApiResponse();
        ApiManager.execute(apiRequest, apiResponse);
        account.setId(apiResponse.getBody(Response.class).getId());
    }

    @After(value = "@CreateAccount")
    public void deleteAccount() {
        apiRequest.clearPathParam();
        apiRequest.method(ApiMethod.DELETE)
                .endpoint(ApiEndPoints.ACCOUNT_ID)
                .addPathParam("ACCOUNT_ID", account.getId());

        apiResponse = new ApiResponse();
        ApiManager.execute(apiRequest, apiResponse);
    }
}
