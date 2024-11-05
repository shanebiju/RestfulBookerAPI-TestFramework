package com.automation.steps;

import com.automation.pojo.CreateBookingRequestPojo;
import com.automation.pojo.CreateBookingResponsePojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;

import static java.lang.String.valueOf;

public class ResponseSteps {
    @Then("verify status code is {int}")
    public void verify_status_code_is(Integer statusCode) {
        Assert.assertTrue(RestAssuredUtils.getStatusCode()==statusCode);
    }
    @Then("verify the response body has the same data as request")
    public void verify_the_response_body_has_the_same_data_as_request() {
        Response response= RestAssuredUtils.getResponse();
        CreateBookingResponsePojo resPojo=response.as(CreateBookingResponsePojo.class);
        CreateBookingRequestPojo reqPojo= (CreateBookingRequestPojo) ConfigReader.getObject("CreateBookingReq");
        Assert.assertTrue(reqPojo.equals(resPojo.getBooking()));
        ConfigReader.setProperty("booking.id",valueOf(resPojo.getBookingid()));
        ConfigReader.setObject("CreateBookingRes",resPojo);
    }

    @Then("verify the response body has the same data as create booking response")
    public void verify_the_response_body_has_the_same_data_as_create_booking_response() {
        Response response= RestAssuredUtils.getResponse();
        CreateBookingResponsePojo resPojo=response.as(CreateBookingResponsePojo.class);
        CreateBookingResponsePojo createBookingResponsePojo=(CreateBookingResponsePojo) ConfigReader.getObject("CreateBookingRes");
        Assert.assertTrue(resPojo.equals(createBookingResponsePojo));

    }

    @Then("verify the response schema with {string}")
    public void verify_the_response_schema_with(String fileName) {
        Response response=RestAssuredUtils.getResponse();
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("data/"+fileName));
    }

    @Then("verify response body has same data as request for update")
    public void verify_response_body_has_same_data_as_request_for_update() {
        Response response=RestAssuredUtils.getResponse();
        CreateBookingRequestPojo responsePojo=response.as(CreateBookingRequestPojo.class);
        CreateBookingRequestPojo requestPojo=(CreateBookingRequestPojo) ConfigReader.getObject("updatedRequestBody");
        Assert.assertTrue(requestPojo.equals(responsePojo));
    }
}
