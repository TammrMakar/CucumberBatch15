package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODUxOTk5NDAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTI0MzE0MCwidXNlcklkIjoiNTMwMSJ9.ZxpGvjO-iRlaU9YyG2bANMPMVCSW84W2ZXY9IJcaFtI";
    static String employee_id;

    @Test
    public void bgetCreatedEmployee(){
    RequestSpecification preparedRequest = given().
            header("Content-Type","application/json").
            header("Authorization",token).
            queryParam("employee_id",employee_id);

    //hitting the endpoint
        Response response= preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        //verify the response
        response.then().assertThat().statusCode(200);

        String tempEmId= response.jsonPath().getString("employee.employee_id");

        //we have 2 emp id, one is global and second is local
        Assert.assertEquals(employee_id,tempEmId);
    }

    @Test
    public void acreateEmployee(){
        //prepare the request
        RequestSpecification preparedRequest = given().
                header("Content-Type","application/json").
                header("Authorization",token).
                body("{\n" +
                        "  \"emp_firstname\": \"Merina\",\n" +
                        "  \"emp_lastname\": \"Tim\",\n" +
                        "  \"emp_middle_name\": \"C\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2001-05-30\",\n" +
                        "  \"emp_status\": \"Confirm\",\n" +
                        "  \"emp_job_title\": \"Manager\"\n" +
                        "}");

        //hitting the endpoint/send the request
         Response response = preparedRequest.when().post("/createEmployee.php");
        //it will print the output in the console
        response.prettyPrint();
        //verifying the assertions
        response.then().assertThat().statusCode(201);

        //we are capturing employee id from the response
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

        //verifying the firstname in the response body
        response.then().assertThat().
                body("Employee.emp_firstname", equalTo("Bella")).
                body("Employee.emp_lastname",equalTo("SAM"));

        //verifying the response headers
        response.then().assertThat().header("Content-Type","application/json");
        System.out.println("My test case is passed");
    }

    //in homework, create a method to get all employee status and job title
    @Test
       public void cupdateEmployee(){
        RequestSpecification preparedRequest = given().
                header("Content-Type","application/json").
                header("Authorization",token).
                body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"Marina\",\n" +
                        "  \"emp_lastname\": \"Barnaba\",\n" +
                        "  \"emp_middle_name\": \"A\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2019-07-01\",\n" +
                        "  \"emp_status\": \"probation\",\n" +
                        "  \"emp_job_title\": \"CEO\"\n" +
                        "}");

           //hitting the endpoint
           Response response = preparedRequest.when().put("/updateEmployee.php");
           response.then().assertThat().statusCode(200);
           //for verification
           response.then().assertThat().body("Message",equalTo("Employee record Updated"));

   }

    @Test
    public void dgetUpdatedEmployee(){
        RequestSpecification preparedRequest = given().
                header("Content-Type","application/json").
                header("Authorization",token).
                queryParam("employee_id",employee_id);
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        //if you want to verify the body of the response.
        //you can do that using hamcrest matchers
}
}

