package api.salesforce;

public enum ApiEndPoints {

    ACCOUNT("/sobjects/Account"),
    ACCOUNT_ID("/sobjects/Account/{ACCOUNT_ID}"),
    CAMPAIGN("/sobjects/Campaign"),
    CAMPAIGN_ID("/sobjects/Campaign/{CAMPAIGN_ID}");

    private String endpoint;

    ApiEndPoints(final String newEndpoint) {
        this.endpoint = newEndpoint;
    }

    /**
     * Gets the api end point command according to the Sales force feature.
     * @return the end point.
     */
    public String toEndpoint() {
        return endpoint;
    }
}
