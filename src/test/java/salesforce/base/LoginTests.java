package salesforce.base;

import org.testng.annotations.Test;
import salesforce.config.ConfigEnvVar;
import salesforce.ui.pages.HomePage;

public class LoginTests extends BaseTest{

    private String username = ConfigEnvVar.getInstance().getUserName();
    private String password = ConfigEnvVar.getInstance().getPassword();

    @Test
    public void accessLogin() {
        HomePage homePage = loginPage.successfulLogin(username, password);
    }

}
