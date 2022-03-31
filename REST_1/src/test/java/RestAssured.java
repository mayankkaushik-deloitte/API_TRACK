import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class RestAssured {
    @Test
    public void get_call(){
        given().
                baseUri("https://jsonplaceholder.typicode.com").header("Content-Type","application/json").
        when().
                get("/posts").
        then().
                statusCode(200).body("userId[39]",equalTo(4)).body("title[39]",equalTo("enim quo cumque"));
    }
    @Test
    public void put_call(){
        File JsonData=new File("C:\\Users\\mayakaushik\\API_TRACK\\REST_1\\src\\test\\resources\\JsonData.json");
        given().
                baseUri("https://reqres.in/api").
                body(JsonData).
                header("Content-Type","application/json").
                when().
                put("/users").
                then().
                statusCode(200).body("name",equalTo("Arun")).body("job",equalTo("Manager"));
    }
}
