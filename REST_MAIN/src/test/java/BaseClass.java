import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BaseClass {

    private String username;
    private String email;
    private String password;
    public static String tokenGenerated;
    public static String token;

    @BeforeMethod
    public void registerUser() throws IOException {
        System.out.println(
                "=============REGISTER==============="
        );
        File file = new File("C:\\Users\\mayakaushik\\API_TRACK\\REST_MAIN\\src\\regDetails.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        XSSFRow row1 = sheet.getRow(1);
        XSSFCell cell1 = row1.getCell(0);
        XSSFCell cell2 = row1.getCell(1);
        XSSFCell cell3 = row1.getCell(2);
        XSSFCell cell4 = row1.getCell(3);
        username = cell1.getStringCellValue();
//        System.out.println(username);
        email = cell2.getStringCellValue();
//        System.out.println(email);
        password = cell3.getStringCellValue();
//        System.out.println(password);
    }

    @Test
    public void authenticationTest() throws IOException {
        System.out.println("===================AUTH TEST=====================");
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
        RequestSpecification request = RestAssured.given();
        String payload = getPayloadString(username,email,password);
        request.header("Content-Type", "application/json");
        Response responseFromGeneratedToken = request.body(payload).post("/user/register");
        responseFromGeneratedToken.prettyPrint();
        String jsonString = responseFromGeneratedToken.getBody().asString();
        tokenGenerated = JsonPath.from(jsonString).get("token");
        token = tokenGenerated;
        System.out.println(tokenGenerated);
        request.header("Authorization", "Bearer" + tokenGenerated)
                .header("Content-Type", "application/json");
        String loginDetails = "{\n" +
                "  \"email\" : \""+email+"\",\n" +
                "  \"password\" : \""+password+"\"\n" +
                "}";
        Response responseLogin = request.body(loginDetails).post("/user/login");
        responseLogin.prettyPrint();
    }

    private String getPayloadString(String username, String email, String password) {
        String payload = "{\n" +
                "  \"name\" : \""+username+"\",\n" +
                "  \"email\" : \""+email+"\",\n" +
                "  \"password\" : \""+password+"\"\n" +
                "}";
        return payload;
    }
}