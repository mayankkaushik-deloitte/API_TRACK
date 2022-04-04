import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class addTaskTest extends BaseClass{
    @Test
    public void addTask() throws IOException {
        System.out.println("================Add Task==================");
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com/task";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        FileInputStream inputStream = new FileInputStream("C:\\Users\\mayakaushik\\API_TRACK\\REST_MAIN\\src\\data\\Add20Task.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getLastCellNum();
        String description = null;
        String task = null;
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j == 0) {
                    description = sheet.getRow(i).getCell(j).getStringCellValue();
                }
                if (j == 1) {
                    task = sheet.getRow(i).getCell(j).getStringCellValue();
                }
            }
            String addTaskJson = "{\n" +
                    "\t\""+description+"\": \""+task+"\"\n" +
                    "}";
            Response responseAddTask = request.body(addTaskJson).post();
            String jsonString = responseAddTask.getBody().asString();
            String task1 = JsonPath.from(jsonString).get("data.description");
            System.out.println(task1);
            if (task.equals(task1)){
                System.out.println("Task is validated");
            }
            else{
                System.out.println("Invalid task");
            }
            responseAddTask.prettyPrint();
            wb.close();
            inputStream.close();
        }
    }
}
