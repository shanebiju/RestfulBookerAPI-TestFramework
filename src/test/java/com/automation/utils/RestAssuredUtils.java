package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtils {
    static RequestSpecification requestSpecification= RestAssured.given();
    static Response response;
    static String endpoint;

    public static void setEndPoint(String endPoint){
        endpoint=endPoint;
    }

    public static void setHeader(String key,String value){
        requestSpecification.header(key,value);
    }

    public static void setBody(Object pojo){
        requestSpecification.body(pojo);
    }

    public static void post(){
        requestSpecification.when().log().all();
        response=requestSpecification.post(endpoint);
        response.then().log().all();
    }

    public static void get(){
        requestSpecification.when().log().all();
        response=requestSpecification.get(endpoint);
        response.then().log().all();
    }

    public static int getStatusCode(){
        return response.getStatusCode();
    }

    public static Response getResponse(){
        return response;
    }
}
