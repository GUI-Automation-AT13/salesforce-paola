Feature: Create opportunity

  @CreateAccount @CreateCampaign
  Scenario: create a new Opportunity with only required fields
    Given I login to Salesforce site as a developer user
    When I navigate to "Opportunity" page
    When I create a new Opportunity with fields
      | Private          | true                |
      | Amount           | 123                 |
      | CloseDate        | 7/6/2021            |
      | OpportunityName  | New Opportunity     |
      | NextStep         | Dev.#23             |
      | Account          | Opportunity Account |
      | Stage            | Prospecting         |
      | Type             | New Customer        |
      | Probability      | 20                  |
      | LeadSource       | Web                 |
      | Campaign         | Opportunity Campaign|
      | OrderNumber      | Order#12            |
      | MainCompetitor   | Sony Inc.           |
      | CurrentGenerator | Number#34-TB        |
      | Delivery         | In progress         |
      | TrackingNumber   | Track.#23-TSB       |
      | Description      | New Opportunity Description|
    Then Successful message appear with Opportunity name
    And All Opportunity headers match with previous fields
    And Opportunity details match with previous fields


  Scenario: create a new Opportunity with only required fields
    Given I login to Salesforce site as a developer user
    When I navigate to "Opportunity" page
    When I create a new Opportunity with fields
      | OpportunityName | New opportunity     |
      | CloseDate       | 7/7/2021          |
      | Stage           | Prospecting         |
    Then Successful message appear with Opportunity name
    And All Opportunity headers match with previous fields
    And Opportunity details match with previous fields