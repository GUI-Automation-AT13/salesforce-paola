package api.salesforce;

import api.salesforce.entities.Token;
import salesforce.config.ConfigEnvVar;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static Token token;

    /**
     * Sets the salesforce token.
     */
    public void getAuth() {
        token = given().urlEncodingEnabled(true)
                .param("username", ConfigEnvVar.getInstance().getUserName())
                .param("password", ConfigEnvVar.getInstance().getPassword())
                .param("client_id", ConfigEnvVar.getInstance().getClientId())
                .param("client_secret", ConfigEnvVar.getInstance().getClientSecret())
                .param("grant_type", "password")
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .log().all()
                .when().
                        post(ConfigEnvVar.getInstance().getLogin())
                .as(Token.class);
    }
}
