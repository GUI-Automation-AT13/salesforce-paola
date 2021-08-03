package salesforce.ui.pages.opportunity;

import api.salesforce.entities.Opportunity;
import core.utils.Translate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import salesforce.ui.pages.BasePage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NewOpportunityPage extends BasePage {

    private static final String XPATH_INPUT_NAME = "//input[@name='%s']";
    private static final String XPATH_CHECKBOX = "//input[@name='%s']";
    private static final String XPATH_DESCRIPTION = "//label[text()='%s']/..//textarea";
    private static final String XPATH_DROPDOWN = "//label[text()='%s']/..//input";
    private static final String XPATH_DROPDOWN_OPTION = "//lightning-base-combobox-item[@data-value='%s']";
    private static final String XPATH_SEARCH_OPTION = "//span[@title='%s']";
    private WebElement saveBtn = driver.findElement(By.xpath("//button[@name='SaveEdit']"));
    private WebElement saveAndNewBtn = driver.findElement(By.xpath("//button[@name='SaveAndNew']"));
    private WebElement cancelBtn = driver.findElement(By.xpath("//button[@name='CancelEdit']"));


    /**
     * Override the method for waiting an element.
     */
    @Override
    protected void waitForPageLoaded() {
        String newXpath = "//button[@name='SaveEdit']";
        By byBtnSave = By.xpath(String.format(newXpath));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byBtnSave));
    }

    /**
     * Sets the inputs fields  for the Opportunity form.
     * @param field name of the Input field.
     * @param value of the input.
     * @return The new opportunity page to set again the input.
     */
    public NewOpportunityPage setInputField(final String field, final String value) {
        webElementAction.setInputField(webElementAction.getElementByXpath(XPATH_INPUT_NAME, field), value);
        return this;
    }

    /**
     * Sets the fields for generic input dropdown when creating a new opportunity.
     * @param field the field of the dropdown.
     * @param option the option from the dropdown.
     * @return The new opportunity page to set again the input.
     */
    public NewOpportunityPage setDropdown(final String field, final String option) {
        webElementAction.clickBtn(webElementAction.getElementByXpath(XPATH_DROPDOWN, field));
        webElementAction.clickBtn(webElementAction.getElementByXpath(XPATH_DROPDOWN_OPTION, option));
        return this;
    }

    /**
     * Sets the fields for generic search dropdown when creating a new opportunity.
     * @param field the field of the search dropdown.
     * @param option the option from the search dropdown.
     * @return The new opportunity page to set again the input.
     */
    public NewOpportunityPage setSearchDown(final String field, final String option) {
        wait.until(ExpectedConditions.visibilityOf(webElementAction.getElementByXpath(XPATH_DROPDOWN, field)));
        webElementAction.clickBtn(webElementAction.getElementByXpath(XPATH_DROPDOWN, field));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(XPATH_SEARCH_OPTION, option))));
        webElementAction.clickBtn(webElementAction.getElementByXpath(XPATH_SEARCH_OPTION, option));
        return this;
    }

    /**
     * Selects the Private checkbox for Private.
     * @param field field type.
     * @param isPrivate state of private.
     * @return NewOpportunityPage.
     */
    public NewOpportunityPage selectCheckbox(final String field, final boolean isPrivate) {
        webElementAction.clickCheckBox(webElementAction.getElementByXpath(XPATH_CHECKBOX, field), isPrivate);
        return this;
    }

    /**
     * Sets the description in the opportunity form.
     * @param descriptionText value of the description.
     * @param field field of web element.
     */
    public void setDescription(final String field, final String descriptionText) {
        webElementAction.setInputField(webElementAction.getElementByXpath(XPATH_DESCRIPTION, field),
                descriptionText);
    }

    /**
     * Sets all the parameters set in the Opportunity entity.
     * @param fields the key of Map.
     * @param opportunity entity.
     */
    public void setOpportunityFormFields(final Set<String> fields, final Opportunity opportunity) {
        Map<String, Runnable> mapFields = new HashMap<>();
        mapFields.put("Private", () -> selectCheckbox("IsPrivate", opportunity.isPrivate()));
        mapFields.put("Amount", () -> setInputField("Amount", opportunity.getAmount()));
        mapFields.put("OpportunityName", () -> setInputField("Name", opportunity.getOpportunityName()));
        mapFields.put("CloseDate", () -> setInputField("CloseDate", opportunity.getCloseDate()));
        mapFields.put("NextStep", () -> setInputField("NextStep", opportunity.getNextStep()));
        mapFields.put("Probability", () -> setInputField("Probability", opportunity.getProbability()));
        mapFields.put("OrderNumber", () -> setInputField("OrderNumber__c", opportunity.getOrderNumber()));
        mapFields.put("CurrentGenerator", () -> setInputField("CurrentGenerators__c",
                opportunity.getCurrentGenerator()));
        mapFields.put("TrackingNumber", () -> setInputField("TrackingNumber__c", opportunity.getTrackingNumber()));
        mapFields.put("MainCompetitor", () -> setInputField("MainCompetitors__c", opportunity.getMainComp()));
        mapFields.put("Description", () -> setDescription(Translate.translateField("description"),
                opportunity.getDescription()));
        mapFields.put("Stage", () -> setDropdown(Translate.translateField("stage"),
                opportunity.getOpportunityStage()));
        mapFields.put("Type", () -> setDropdown(Translate.translateField("type"),
                opportunity.getTypeOption()));
        mapFields.put("LeadSource", () -> setDropdown(Translate.translateField("leadSource"),
                opportunity.getLeadSource()));
        mapFields.put("Delivery", () -> setDropdown("Delivery/Installation Status", opportunity.getDeliveryOption()));
        mapFields.put("Account", () -> setSearchDown(Translate.translateField("accountName"),
                opportunity.getSearchAccount()));
        mapFields.put("Campaign", () -> setSearchDown(Translate.translateField("campaignName"),
                opportunity.getSearchCampaign()));
        fields.forEach(field -> mapFields.get(field).run());
    }

    /**
     * Creates a new Opportunity and goes to the details paage.
     * @param fields the keys of the Map.
     * @param opportunity entity of Opportunity.
     * @return created opportunity page.
     */
    public CreatedOpportunity createNewOpportunity(final Set<String> fields, final Opportunity opportunity) {
        setOpportunityFormFields(fields, opportunity);
        webElementAction.clickBtn(saveBtn);
        return new CreatedOpportunity();
    }

    /**
     * Redirects to the Created Opportunity page when form is saved correctly.
     * @return Created Opportunity page.
     */
    public CreatedOpportunity clickSaveOpportunity() {
        webElementAction.clickBtn(saveBtn);
        return new CreatedOpportunity();
    }

    /**
     * Saves the Opportunity created and open a new form.
     * @return New Opportunity form page.
     */
    public NewOpportunityPage clickSaveAndNewBtn() {
        webElementAction.clickBtn(saveAndNewBtn);
        return new NewOpportunityPage();
    }

    /**
     * Returns to the Opportunity page when cancel button is selected in the new form.
     * @return Opportunity page.
     */
    public OpportunityPage clickCancelBtn() {
        webElementAction.clickBtn(cancelBtn);
        return new OpportunityPage();
    }
}
