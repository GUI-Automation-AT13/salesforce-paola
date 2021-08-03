package salesforce.ui;

import core.selenium.DriverManager;
import salesforce.config.ConfigEnvVar;
import salesforce.ui.pages.opportunity.OpportunityPage;

public class PageTransporter {

    private static String baseUrl = ConfigEnvVar.getInstance().getBaseUrl();
    private String opportunityUrl = ConfigEnvVar.getInstance().getOpportunityUrl();

    /**
     * Goes to a specific page.
     * @param url destination page.
     */
    public static void goToURL(final String url) {
        DriverManager.getInstance().getDriver().navigate().to(url);
    }

    /**
     * Goes to the Opportunity Page feature.
     * @return Opportunity page.
     */
    public OpportunityPage goToOpportunityPage() {
        goToURL(baseUrl.concat(opportunityUrl));
        return new OpportunityPage();
    }

    public static void navigateToSpecificFeature(final String feature) {
        goToURL(baseUrl.concat(String.format(ConfigEnvVar.getInstance().getFeatureURL(), feature)));
    }
}
