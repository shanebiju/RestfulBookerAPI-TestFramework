package com.automation.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    static Properties properties;

    public static void initConfig(){
        try{
            properties=new Properties();
            properties.load(new FileInputStream("src/test/resources/config/config.properties"));
        } catch (Exception e) {
            System.out.println("Unable to load config file");
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }

    public static void setProperty(String key,String value){
        properties.setProperty(key, value);
    }

    public static void setObject(String key,Object value){
        properties.put(key,value);
    }

    public static Object getObject(String key){
        return properties.get(key);
    }
}
