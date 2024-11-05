Feature: Validate auth endpoint
  Scenario Outline: Verify token is generated for only valid credentials
    Given user wants to call "/auth" endpoint
    And set header "Content-Type" to "application/json"
    And set request body with username "<username>" and password "<password>"
    When user sends a post request
    Then verify status code is 200
    Then verify the response schema with "create_token_schema.json"
  Examples:
    |username|password|
    |    admin    |   password123 |
    |  username   |   password123 |
    |  username   |     password  |
    |   admin     |     password  |