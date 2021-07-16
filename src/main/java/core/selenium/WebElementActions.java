package core.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementActions{

    private WebDriver driver;
    private WebDriverWait wait;

    public WebElementActions() {
        driver = DriverManager.getInstance().getDriver();
        wait = DriverManager.driverManager.getWait();
    }

    public void setInputField(final WebElement webElement, final String text) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void clickBtn(final WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public void clickCheckBox(final WebElement webElement, final boolean select) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        if (select) {
            webElement.click();
        }
    }

    public void clickDropdown(final WebElement webElement, final WebElement optionElement) {
        webElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(optionElement));
        optionElement.click();
    }
}
