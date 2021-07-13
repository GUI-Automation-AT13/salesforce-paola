package salesforce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    @FindBy(name = "username")
    private WebElement usernameTxtBox;

    @FindBy(name = "password")
    private WebElement passwordTxtBox;

    @FindBy(name = "Login")
    private WebElement loginBtn;

    /**
     * Sets the driver for parent class.
     * @param driver
     */
    public LoginPage(final WebDriver driver) {
        super(driver);
    }

    /**
     * Waits for the page to load completely.
     * Considering the appear of Login button element.
     */
    @Override
    protected void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(loginBtn));
    }

    /**
     * Sets the username text for the login page.
     * @param userName for login.
     */
    private void setUserName(final String userName) {
        usernameTxtBox.sendKeys(userName);
    }

    /**
     * Sets the password text for the login page.
     * @param password for login.
     */
    private void setPassword(final String password) {
        passwordTxtBox.sendKeys(password);
    }

    /**
     * Sets the property to click login button.
     */
    private void clickLoginBtn() {
        loginBtn.click();
    }

    /**
     * Goes to Homepage if login is correct.
     * @param userName for login.
     * @param password for login.
     * @return HomePage the principal page.
     */
    public HomePage successfulLogin(final String userName, final String password) {
        setUserName(userName);
        setPassword(password);
        clickLoginBtn();
        return new HomePage(driver);
    }
}
