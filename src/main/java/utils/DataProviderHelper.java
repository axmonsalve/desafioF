package main.java.utils;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class DataProviderHelper {

    @DataProvider(name = "LoginData")
    public Object [][] getLoginData() throws IOException, IOException {
        String path = System.getProperty("user.dir") + File.separator + "datafiles" + File.separator + "loginData.xlsx";
        ExcelUtilities excelUtility = new ExcelUtilities(path);

        int totalRows = excelUtility.getRowCount("testlogin");
        int totalCols = excelUtility.getCellCount("testlogin", 1);

        Object[][] loginData = new Object[totalRows][totalCols];

        for(int i = 1; i <= totalRows; i++) {
            for(int j = 0; j < totalCols; j++) {
                loginData[i-1][j] = excelUtility.getCellData("testlogin", i, j);
            }
        }

        return loginData;
    }
}
