package salesforce.ui.pages.opportunity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import salesforce.ui.pages.BasePage;

public class NewOpportunityPage extends BasePage {

    @FindBy(xpath = "//input[@name='IsPrivate']")
    private WebElement privateCheckBox;

    @FindBy(xpath = "//input[@name='CloseDate']")
    private WebElement closeDate;

    @FindBy(xpath = "//textarea[@class='slds-textarea']")
    private WebElement textAreaInput;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    private WebElement saveBtn;

    @FindBy(name = "SaveAndNew")
    private WebElement saveAndNewBtn;

    @FindBy(name = "CancelEdit")
    private WebElement cancelBtn;

    private static final String XPATH_INPUT = "//input[@name='%s']";
    private static final String XPATH_DROPDOWN_ELEMENT = "//label[text()='%s']/..//input";
    private static final String XPATH_DROPDOWN_OPTION = "//lightning-base-combobox-item[@data-value='%s']";
    //private static final String XPATH_SEARCH = "//input[contains(@placeholder,'%s')]";
    //private static final String XPATH_SEARCH_OPTION = "//lightning-base-combobox-item[.//span[@title='%s']]/..";
    private static final String XPATH_SEARCH = "//input[contains(@placeholder,'%s')]";
    private static final String XPATH_SEARCH_OPTION = "//span[@title='%s']";

    /**
     * Override the method for waiting an element.
     */
    @Override
    protected void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(saveBtn));
    }

    /**
     * Sets the inputs fields  for the Opportunity form.
     * @param field name of the Input field.
     * @param value of the input.
     * @return The new opportunity page to set again the input.
     */
    public NewOpportunityPage setInputField(final String field, final String value) {
        String modifiedField = field.replace(" ", "");
        WebElement element = driver.findElement(By.xpath(String.format(XPATH_INPUT, modifiedField)));
        webElementAction.setInputField(element, value);
        return this;
    }

    /**
     * Sets the fields for generic input dropdown when creating a new opportunity.
     * @param field the field of the dropdown.
     * @param option the option from the dropdown.
     * @return The new opportunity page to set again the input.
     */
    public NewOpportunityPage setDropdown(final String field, final String option) {
        webElementAction.clickBtn(driver.findElement(By.xpath(String.format(XPATH_DROPDOWN_ELEMENT, field))));
        webElementAction.clickBtn(driver.findElement(By.xpath(String.format(XPATH_DROPDOWN_OPTION, option))));
        return this;
    }

    /**
     * Sets the fields for generic search dropdown when creating a new opportunity.
     * @param field the field of the search dropdown.
     * @param option the option from the search dropdown.
     * @return The new opportunity page to set again the input.
     */
    public NewOpportunityPage setSearchDown(final String field, final String option) {
        driver.findElement(By.xpath(String.format(XPATH_SEARCH, field))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(XPATH_SEARCH_OPTION, option))));
        driver.findElement(By.xpath(String.format(XPATH_SEARCH_OPTION, option))).click();
        return this;
    }

    /**
     * Selects the Private checkbox for Private.
     * @param isPrivate state of private.
     */
    public void selectPrivateCheckbox(final boolean isPrivate) {
        webElementAction.clickCheckBox(privateCheckBox, isPrivate);
    }

    /**
     * Sets the date for the CloseDate input.
     * @param closeDateString String for the date.
     */
    public void setCloseDate(final String closeDateString) {
        webElementAction.setInputField(closeDate, closeDateString);
    }

    /**
     * Sets the description in the opportunity form.
     * @param descriptionText value of the description.
     */
    public void setDescription(final String descriptionText) {
        webElementAction.setInputField(textAreaInput, descriptionText);
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
