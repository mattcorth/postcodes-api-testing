package com.mattcorth.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostcodesApiHelper {
    private static final String BASE_URI = ApiConfig.getBaseUri();
    private static final String POSTCODES_PATH = ApiConfig.getPostcodes();
    private static final String TERMINATED_POSTCODES_PATH = ApiConfig.getTerminatedPostcodes();
    private static final String SCOTTISH_POSTCODES_PATH = ApiConfig.getScottishPostcodes();
    private static final String OUTWARD_CODES_PATH = ApiConfig.getOutwardCodes();
    private static final String PLACES_PATH = ApiConfig.getPlaces();

    private static Response response;

    private static RequestSpecBuilder getBaseSpecBuilder(String path) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(path);
    }

    public static RequestSpecification getPostcodeSpec(String postcode) {
        return getBaseSpecBuilder(POSTCODES_PATH)
                .addPathParam("postcode", postcode)
                .build();
    }

    public static Response getPostcode(String postcode) {
        RestAssured.registerParser("text/html", Parser.JSON);
        return response = RestAssured
                .given()
                    .spec(getPostcodeSpec(postcode))
                .when()
                    .get()
                .then()
                    //.log().all()
                    .extract().response();

    }
}
