package salesforce.ui.pages.opportunity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import salesforce.ui.pages.BasePage;

public class CreatedOpportunity extends BasePage {

    @FindBy(css = ".slds-theme--success span a div")
    private WebElement textCreatedSuccessfull;

    @FindBy(xpath = "//h1//slot//lightning-formatted-text")
    private WebElement createdOpportunityName;

    @FindBy(xpath = "//p[@title='Close Date']//..//lightning-formatted-text")
    private WebElement createdOpportunityCloseDate;

    @FindBy(xpath = "//a[./span[@class='current slds-path__stage']]/span[@class='title slds-path__title']")
    private WebElement createdOpportunityStage;

    @FindBy(xpath = "//a[@data-tab-value='detailTab']")
    private WebElement detailBtn;

    private static final String DETAIL_ELEMENT = "//div/span[text()='%s']//..//..//lightning-formatted-text";

    @Override
    protected void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(detailBtn));
    }

    public String getDetailElementText(final String field) {
        webElementAction.clickBtn(detailBtn);
        return driver.findElement(By.xpath(String.format(DETAIL_ELEMENT, field))).getText();
    }

    public String getSuccesfullCreateAlert() {
        return webElementAction.getElementText(textCreatedSuccessfull);
    }

    public String getOpportunityNameTitle() {
        return webElementAction.getElementText(createdOpportunityName);
    }

    public String getCloseDate() {
        return webElementAction.getElementText(createdOpportunityCloseDate);
    }

    public String getActiveStage() {
        return webElementAction.getElementText(createdOpportunityStage);
    }
}
