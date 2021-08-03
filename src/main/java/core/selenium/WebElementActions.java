package core.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

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
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.click();
    }

    /**
     * Clicks the checkbox elements.
     * @param webElement checkbox.
     * @param isPrivate set the status from private.
     */
    public void clickCheckBox(final WebElement webElement, final boolean isPrivate) {
        if (!webElement.isSelected() && isPrivate) {
            webElement.click();
        }
    }

    /**
     * Gets the text of a Web element.
     * @param webElement the specific element.
     * @return the string with the text.
     */
    public String getElementText(final WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }

    /**
     * Gets an element set by Xpath.
     * @param field the type of the element.
     * @param value the value of the element.
     * @return a webElement .
     */
    public WebElement getElementByXpath(final String field, final String value) {
        return driver.findElement(By.xpath(String.format(field, value)));
    }

    /**
     * Gets text from detail fields.
     * @param xpath the path for the field.
     * @param field name of the field.
     * @return the text from the detail field.
     */
    public String getDetailText(final String xpath, final String field) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        try {
            By xpathElement = By.xpath(String.format(xpath, field));
            WebElement element = driver.findElement(xpathElement);
            return element.getText();
        } catch (NoSuchElementException e) {
            return null;
        } finally {
            driver.manage().timeouts().implicitlyWait(DriverConfig.getInstance().getImplicitWaitTime(),
                    TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Gets the generic text from the header in a created opportunity page.
     * @param field name of the field to get the String.
     * @param headerType type of the element field.
     * @return the current text of the specific field.
     */
    public String getHeaderString(final String xpath, final String field, final String headerType) {
        try {
            WebElement createdHeader = driver.findElement(By.xpath(String.format(xpath, field,
                    headerType)));
            return createdHeader.getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
