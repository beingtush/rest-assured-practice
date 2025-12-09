package com.restassured.practice.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

/**
 * Centralized API configuration class for REST Assured specifications
 * Provides reusable request and response specifications
 */
public class ApiConfig {

    /**
     * Base request specification with common settings
     */
    public static RequestSpecification getDefaultRequestSpec(String baseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    /**
     * Request specification for JSONPlaceholder API
     */
    public static RequestSpecification getJsonPlaceholderRequestSpec() {
        return getDefaultRequestSpec("https://jsonplaceholder.typicode.com");
    }

    /**
     * Request specification for ReqRes API
     */
    public static RequestSpecification getReqResRequestSpec() {
        return getDefaultRequestSpec("https://reqres.in/api");
    }

    /**
     * Request specification for HTTPBin API
     */
    public static RequestSpecification getHttpBinRequestSpec() {
        return getDefaultRequestSpec("https://httpbin.org");
    }

    /**
     * Request specification for REST Countries API
     */
    public static RequestSpecification getRestCountriesRequestSpec() {
        return getDefaultRequestSpec("https://restcountries.com/v3.1");
    }

    /**
     * Default response specification
     * Validates that response time is less than 5 seconds
     */
    public static ResponseSpecification getDefaultResponseSpec() {
        return new ResponseSpecBuilder()
                .expectResponseTime(lessThan(5000L))
                .log(LogDetail.ALL)
                .build();
    }

    /**
     * Success response specification (2xx status codes)
     */
    public static ResponseSpecification getSuccessResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    /**
     * Created response specification (201 status code)
     */
    public static ResponseSpecification getCreatedResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectResponseTime(lessThan(3000L))
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
