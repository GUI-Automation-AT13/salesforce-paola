package salesforce.ui.pages.opportunity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import salesforce.ui.pages.BasePage;

public class OpportunityPage extends BasePage {

    @FindBy(css = "a[title='New']")
    private WebElement newBtn;

    /**
     * Override method for waiting an element.
     */
    @Override
    protected void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(newBtn));
    }

    /**
     * Clicks the new button for Opportunity form.
     */
    private void clickNewBtnOpportunity() {
        webElementAction.clickBtn(newBtn);
    }

    /**
     * Goes to the New Opportunity page.
     * @return the new Opportunity page.
     */
    public NewOpportunityPage openNewOpportunityForm() {
        clickNewBtnOpportunity();
        return new NewOpportunityPage();
    }
}
