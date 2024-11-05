Feature: Validate get booking by id
  Scenario: Verify user can get book by id
    Given user wants to call "/booking" endpoint with id "booking.id"
    And set header "Accept" to "application/json"
    When user sends a get request
    Then verify status code is 200
    And verify the response body has the same data as create booking response
    And verify response body has a field "" with value "Shane"