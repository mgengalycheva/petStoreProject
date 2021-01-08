package api.steps;

import helpers.Helpers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.serenitybdd.rest.RestRequests.given;

public class ApiSteps {

    Helpers helpers = new Helpers();

    public RequestSpecification requestSpecification = helpers.getRequestSpecification();

    public Logger logger = LogManager.getLogger(ApiSteps.class);

    @Step("create pet and check by id")
    public void postGetPetById() {

        logger.info("start test");

        String path = "/pet";
        Random random = new Random();
        int petId = 1 + random.nextInt(1000 - 1);

        Map<String, String> pet = new HashMap<>();
        pet.put("id", Integer.toString(petId));
        pet.put("name", "French Bulldog");

        // create new pet
        Response response = given().spec(requestSpecification).body(pet).when().post(path);
        logger.warn("created new pet");
        response.prettyPrint();
        int newCreatedPetId = response.then().extract().path("id");

        // get created pet by id
        helpers.getResponse("/pet/" + newCreatedPetId).then().statusCode(200);
        logger.warn("get created pet by id");

        // delete created pet by id
        given().spec(requestSpecification).when().delete("/pet/" + newCreatedPetId).then().statusCode(200);
        logger.warn("delete created pet");

        logger.info("finish test");
    }

    @Step("create pet and change by id")
    public void postPutPetById() {

        logger.info("start test");

        String path = "/pet";
        Random random = new Random();
        int petId = 1 + random.nextInt(1000 - 1);

        Map<String, String> pet = new HashMap<>();
        pet.put("id", Integer.toString(petId));
        pet.put("name", "Labrador");
        pet.put("status", "available");

        // create new pet
        Response response = given().spec(requestSpecification).body(pet).when().post(path);
        response.prettyPrint();
        logger.warn("created new pet");
        int newCreatedPetId = response.then().extract().path("id");

        // edit created pet by ID
        Map<String, String> editPet = new HashMap<>();
        editPet.put("id", Integer.toString(newCreatedPetId));
        editPet.put("name", "Golden Retriever");
        editPet.put("status", "not available");

        Response newResponse = given().spec(requestSpecification).body(editPet).when().put(path);
        newResponse.prettyPrint();
        logger.warn("edit created pet");

        // delete created pet by ID
        given().spec(requestSpecification).when().delete("/pet/" + newCreatedPetId).then().statusCode(200);
        logger.warn("delete created pet");

        logger.info("finish test");
    }
}
