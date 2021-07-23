package core.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementActions {

    private WebDriver driver;
    private WebDriverWait wait;

    public WebElementActions() {
        driver = DriverManager.getInstance().getDriver();
        wait = DriverManager.driverManager.getWait();
    }

    /**
     * Sets the actions for inputs fields with text.
     * @param webElement input element.
     * @param text string to send to the input.
     */
    public void setInputField(final WebElement webElement, final String text) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }

    /**
     * Clicks the button element.
     * @param webElement btn element.
     */
    public void clickBtn(final WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    /**
     * Clicks the checkbox elements.
     * @param webElement checkbox.
     */
    public void clickCheckBox(final WebElement webElement) {
        if (!webElement.isSelected()) {
            webElement.click();
        }
    }

    /**
     * Selects the option in a dropdown input.
     * @param webElement dropdown element.
     * @param optionElement option selected from the dropdown.
     */
    public void clickDropdown(final WebElement webElement, final WebElement optionElement) {
        webElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(optionElement));
        optionElement.click();
    }
}
