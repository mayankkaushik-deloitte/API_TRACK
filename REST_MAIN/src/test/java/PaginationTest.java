import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(ExtentReport.class)

public class PaginationTest extends BaseClass{
    @Test(priority = 1)
    public void paginationFor2(){
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
        RequestSpecification request2 = RestAssured.given();
        request2.header("Authorization","Bearer "+ tokenGenerated)
                .header("Content-Type","application/json");
        Response response2 = request2.get("/task?limit=2");
        response2.prettyPrint();
    }
    @Test(priority = 2)
    public void paginationFor5(){
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
        RequestSpecification request5 = RestAssured.given();
        request5.header("Authorization","Bearer "+ tokenGenerated)
                .header("Content-Type","application/json");
        Response response5 = request5.get("/task?limit=5");
        response5.prettyPrint();
    }
    @Test(priority = 3)
    public void paginationFor10(){
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
        RequestSpecification request10 = RestAssured.given();
        request10.header("Authorization","Bearer "+ tokenGenerated)
                .header("Content-Type","application/json");
        Response response10 = request10.get("/task?limit=10");
        response10.prettyPrint();
    }
}
