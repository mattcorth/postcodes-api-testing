package com.mattcorth.stepdefs;

import com.mattcorth.pojos.PostcodeResponse;
import com.mattcorth.utils.PostcodesApiHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class lookupPostcodeStepdefs {

    private static Response response;
    private static PostcodeResponse postcodeResponse;

    @When("I lookup {string}")
    public void iLookupPostcode(String postcode) {
        response = PostcodesApiHelper.getPostcode(postcode);
        if (response.getStatusCode() == 200)
            postcodeResponse = response.as(PostcodeResponse.class);
    }

    @Then("The returned status code should be {int}")
    public void theReturnedStatusCodeShouldBe(int expectedStatusCode) {
        MatcherAssert.assertThat(response.getStatusCode(), Matchers.is(expectedStatusCode));
    }

    @When("I use a DELETE request on the lookup endpoint")
    public void iUseADELETERequestOnTheLookupEndpoint() {
        response = RestAssured
                .given()
                    .spec(PostcodesApiHelper.getPostcodeSpec("L77AJ"))
                .when()
                    .delete()
                .then()
                    .log().all()
                    .extract().response();
    }

    @When("I omit the postcode in the path parameter")
    public void iOmitThePostcodeInThePathParameter() {
        response = PostcodesApiHelper.getPostcode("");
    }
}
