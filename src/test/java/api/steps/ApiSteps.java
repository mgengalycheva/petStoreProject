package api.steps;

import helpers.Helpers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static net.serenitybdd.rest.RestRequests.given;

public class ApiSteps {

    Helpers helpers = new Helpers();

    public RequestSpecification requestSpecification = helpers.getRequestSpecification();

    @Step("create pet and check by id")
    public void postGetPetById() {


        String path = "/pet";
        Random random = new Random();
        int petId = 1 + random.nextInt(1000 - 1);

        Map<String, String> pet = new HashMap<>();
        pet.put("id", "" + petId + "");
        pet.put("name", "type dog");


        // create pet
        Response response = given().spec(requestSpecification).body(pet).when().post(path);
        response.prettyPrint();
        int newCreatedPetId = response.then().extract().path("id");

        // get pet id
        helpers.getResponse("/pet/" + newCreatedPetId).then().statusCode(200);

        // delete pet by id
        given().spec(requestSpecification).when().delete("/pet/" + newCreatedPetId).then().statusCode(200);

    }
}
