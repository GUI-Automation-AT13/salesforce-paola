package salesforce.ui.pages.opportunity;

import core.utils.Translate;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import salesforce.config.ConfigEnvVar;
import salesforce.ui.pages.BasePage;
import java.util.HashMap;
import java.util.Map;

public class CreatedOpportunity extends BasePage {
    private final int sleepTime = 2000;

    @FindBy(css = ".slds-theme--success span a div")
    private WebElement textCreatedSuccessfull;

    @FindBy(xpath = "//h1//slot//lightning-formatted-text")
    private WebElement createdOpportunityTitle;

    @FindBy(css = ".current + span")
    private WebElement createdOpportunityStage;

    @FindBy(xpath = "//a[@data-tab-value='detailTab']")
    private WebElement detailBtn;

    private By menuBtn = By.xpath("//li[contains(@class,'slds-dropdown-trigger_click')]");
    private By deleteOption = By.xpath("//div[@role='menu']//*[@title='" + Translate.translateField("delete")
            + "']//a");
    private By deleteBtnConfirm = By.xpath("//button[@title=' + ConfigEnvVar.getResourceBundle()."
            + "getString(\"delete\") + \"']");
    private static final String CREATED_HEADER = "//div[./p[text()='%s']]//%s";
    private static final String CREATED_DETAIL = "//div[./div[./span[contains(text(),'%s')]]]"
            + "//lightning-formatted-text";
    private static final String CREATED_SEARCH = "//div[./div[./span[contains(text(),'%s')]]]/"
            + "div[@class='slds-form-element__control']";

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
     * @param headerType type of the element field.
     * @return the current text of the specific field.
     */
    public String getHeaderString(final String field, final String headerType) {
        try {
            WebElement createdHeader = driver.findElement(By.xpath(String.format(CREATED_HEADER, field,
                    headerType)));
            return webElementAction.getElementText(createdHeader);
        } catch (NoSuchElementException e) {
            return "";
        }
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
            Thread.sleep(sleepTime);
        } catch (InterruptedException ie) {

        }
    }

    /**
     * Gets text from detail fields.
     * @param xpath the path for the field.
     * @param field name of the field.
     * @return the text from the detail field.
     */
    public String getDetailText(final String xpath, final String field) {
        By xpathElement = By.xpath(String.format(xpath, field));
        if (!driver.findElement(xpathElement).getText().isEmpty()) {
            WebElement element = driver.findElement(xpathElement);
            return webElementAction.getElementText(element);
        } else {
            return "";
        }
    }

    /**
     * Gets Map with all the detail fields text.
     * @return Map with detail field text.
     */
    public Map<String, String> getDetailMap() {
        Map<String, String> mapFields = new HashMap<>();
        mapFields.put("OpportunityName", getDetailText(CREATED_DETAIL,
                Translate.translateField("opportunityName")));
        mapFields.put("Type", getDetailText(CREATED_DETAIL,
                Translate.translateField("type")));
        mapFields.put("LeadSource", getDetailText(CREATED_DETAIL,
                Translate.translateField("leadSource")));
        mapFields.put("Amount", getDetailText(CREATED_DETAIL,
                Translate.translateField("amount")));
        mapFields.put("CloseDate", getDetailText(CREATED_DETAIL,
                Translate.translateField("closeDate")));
        mapFields.put("NextStep", getDetailText(CREATED_DETAIL,
                Translate.translateField("nextStep")));
        mapFields.put("Stage", getDetailText(CREATED_DETAIL,
                Translate.translateField("stage")));
        mapFields.put("Probability", getDetailText(CREATED_SEARCH,
                Translate.translateField("probability")));
        mapFields.put("Account", getDetailText(CREATED_SEARCH,
                Translate.translateField("accountName")));
        return mapFields;
    }

    /**
     * Deletes the created Opportunity in the Opportunity created page.
     * @return the opportunity page
     */
    public OpportunityPage deleteCreatedOpportunity() {
        webElementAction.clickBtn(driver.findElement(menuBtn));
        webElementAction.clickBtn(driver.findElement(deleteOption));
        webElementAction.clickBtn(driver.findElement(deleteBtnConfirm));
        return new OpportunityPage();
    }
}
