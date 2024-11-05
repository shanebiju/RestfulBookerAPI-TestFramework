Feature: Validate create booking endpoint
  Scenario: verify user can create booking
    Given user wants to call "/booking" endpoint
    And set header "Content-Type" to "application/json"
    And set request body from "create_booking.json"
    When user sends a post request
    Then verify status code is 200
    And verify the response schema with "create_booking_response_schema.json"
    And  verify the response body has the same data as request