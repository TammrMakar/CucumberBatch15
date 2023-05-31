package APIStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {


    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification generateTokenRequest = given().
                header("Content-Type", "application/json").
                body("{\n" +
                        "  \"email\": \"tammr@test.com\",\n" +
                        "  \"password\": \"tammr123\"\n" +
                        "}");

        //hitting the endpoint
        Response response = generateTokenRequest.when().post("/generateToken.php");

        //storing the taken in global variable
        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);
    }


    }

