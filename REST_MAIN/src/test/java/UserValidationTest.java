import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(ExtentReport.class)

public class UserValidationTest extends BaseClass{
    @Test
    public void validateUser(){
        System.out.println(token);

        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com/user/me";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer "+ token)
                .header("Content-Type","application/json");
        Response responseValidateUser = request.get();
        responseValidateUser.prettyPrint();
    }
}
