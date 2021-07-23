Feature: Create opportunity

  Scenario: create an opportunity with required fields.
    Given I login to Salesforce site as an admin user
    And I navigate to Opportunity page
    When I create a new Opportunity with fields
    | opportunityName | new name UNIQUE_ID |
    | closeDate       | DATE_TODAY |
    | stage           | Prospecting |
    Then A successful message is display
    Then All opportunity fields headers matches
    And I check details fields
    Then All fields matches
    When I check Opportunity title
    Then The title matches


    Scenario: create a new Opportunity with only required fields
      Given I login to Salesforce site with valid credentials
      | username |
      | password |
      And navigate to Opportunity page selecting new
      When I create a new Opportunity with fields
        | opportunityName | new name UNIQUE_ID  |
        | closeDate       | DATE_TODAY          |
        | stage           | Prospecting         |
      Then Successful message appear with Opportunity name
      And All Opporunity fields headers