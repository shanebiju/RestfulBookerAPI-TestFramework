package com.automation.steps;

import com.automation.pojo.CreateBookingRequestPojo;
import com.automation.pojo.CreateTokenRequestPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.Constants;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.lang.reflect.Field;

public class RequestSteps {
    @Given("user wants to call {string} endpoint")
    public void user_wants_to_call_endpoint(String endpoint) {
        RestAssuredUtils.clear();
        if(endpoint.contains("@id")){
            endpoint=endpoint.replace("@id",ConfigReader.getProperty("booking.id"));
        }
        RestAssuredUtils.setEndPoint(endpoint);
    }

    @Then("save {string} as {string}")
    public void save_as(String jsonPath, String ConfigKey) {
        String value=RestAssuredUtils.getFieldValueFromResponse(jsonPath);
        ConfigReader.setProperty(ConfigKey,value);
    }

    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        if(value.contains("@token")){
            value=value.replace("@token",ConfigReader.getProperty("api.token"));
        }
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
    @Given("user wants to call {string} endpoint with id {string}")
    public void user_wants_to_call_endpoint_with_id(String url, String key) {
        String id=ConfigReader.getProperty(key);
        RestAssuredUtils.setEndPoint(url+"/"+id);
    }

    @Given("set request body with username {string} and password {string}")
    public void set_request_body_with_username_and_password(String username, String password) {
        CreateTokenRequestPojo createTokenRequestPojo=new CreateTokenRequestPojo();
        createTokenRequestPojo.setUsername(username);
        createTokenRequestPojo.setPassword(password);
        RestAssuredUtils.setBody(createTokenRequestPojo);
    }

    @When("set request body from {string} with {string} as {string}")
    public void set_request_body_from_with_as(String jsonFile, String fieldName, String value) {
        try{
            String content=RestAssuredUtils.getDataFromJson(jsonFile);
            System.out.println(content);
            ObjectMapper mapper=new ObjectMapper();
            CreateBookingRequestPojo createBookingRequestPojo=mapper.readValue(content, CreateBookingRequestPojo.class);
            Field field=CreateBookingRequestPojo.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            if(Constants.createBookingReqInt.contains(fieldName)){
                field.set(createBookingRequestPojo,value);
            }else{
                field.set(createBookingRequestPojo,Integer.parseInt(value));
            }
            RestAssuredUtils.setBody(createBookingRequestPojo);
            ConfigReader.setObject("updatedRequestBody",createBookingRequestPojo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("user sends a put request")
    public void user_sends_a_put_request() {
        RestAssuredUtils.put();
    }

    @When("user sends a delete request")
    public void user_sends_a_delete_request() {
        RestAssuredUtils.delete();
    }
}
