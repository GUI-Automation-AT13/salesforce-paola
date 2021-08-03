package salesforce.ui.pages.opportunity;

import core.utils.Translate;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import salesforce.ui.pages.BasePage;

public class OpportunityPage extends BasePage {
    private String newXpath;
    private By btnNew;

    /**
     * Override method for waiting an element.
     */
    @Override
    protected void waitForPageLoaded() {
        newXpath = String.format("//a[@title='%s']", Translate.translateField("new"));
        btnNew = By.xpath(String.format(newXpath));
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnNew));
    }

    /**
     * Clicks the new button for Opportunity form.
     */
    private void clickNewBtnOpportunity() {
        driver.findElement(By.xpath(newXpath)).click();
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
