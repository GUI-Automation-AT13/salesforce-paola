package api.salesforce.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

public class Opportunity {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String opportunityName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nextStep;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String closeDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String opportunityStage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String typeOption;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String leadSource;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deliveryOption;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String probability;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String orderNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String currentGenerator;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trackingNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mainComp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String searchAccount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String searchCampaign;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean isPrivate = false;

    /**
     * Returns the Opportunity name.
     * @return string of the name.
     */
    public String getOpportunityName() {
        return opportunityName;
    }

    /**
     * Sets the Opportunity Name.
     * @param opportunityNameNew the name to set.
     */
    public void setOpportunityName(final String opportunityNameNew) {
        this.opportunityName = opportunityNameNew.concat(LocalDate.now().toString());
    }

    /**
     * Returns the Opportunity Next Step.
     * @return string of the next step.
     */
    public String getNextStep() {
        return nextStep;
    }

    /**
     * Sets the Opportunity Next step.
     * @param nextStepNew the text to set.
     */
    public void setNextStep(final String nextStepNew) {
        this.nextStep = nextStepNew;
    }

    /**
     * Returns the Opportunity Amount.
     * @return long of the amount.
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * Sets the Opportunity Amount.
     * @param amountNew the amount to set.
     */
    public void setAmount(final Long amountNew) {
        this.amount = amountNew;
    }

    /**
     * Returns the Opportunity Close date.
     * @return string of the close date.
     */
    public String getCloseDate() {
        return closeDate;
    }

    /**
     * Sets the Opportunity Close Date.
     * @param closeDateNew the date to set.
     */
    public void setCloseDate(final String closeDateNew) {
        this.closeDate = closeDateNew;
    }

    /**
     * Returns the Opportunity Stage.
     * @return string of the stage.
     */
    public String getOpportunityStage() {
        return opportunityStage;
    }

    /**
     * Sets the Opportunity Stage.
     * @param opportunityStageNew the text to set.
     */
    public void setOpportunityStage(final String opportunityStageNew) {
        this.opportunityStage = opportunityStageNew;
    }

    /**
     * Returns the Opportunity type.
     * @return string of the type.
     */
    public String getTypeOption() {
        return typeOption;
    }

    /**
     * Sets the Opportunity Type.
     * @param typeOptionNew the text to set.
     */
    public void setTypeOption(final String typeOptionNew) {
        this.typeOption = typeOptionNew;
    }

    /**
     * Returns the Opportunity Lead source.
     * @return string of the lead source.
     */
    public String getLeadSource() {
        return leadSource;
    }

    /**
     * Sets the Opportunity Lead Source.
     * @param leadSourceNew the text to set.
     */
    public void setLeadSource(final String leadSourceNew) {
        this.leadSource = leadSourceNew;
    }

    /**
     * Returns the Opportunity Delivery option.
     * @return string of the delivery option.
     */
    public String getDeliveryOption() {
        return deliveryOption;
    }

    /**
     * Sets the Opportunity Delivery Option.
     * @param deliveryOptionNew the text to set.
     */
    public void setDeliveryOption(final String deliveryOptionNew) {
        this.deliveryOption = deliveryOptionNew;
    }

    /**
     * Returns the Opportunity probability.
     * @return string of the probability.
     */
    public String getProbability() {
        return probability;
    }

    /**
     * Sets the Opportunity Probability.
     * @param probabilityNew the text to set.
     */
    public void setProbability(final String probabilityNew) {
        this.probability = probabilityNew;
    }

    /**
     * Returns the Opportunity Order Number.
     * @return string of the order number.
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the Opportunity Order Number.
     * @param orderNumberNew the text to set.
     */
    public void setOrderNumber(final String orderNumberNew) {
        this.orderNumber = orderNumberNew;
    }

    /**
     * Returns the Opportunity Current Generator.
     * @return string of the current generator.
     */
    public String getCurrentGenerator() {
        return currentGenerator;
    }

    /**
     * Sets the Opportunity Current Generator.
     * @param currentGeneratorNew the text to set.
     */
    public void setCurrentGenerator(final String currentGeneratorNew) {
        this.currentGenerator = currentGeneratorNew;
    }

    /**
     * Returns the Opportunity Tracking Number.
     * @return string of the tracking number.
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * Sets the Opportunity Tracking Number.
     * @param trackingNumberNew the text to set.
     */
    public void setTrackingNumber(final String trackingNumberNew) {
        this.trackingNumber = trackingNumberNew;
    }

    /**
     * Returns the Opportunity Main competitor.
     * @return string of the main competitor.
     */
    public String getMainComp() {
        return mainComp;
    }

    /**
     * Sets the Opportunity Main Competitor.
     * @param mainCompNew the text to set.
     */
    public void setMainComp(final String mainCompNew) {
        this.mainComp = mainCompNew;
    }

    /**
     * Returns the Opportunity Description.
     * @return string of the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the Opportunity Description.
     * @param descriptionNew the text to set.
     */
    public void setDescription(final String descriptionNew) {
        this.description = descriptionNew;
    }

    /**
     * Returns the Opportunity Search Account.
     * @return string of the search account.
     */
    public String getSearchAccount() {
        return searchAccount;
    }

    /**
     * Sets the Opportunity Account.
     * @param searchAccountNew the text to set.
     */
    public void setSearchAccount(final String searchAccountNew) {
        this.searchAccount = searchAccountNew;
    }

    /**
     * Returns the Opportunity Search Campaign.
     * @return string of the search campaign.
     */
    public String getSearchCampaign() {
        return searchCampaign;
    }

    /**
     * Sets the Opportunity Campaign.
     * @param searchCampaignNew the text to set.
     */
    public void setSearchCampaign(final String searchCampaignNew) {
        this.searchCampaign = searchCampaignNew;
    }

    /**
     * Returns the Opportunity Private.
     * @return boolean of private status.
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Sets the Opportunity Private status.
     * @param isPrivateNew the status to set.
     */
    public void setPrivate(final boolean isPrivateNew) {
        isPrivate = isPrivateNew;
    }
}
