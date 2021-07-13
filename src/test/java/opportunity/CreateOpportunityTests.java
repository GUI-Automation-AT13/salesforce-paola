package opportunity;

import base.BaseTest;
import org.testng.annotations.Test;
import salesforce.pages.HomePage;

public class CreateOpportunityTests extends BaseTest {

    @Test
    public void createOpportunityWithRequiredFields() {
        HomePage homePage = loginPage.successfulLogin("", "");
    }
}
