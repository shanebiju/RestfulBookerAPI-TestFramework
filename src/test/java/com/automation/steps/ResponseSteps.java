package com.automation.steps;

import com.automation.pojo.CreateBookingRequestPojo;
import com.automation.pojo.CreateBookingResponsePojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;

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
    }
}
