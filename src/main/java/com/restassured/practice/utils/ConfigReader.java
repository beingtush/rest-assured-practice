package com.restassured.practice.utils;

public class ConfigReader {
    
    // Base URLs for different APIs
    public static final String JSONPLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String REQRES_BASE_URL = "https://reqres.in/api";
    public static final String HTTPBIN_BASE_URL = "https://httpbin.org";
    public static final String RESTCOUNTRIES_BASE_URL = "https://restcountries.com/v3.1";
    
    // Timeouts
    public static final int DEFAULT_TIMEOUT = 5000;
    
    // Common headers
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String ACCEPT_JSON = "application/json";
}
