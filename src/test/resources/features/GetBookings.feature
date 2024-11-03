Feature: Validate get bookings endpoint
  Scenario: Verify user can get bookings
    Given user wants to call "/booking" endpoint
    And set header "Accept" to "application/json"
    When user sends a get request
    Then verify status code is 200