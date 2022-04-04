package UTILS;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
public class ReadTaskDetails {
    public static String JSONasString(String description, String task) throws InterruptedException, IOException {

        System.out.println(description+" "+" "+task);
        String res = "{\n" +
                    "\t\"" + description + ": \"" + task + "\"\n" +
                    "}";
        return res;
    }
}