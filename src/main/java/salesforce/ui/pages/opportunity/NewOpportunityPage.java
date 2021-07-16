package salesforce.ui.pages.opportunity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import salesforce.ui.pages.BasePage;

public class NewOpportunityPage extends BasePage {

    @FindBy(name = "IsPrivate")
    private WebElement privateCheckBox;

    @FindBy(name = "Amount")
    private WebElement amountInput;

    @FindBy(xpath = "//input[@name='CloseDate']")
    private WebElement closeDate;

    @FindBy(xpath = "//input[@name='Name']")
    private WebElement nameOpportunityInput;

    @FindBy(name = "NextStep")
    private WebElement nextStepInput;

    @FindBy(css = ".slds-input.slds-combobox__input[placeholder='Search Accounts...']")
    private WebElement accountSearch;

    @FindBy(xpath = "//label[text()='Stage']/..//input[@class='slds-input slds-combobox__input']")
    private WebElement stageInput;

    @FindBy(xpath = "//lightning-base-combobox-item[@data-value='Prospecting']")
    private WebElement optionStageDropdown;

    @FindBy(name = "Probability")
    private WebElement probabilityInput;

    @FindBy(xpath = "//label[text()='Type']/..//input[@class='slds-input slds-combobox__input']")
    private WebElement typeInput;

    @FindBy(css = ".slds-input.slds-combobox__input[placeholder='Search Campaigns...']")
    private WebElement campaignSearch;

    @FindBy(name = "OrderNumber__c")
    private WebElement orderNumberInput;

    @FindBy(name = "MainCompetitors__c")
    private WebElement mainCompetitorInput;

    @FindBy(name = "CurrentGenerators__c")
    private WebElement currentGeneratorsInput;

    @FindBy(name = "TrackingNumber__c")
    private WebElement trackingNumberInput;

    //Delivery

    @FindBy(className = "slds-textarea")
    private WebElement textAreaInput;

    @FindBy(css = "button[title='Save']")
    private WebElement saveBtn;

    @FindBy(name = "SaveAndNew")
    private WebElement saveAndNewBtn;

    @FindBy(name = "CancelEdit")
    private WebElement cancelBtn;

    @Override
    protected void waitForPageLoaded() {
       // wait.until(ExpectedConditions.visibilityOf(nameOpportunityInput));
    }

    public void selectPrivateCheckbx(final boolean select) {
        webElementAction.clickCheckBox(privateCheckBox, true);
    }

    public void setAmount(final String amount) {
        webElementAction.setInputField(amountInput, amount);
    }

    public void setCloseDate(final String closeDateString) {
        webElementAction.setInputField(closeDate, closeDateString);
    }

    public void setOpportunityName(final String nameOpportunityText) {
        webElementAction.setInputField(nameOpportunityInput, nameOpportunityText);
    }

    public void setNextStep(final String nextStepText) {
        webElementAction.setInputField(nextStepInput, nextStepText);
    }

    public void setStage() {
        webElementAction.clickDropdown(stageInput, optionStageDropdown);
    }
    //

    public void setProbability(final String probabilityText) {
        webElementAction.setInputField(probabilityInput, probabilityText);
    }

    //

    public void setOrderNumber(final String orderNumberText) {
        webElementAction.setInputField(orderNumberInput, orderNumberText);
    }

    public void setMainCompetitor(final String mainCompetitorText) {
        webElementAction.setInputField(mainCompetitorInput, mainCompetitorText);
    }

    public void setCurrentGenerator(final String currentGeneratorText) {
        webElementAction.setInputField(currentGeneratorsInput, currentGeneratorText);
    }

    public void setTrackingNumer(final String trackingNumerText) {
        webElementAction.setInputField(trackingNumberInput, trackingNumerText);
    }

    //

    public void setDescription(final String descriptionText) {
        webElementAction.setInputField(textAreaInput, descriptionText);
    }

    public CreatedOpportunity clickSaveOpportunity() {
        webElementAction.clickBtn(saveBtn);
        return new CreatedOpportunity();
    }

    public NewOpportunityPage clickSaveAndNewBtn() {
        webElementAction.clickBtn(saveAndNewBtn);
        return new NewOpportunityPage();
    }

    public OpportunityPage clickCancelBtn() {
        webElementAction.clickBtn(cancelBtn);
        return new OpportunityPage();
    }
}
