package test.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.utils.Common;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTests {
    public static WebDriver driver;
    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    public static String url;

    @BeforeTest()
    public void beforeTestMethod() {

        htmlReporter = new ExtentSparkReporter(
                System.getProperty("user.dir") + File.separator + "reports" + File.separator + "AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report QA Desafio Falabella");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Axel Monsalve");
    }
    @BeforeMethod()
    @Parameters(value = { "browserName" })
    public void beforeMethodMethod(@Optional("chrome") String browserName, Method testMethod, ITestContext context) throws IOException {
        InputStream input = new FileInputStream("src/config/config.properties");
        Properties prop = new Properties();
        prop.load(input);
        url = prop.getProperty("URL");
        logger = extent.createTest(testMethod.getName());
        setUpDriver(browserName); // Este parametro se invoca en el testng
        driver.manage().window().maximize();
//        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod()
    public void afterMethodMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.SUCCESS) {
            // Vamos creando el html del reporte como exitoso
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            // Vamos creando el html del reporte como fallido
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Failed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            // Tomamos un screenshot y lo guardamos para logearlo en el reporte
            String temp = Common.getScreenshot(driver);
            logger.fail(result.getThrowable().getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
            logger.log(Status.FAIL, m);
        } else if (result.getStatus() == ITestResult.SKIP) {
            // Si se salta el test, esto loggea su detalle en el reporte
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Skipped";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.AMBER);
            logger.log(Status.SKIP, m);
        }
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @AfterTest()
    public void afterTestMethod() {
        extent.flush();
    }

    public void setUpDriver(String browserName) throws MalformedURLException {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--incognito");
//            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();
            driver = new EdgeDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }
}
