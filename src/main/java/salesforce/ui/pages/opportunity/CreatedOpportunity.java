package salesforce.ui.pages.opportunity;

import core.utils.Translate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import salesforce.ui.pages.BasePage;
import java.util.HashMap;
import java.util.Map;

public class CreatedOpportunity extends BasePage {
    private final int sleepTime = 2000;
    private Map<String, String> headersFields;
    private Map<String, String> detailFields;

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
    private By deleteBtnConfirm = By.xpath("//button[@title='" + Translate.translateField("delete") + "']");
    private static final String CREATED_HEADER = "//div[./p[text()='%s']]//%s";
    private static final String CREATED_DETAIL = "//div[./div[./span[contains(text(),'%s')]]]"
            + "//lightning-formatted-text";
    private static final String CREATED_SEARCH = "//div[./div[./span[contains(text(),'%s')]]]/"
            + "div[@class='slds-form-element__control']";
    private static final String CREATED_ACCOUNT_DETAIL = "//div[./div[./span[contains(text(),'%s')]]]//a/span";

    public CreatedOpportunity() {
        setHeadersFields();
    }

    /**
     * Waits for a specific element to load on the page.
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
     * Gets a Map with all the headers fields of created opportunity page.
     */
    public void setHeadersFields() {
        headersFields = new HashMap<>();
        headersFields.put("OpportunityName", getTitleHeader());
        headersFields.put("Account", webElementAction.getHeaderString(CREATED_HEADER,
                Translate.translateField("accountName"), "a/span"));
        headersFields.put("CloseDate", webElementAction.getHeaderString(CREATED_HEADER,
                Translate.translateField("closeDate"), "lightning-formatted-text"));
        headersFields.put("Amount", webElementAction.getHeaderString(CREATED_HEADER,
                Translate.translateField("amount"), "lightning-formatted-text"));
        headersFields.put("Stage", getCurrentStage());
    }

    /**
     * Gets all the headers fields.
     * @return Map with all headers fields.
     */
    public Map<String, String> getHeadersFields() {
        return headersFields;
    }

    /**
     * Gets the Stage form the created Opportunity page.
     * @return A string with the current text of the Stage.
     */
    public String getCurrentStage() {
        return webElementAction.getElementText(createdOpportunityStage);
    }

    /**
     * Clicks the detail button for created Opportunity.
     */
    public void clickDetails() {
        webElementAction.clickBtn(driver.findElement(By.xpath("//a[@data-tab-value='detailTab']")));
        //NEEDED TO WAIT FOR JAVASCRIPT TO LOAD
        try {
            Thread.sleep(sleepTime);
            setDetailMap();
        } catch (InterruptedException ie) {

        }
    }

    /**
     * Gets Map with all the detail fields text.
     * @return Map with detail field text.
     */
    public void setDetailMap() {
        detailFields = new HashMap<>();
        detailFields.put("OpportunityName", webElementAction.getDetailText(CREATED_DETAIL,
                Translate.translateField("opportunityName")));
        detailFields.put("Type", webElementAction.getDetailText(CREATED_DETAIL,
                Translate.translateField("type")));
        detailFields.put("LeadSource", webElementAction.getDetailText(CREATED_DETAIL,
                Translate.translateField("leadSource")));
        detailFields.put("Amount", webElementAction.getDetailText(CREATED_DETAIL,
                Translate.translateField("amount")));
        detailFields.put("CloseDate", webElementAction.getDetailText(CREATED_DETAIL,
                Translate.translateField("closeDate")));
        detailFields.put("NextStep", webElementAction.getDetailText(CREATED_DETAIL,
                Translate.translateField("nextStep")));
        detailFields.put("Stage", webElementAction.getDetailText(CREATED_DETAIL,
                Translate.translateField("stage")));
        detailFields.put("Probability", webElementAction.getDetailText(CREATED_SEARCH,
                Translate.translateField("probability")));
        detailFields.put("Account", webElementAction.getDetailText(CREATED_ACCOUNT_DETAIL,
                Translate.translateField("account")));
    }

    /**
     * Gets all the detail fields in Opportunity created.
     * @return Map with all detail fields.
     */
    public Map<String, String> getDetailMap() {
        return detailFields;
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
