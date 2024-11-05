Feature: Validate create and update booking end point

  Scenario: Verify user can create and update booking
    Given user wants to call "/booking" endpoint
    And set header "Content-Type" to "application/json"
    And set request body from "create_booking.json"
    When user sends a post request
    Then verify status code is 200
    And verify the response schema with "create_booking_response_schema.json"
    And  verify the response body has the same data as request
    And save "bookingid" as "booking.id"
    Given user wants to call "/auth" endpoint
    And set header "Content-Type" to "application/json"
    And set request body with username "admin" and password "password123"
    When user sends a post request
    Then verify status code is 200
    And verify the response schema with "create_token_schema.json"
    And save "token" as "api.token"
    When user wants to call "/booking/@id" endpoint
    And set header "Content-Type" to "application/json"
    And set header "Accept" to "application/json"
    And set header "Cookie" to "token=@token"
    And set request body from "create_booking.json" with "additionalneeds" as "KANYE"
    When user sends a put request
    Then verify status code is 200
    And verify the response schema with "update_booking_schema.json"
    And verify response body has same data as request for update
    Given user wants to call "/booking/@id" endpoint
    And set header "Cookie" to "token=@token"
    When user sends a delete request
    Then verify status code is 201
    Given user wants to call "/booking/@id" endpoint
    When user sends a get request
    Then verify status code is 404