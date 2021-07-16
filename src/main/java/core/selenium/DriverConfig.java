package core.selenium;

import salesforce.config.ConfigEnvVar;

public class DriverConfig {
    static DriverConfig driverConfig;
    private String browser;
    private int implicitWaitTime;
    private int explicitWaitTime;

    private DriverConfig() {
        initialize();
    }

    /**
     * Controls initializing of driverConfig.
     *
     * @return a DriverConfig entity.
     */
    public static DriverConfig getInstance() {
        if (driverConfig == null) {
            driverConfig = new DriverConfig();
        }
        return driverConfig;
    }

    private void initialize() {
        browser = ConfigEnvVar.getInstance().getBrowser();
        implicitWaitTime = 20;
        explicitWaitTime = 20;
    }

    public String getBrowser() {
        return browser;
    }

    public int getImplicitWaitTime() {
        return implicitWaitTime;
    }

    public int getExplicitWaitTime() {
        return explicitWaitTime;
    }
}
