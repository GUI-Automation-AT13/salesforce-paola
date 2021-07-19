package core.selenium;

import salesforce.config.ConfigEnvVar;

public final class DriverConfig {
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
        implicitWaitTime = Integer.parseInt(ConfigEnvVar.getInstance().getWaitTime());
        explicitWaitTime = Integer.parseInt(ConfigEnvVar.getInstance().getWaitTime());
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
