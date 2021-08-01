package cucumber.steps;

import io.cucumber.java.en.When;
import salesforce.ui.PageTransporter;

public class NavigationSteps {

    @When("I navigate to {string} page")
    public void iNavigateToAFeaturePage(final String page) {
        PageTransporter.navigateToSpecificFeature(page);
    }
}
