Feature: Create opportunity

  @CreateAccount @CreateCampaign
  Scenario: create a new Opportunity with all fields
    Given I login to Salesforce site as an developer user
    When I navigate to "Opportunity" page
    When I create a new Opportunity with fields
      | Private          | true                        |
      | Amount           | 123                         |
      | CloseDate        | 7/6/2021                    |
      | OpportunityName  | New Opportunity             |
      | NextStep         | Dev.#23                     |
      | Account          | Opportunity Account         |
      | Stage            | Prospecting                 |
      | Type             | New Customer                |
      | Probability      | 10                          |
      | LeadSource       | Web                         |
      | Campaign         | Opportunity Campaign        |
      | OrderNumber      | Order#12                    |
      | MainCompetitor   | Sony Inc.                   |
      | CurrentGenerator | Number#34-TB                |
      | Delivery         | In progress                 |
      | TrackingNumber   | Track.#23-TSB               |
      | Description      | New Opportunity Description |
    Then A Successful message appear with created Opportunity name
    And All Opportunity headers should match with previous fields
    And Created Opportunity details should match with previous fields

  Scenario: create a new Opportunity with only required fields
    Given I login to Salesforce site as a developer user
    When I navigate to "Opportunity" page
    When I create a new Opportunity with fields
      | CloseDate        | 7/6/2021                    |
      | OpportunityName  | New Opportunity             |
      | Stage            | Prospecting                 |
    Then A Successful message appear with created Opportunity name
    And All Opportunity headers should match with previous fields
    And Created Opportunity details should match with previous fields
