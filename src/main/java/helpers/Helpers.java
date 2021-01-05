package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static net.serenitybdd.rest.RestRequests.given;

public class Helpers {

    public RequestSpecification getRequestSpecification() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .addHeader("Content-Type","application/json")
                .addHeader("Accept", "application/json")
                .build();

        return requestSpecification;
    }

    public Response getResponse(String path) {
        RequestSpecification requestSpecification = getRequestSpecification();
        Response response = given().spec(requestSpecification).when().get(path);

        return response;
    }

}
