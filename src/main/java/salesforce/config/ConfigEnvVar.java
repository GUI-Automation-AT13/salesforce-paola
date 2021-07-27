package salesforce.config;

import core.utils.PropertiesReader;

import java.util.Properties;

public final class ConfigEnvVar {

    private static ConfigEnvVar envConfig;
    private static final String CONFIG_PATH = "credential.properties";
    private String loginUrl;
    private String baseUrl;
    private String opportunityUrl;
    private String userName;
    private String password;
    private String waitTime;
    private String clientId;
    private String clientSecret;
    private String version;
    private String service;
    private String login;

    public String getVersion() {
        return version;
    }

    public String getService() {
        return service;
    }

    public String getLogin() {
        return login;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public String getBrowser() {
        return browser;
    }

    private String browser;

    private ConfigEnvVar() {
        initialize();
    }

    public static ConfigEnvVar getInstance() {
        if (envConfig == null) {
            envConfig = new ConfigEnvVar();
        }
        return envConfig;
    }

    private void initialize() {
        Properties properties = PropertiesReader.readProperties(CONFIG_PATH);
        loginUrl = properties.getProperty("LOGIN_URL");
        baseUrl = properties.getProperty("BASE_URI");
        opportunityUrl = properties.getProperty("OPPORTUNITY_URL");
        userName = properties.getProperty("USER");
        password = properties.getProperty("PASS");
        browser = properties.getProperty("BROWSER");
        waitTime = properties.getProperty("WAIT_TIME");
        clientId = properties.getProperty("CLIENT_ID");
        clientSecret = properties.getProperty("CLIENT_SECRET");
        login = properties.getProperty("LOGIN");
        version = properties.getProperty("VERSION");
        service = properties.getProperty("SERVICE");
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getOpportunityUrl() {
        return opportunityUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
