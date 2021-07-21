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
    private WebElement createdOpportunityTitle;

    @FindBy(css = ".current + span")
    private WebElement createdOpportunityStage;

    @FindBy(xpath = "//a[@data-tab-value='detailTab']")
    private WebElement detailBtn;

    @FindBy(xpath = "//li[contains(@class,'slds-dropdown-trigger_click')]")
    private WebElement menuBtn;

    @FindBy(xpath = "//div[@role='menu']//*[@title='Delete']//a")
    private WebElement deleteOption;

    @FindBy(xpath = "//button[@title='Delete']")
    private WebElement deleteBtnConfirm;

    private static final String CREATED_HEADER = "//div[./p[text()='%s']]//lightning-formatted-text";
    private static final String CREATED_DETAIL = "//div[./div[./span[contains(text(),'%s')]]]//lightning-formatted-text";

    /**
     * Waits for an specific element to load on the page.
     */
    @Override
    protected void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(detailBtn));
    }

    /**
     * Gets the alert text when an opportunity is created correctly.
     * @return the text of the alert when opportunity is created.
     */
    public String getSuccessfulAlert() {
        return webElementAction.getElementText(textCreatedSuccessfull);
    }

    /**
     * Gets the Title of the created opportunity page.
     * @return the current text of the title header.
     */
    public String getTitleHeader() {
        return webElementAction.getElementText(createdOpportunityTitle);
    }

    /**
     * Gets the generic text from the header in a created opportunity page.
     * @param field name of the field to get the String.
     * @return the current text of the specific field.
     */
    public String getHeaderString(final String field) {
        return driver.findElement(By.xpath(String.format(CREATED_HEADER, field))).getText();
    }

    /**
     * Gets the Stage form the created Opportunity page.
     * @return A string with the current text of the Stage.
     */
    public String getCurrentStage() {
        return webElementAction.getElementText(createdOpportunityStage);
    }

    /**
     * Clicks the detail button for created Oppoprtunity.
     */
    public void clickDetails() {
        webElementAction.clickBtn(driver.findElement(By.xpath("//a[@data-tab-value='detailTab']")));
        //NEEDED TO WAIT FOR JAVASCRIPT TO LOAD
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {

        }
    }

    /**
     * Gets the text for a generic element.
     * @param field the name of the web element.
     * @return the text of the specific element.
     */
    public String getDetailTextElement(final String field) {
        return webElementAction.getElementText(driver.findElement(By.xpath(String.format(CREATED_DETAIL, field))));
    }

    /**
     * Deletes the created Opportunity in the Opportunity created page.
     * @return the opportunity page
     */
    public OpportunityPage deleteCreatedOpportunity() {
        webElementAction.clickBtn(menuBtn);
        webElementAction.clickBtn(deleteOption);
        webElementAction.clickBtn(deleteBtnConfirm);
        return new OpportunityPage();
    }
}
