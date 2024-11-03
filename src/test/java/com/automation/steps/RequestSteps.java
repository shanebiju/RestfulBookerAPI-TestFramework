package com.automation.steps;

import com.automation.pojo.CreateBookingRequestPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;

public class RequestSteps {
    @Given("user wants to call {string} endpoint")
    public void user_wants_to_call_endpoint(String endpoint) {
        RestAssuredUtils.setEndPoint(endpoint);
    }
    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        RestAssuredUtils.setHeader(key, value);
    }
    @Given("set request body from {string}")
    public void set_request_body_from(String fileName) {
        String filePath="src/test/resources/data/";
        ObjectMapper mapper=new ObjectMapper();
        try{
            CreateBookingRequestPojo pojoReq=mapper.readValue(new File(filePath+fileName), CreateBookingRequestPojo.class);
            ConfigReader.setObject("CreateBookingReq",pojoReq);
            RestAssuredUtils.setBody(pojoReq);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @When("user sends a post request")
    public void user_sends_a_post_request() {
        RestAssuredUtils.post();
    }

    @Given("user wants to get booking with id {string}")
    public void user_wants_to_get_booking_with_id(String string) {

    }
    @When("user sends a get request")
    public void user_sends_a_get_request() {
        RestAssuredUtils.get();
    }
}
