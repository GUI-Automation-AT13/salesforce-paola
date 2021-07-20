package salesforce.ui.pages.opportunity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import salesforce.ui.pages.BasePage;

public class NewOpportunityPage extends BasePage {

    @FindBy(xpath = "//input[@name='IsPrivate']")
    private WebElement privateCheckBox;

    @FindBy(xpath = "//input[@name='Amount']")
    private WebElement amountInput;

    @FindBy(xpath = "//input[@name='CloseDate']")
    private WebElement closeDate;

    @FindBy(xpath = "//input[@name='Name']")
    private WebElement nameOpportunityInput;

    @FindBy(xpath = "//input[@name='NextStep']")
    private WebElement nextStepInput;

    @FindBy(css = ".slds-input.slds-combobox__input[placeholder='Search Accounts...']")
    private WebElement accountSearch;

    @FindBy(xpath = "//label[text()='Stage']/..//input[@class='slds-input slds-combobox__input']")
    private WebElement stageInput;

    @FindBy(xpath = "//lightning-base-combobox-item[@data-value='Prospecting']")
    private WebElement optionStageInput;

    @FindBy(xpath = "//input[@name='Probability']")
    private WebElement probabilityInput;

    @FindBy(xpath = "//label[text()='Type']/..//input[@class='slds-input slds-combobox__input']")
    private WebElement typeInput;

    @FindBy(css = ".slds-input.slds-combobox__input[placeholder='Search Campaigns...']")
    private WebElement campaignSearch;

    @FindBy(xpath = "//input[@name='OrderNumber__c']")
    private WebElement orderNumberInput;

    @FindBy(xpath = "//input[@name='MainCompetitors__c']")
    private WebElement mainCompetitorInput;

    @FindBy(xpath = "//input[@name='CurrentGenerators__c']")
    private WebElement currentGeneratorsInput;

    @FindBy(xpath = "//input[@name='TrackingNumber__c']")
    private WebElement trackingNumberInput;

    @FindBy(xpath = "//textarea[@class='slds-textarea']")
    private WebElement textAreaInput;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    private WebElement saveBtn;

    @FindBy(name = "SaveAndNew")
    private WebElement saveAndNewBtn;

    @FindBy(name = "CancelEdit")
    private WebElement cancelBtn;

    public void setStage() {
        webElementAction.clickBtn(stageInput);
        webElementAction.clickBtn(optionStageInput);
    }

    /**
     * Override the method for waiting an element.
     */
    @Override
    protected void waitForPageLoaded() {
       wait.until(ExpectedConditions.visibilityOf(nameOpportunityInput));
    }

    /**
     * Selects the Private checkbox for Private.
     */
    public void selectPrivateCheckbox() {
        webElementAction.clickCheckBox(privateCheckBox);
    }

    /**
     * Sets the amount for the Opportunity form.
     * @param amount String for the Amount value.
     */
    public void setAmount(final String amount) {
        webElementAction.setInputField(amountInput, amount);
    }

    /**
     * Sets the date for the CloseDate input.
     * @param closeDateString String for the date.
     */
    public void setCloseDate(final String closeDateString) {
        webElementAction.setInputField(closeDate, closeDateString);
    }

    /**
     * Sets the opportunity name.
     * @param nameOpportunityText value of the opportunity name.
     */
    public void setOpportunityName(final String nameOpportunityText) {
        webElementAction.setInputField(nameOpportunityInput, nameOpportunityText);
    }

    /**
     * Sets the next step in the opportunity form.
     * @param nextStepText value of the next step.
     */
    public void setNextStep(final String nextStepText) {
        webElementAction.setInputField(nextStepInput, nextStepText);
    }

    /**
     * Sets te probability for the Opportunity.
     * @param probabilityText value of the probability.
     */
    public void setProbability(final String probabilityText) {
        webElementAction.setInputField(probabilityInput, probabilityText);
    }

    /**
     * Sets the order number in the opportunity form.
     * @param orderNumberText value of the order number.
     */
    public void setOrderNumber(final String orderNumberText) {
        webElementAction.setInputField(orderNumberInput, orderNumberText);
    }

    /**
     * Sets the main competitor in the opportunity form.
     * @param mainCompetitorText value of the main competitor.
     */
    public void setMainCompetitor(final String mainCompetitorText) {
        webElementAction.setInputField(mainCompetitorInput, mainCompetitorText);
    }

    /**
     * Sets the current generator in the opportunity form.
     * @param currentGeneratorText value of the current generator.
     */
    public void setCurrentGenerator(final String currentGeneratorText) {
        webElementAction.setInputField(currentGeneratorsInput, currentGeneratorText);
    }

    /**
     * Sets the tracking number in the opportunity form.
     * @param trackingNumberText value of the tracking number.
     */
    public void setTrackingNumber(final String trackingNumberText) {
        webElementAction.setInputField(trackingNumberInput, trackingNumberText);
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
