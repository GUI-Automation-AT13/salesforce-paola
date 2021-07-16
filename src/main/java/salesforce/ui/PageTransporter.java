package salesforce.ui;

import core.selenium.DriverManager;
import salesforce.config.ConfigEnvVar;
import salesforce.ui.pages.opportunity.OpportunityPage;

public class PageTransporter {

    private String baseUrl = ConfigEnvVar.getInstance().getBaseUrl();
    private String opportunityUrl = ConfigEnvVar.getInstance().getOpportunityUrl();

    public void goToURL(final String url) {
        DriverManager.getInstance().getDriver().navigate().to(url);
    }

    public OpportunityPage goToOpportunityPage() {
        goToURL(baseUrl.concat(opportunityUrl));
        return new OpportunityPage();
    }
}
