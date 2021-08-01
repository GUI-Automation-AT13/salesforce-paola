package salesforce.config;

import core.utils.PropertiesReader;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public final class ConfigEnvVar {

    private static ConfigEnvVar envConfig;
    private static final String CONFIG_PATH = "credential.properties";
    private static String loginUrl;
    private static String baseUrl;
    private static String opportunityUrl;
    private static String userName;
    private static String password;
    private static String waitTime;
    private static String clientId;
    private static String clientSecret;
    private static String version;
    private static String service;
    private static String login;
    private static String browser;
    private static Locale language;
    private static String featureURL;
    private static Properties properties = PropertiesReader.readProperties(CONFIG_PATH);

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
        featureURL = properties.getProperty("FEATURE_URL");
        language = new Locale(properties.getProperty("LANGUAGE"));
        loginUrl = properties.getProperty("LOGIN_URL");
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

    public String getFeatureURL() {
        return featureURL;
    }

    public Locale getLanguage() {
        return language;
    }
}
