package main.java.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Common {
    public static String getScreenshot(WebDriver driver) {
        String path = "reports" + File.separator + "screenshots" + File.separator + System.currentTimeMillis()+".png";

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(path);
            FileUtils.copyFile(src, destination);
        }catch(IOException e) {
            System.out.println("Capture Failed " + e.getMessage());
        }

        return path.substring(8);
    }
}
