package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.util.Scanner;

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

    public static void put(){
        requestSpecification.when().log().all();
        response=requestSpecification.put(endpoint);
        response.then().log().all();
    }

    public static void delete(){
        requestSpecification.when().log().all();
        response=requestSpecification.delete(endpoint);
        response.then().log().all();
    }

    public static int getStatusCode(){
        return response.getStatusCode();
    }

    public static String getDataFromJson(String fileName){
        String filePath="src/test/resources/data/";
        String data="";
        try{
            Scanner sc=new Scanner(new FileInputStream(filePath+fileName));
            data=sc.useDelimiter("\\Z").next();
        } catch (Exception e) {
            System.out.println("Unable to load file");
        }
        return data;
    }

    public static Response getResponse(){
        return response;
    }

    public static String getFieldValueFromResponse(String jsonPath){
       return  response.jsonPath().getString(jsonPath);
    }

    public static void clear(){
        requestSpecification=RestAssured.given();
    }

}
