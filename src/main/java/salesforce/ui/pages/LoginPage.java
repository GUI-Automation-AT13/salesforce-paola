package salesforce.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    @FindBy(name = "username")
    private WebElement usernameTxtBox;

    @FindBy(name = "pw")
    private WebElement passwordTxtBox;

    private By loginBtnXPath = By.xpath("//input[@name='Login']");

    /**
     * Waits for the page to load completely.
     * Considering the appear of Login button element.
     */
    @Override
    protected void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(usernameTxtBox));
    }

    /**
     * Sets the username text for the login page.
     * @param userName for login.
     */
    private void setUserName(final String userName) {
        webElementAction.setInputField(usernameTxtBox, userName);
    }

    /**
     * Sets the password text for the login page.
     * @param password for login.
     */
    private void setPassword(final String password) {
        webElementAction.setInputField(passwordTxtBox, password);
    }

    /**
     * Sets the property to click login button.
     */
    private void clickLoginBtn() {
        WebElement webElement = driver.findElement(loginBtnXPath);
        webElementAction.clickBtn(webElement);
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
        return new HomePage();
    }
}
