import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(ExtentReport.class)

public class GetTaskTest extends BaseClass {
    @Test
    public void getTask(){
        System.out.println(token);

        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com/task";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer "+ token)
                .header("Content-Type","application/json");
        Response responseGetTask = request.get();
        responseGetTask.prettyPrint();
    }
}
