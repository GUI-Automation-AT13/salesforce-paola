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
