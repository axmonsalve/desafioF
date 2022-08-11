package main.java.register;

import com.aventstack.extentreports.MediaEntityBuilder;
import main.java.registerSuccess.RegisterSuccessActions;
import main.java.utils.Common;
import main.java.utils.ExcelUtilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.BaseTests;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class RegisterActions extends RegisterObjects {
    WebDriver driver;
    Actions action;
    WebDriverWait wait;
    String screenshotTemp = "";
    Random rnd = new Random(System.currentTimeMillis());


    public RegisterActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public WebElement getRegistrameButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(registrameButton));
    }

    public RegisterSuccessActions fillRegisterForm(String firstName, String lastName) throws IOException {
        ExcelUtilities excelUtilities = new ExcelUtilities("datafiles/loginData.xlsx");
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        nombreInput.sendKeys(firstName);
        primerApellidoInput.sendKeys(lastName);

        Random rndNumberRut = new Random(System.currentTimeMillis());
        int intRndLimit = rndNumberRut.nextInt(25000000);
        String completeRut = generateRutWithDV(intRndLimit);
        excelUtilities.setCellData("testlogin", 1, 2, completeRut);

        rutInput.sendKeys(completeRut);

        Random rndNumberPhone = new Random(System.currentTimeMillis());
        int intRndLimitPhone = rndNumberPhone.nextInt(999999999);
        String phoneNumberStr = String.valueOf(intRndLimitPhone);
        System.out.println(phoneNumberStr);
        celularInput.sendKeys(phoneNumberStr);
        excelUtilities.setCellData("testlogin", 1, 3, phoneNumberStr);

        Random rndEmail = new Random(System.currentTimeMillis());
        int intRndLimitEmail = rndEmail.nextInt(200000);
        String emailStr = String.valueOf(intRndLimitEmail) + "@automation.te";
        correoInput.sendKeys(emailStr);
        excelUtilities.setCellData("testlogin", 1, 4, emailStr);

        Random rndPassword = new Random(System.currentTimeMillis());
        int intRndLimitPass = rndPassword.nextInt(999999999);
        String passStr = String.valueOf(intRndLimitPass) + "Test";
        passwordInput.sendKeys(passStr);
        excelUtilities.setCellData("testlogin", 1, 5, passStr);

        executor.executeScript("arguments[0].click();", aceptoTerminoCheckbox);
        executor.executeScript("arguments[0].click();", autorizoCheckbox);

        executor.executeScript("arguments[0].scrollIntoView(true)", primerApellidoInput);

        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Filling register form successfully",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());

        //CLICK EN ENVIAR BUTTON
        executor.executeScript("arguments[0].click();", registrameButton);
        return new RegisterSuccessActions(driver);
    }

    public String fillInvalidName() {
        Random rndNumber = new Random(System.currentTimeMillis());
        int intRndNumber = rndNumber.nextInt(99999);
        String invalidName = String.valueOf(intRndNumber);
        nombreInput.sendKeys(invalidName + Keys.TAB);

        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Typing wrong user name",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());
        return wait.until(ExpectedConditions.visibilityOf(invalidNombreMessage)).getText();
    }

    public String fillInvalidLastName() {
        int intRnd = rnd.nextInt(6666312);
        String invalidLastName = String.valueOf(intRnd);
        primerApellidoInput.sendKeys(invalidLastName + Keys.TAB);

        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Typing worng user last name",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());

        return wait.until(ExpectedConditions.visibilityOf(invalidApellidoMessage)).getText();
    }

    public String fillInvalidRut() {
        int intRnd = rnd.nextInt(35);
        String invalidRut = String.valueOf(intRnd);
        rutInput.sendKeys(invalidRut + Keys.TAB);

        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Typing worng user RUT",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());

        return wait.until(ExpectedConditions.visibilityOf(invalidRutMessage)).getText();
    }

    public String fillInvalidPhone() {
        int intRnd = rnd.nextInt(35);
        String invalidPhone = String.valueOf(intRnd);
        celularInput.sendKeys(invalidPhone + Keys.TAB);

        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Typing worng user phone number",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());

        return wait.until(ExpectedConditions.visibilityOf(invalidPhoneMessage)).getText();
    }

    public String fillInvalidEmail() {
        int intRnd = rnd.nextInt(377415);
        String invalidEmail = String.valueOf(intRnd) + "@correo";
        correoInput.sendKeys(invalidEmail + Keys.TAB);

        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Typing worng user email",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());

        return wait.until(ExpectedConditions.visibilityOf(invalidEmailMessage)).getText();
    }

/*    public String fillInvalidPassword(){
        int intRnd = rnd.nextInt(35);
        String invalidPass = String.valueOf(intRnd);
        passwordInput.sendKeys(invalidPass + Keys.TAB);

        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Typing worng user password",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());

        return wait.until(ExpectedConditions.visibilityOf(invalidEmailMessage)).getText();
    }*/


    public String generateRutWithDV(int rut) {
        int number = rut;
        int reverse = 0;
        while (number != 0) {
            int remainder = number % 10;
            reverse = reverse * 10 + remainder;
            number = number / 10;
        }

        int[] intTab = String.valueOf(reverse).chars().map(Character::getNumericValue).toArray();

        int addedRut = 0;
        int a = 2;
        for (int i = 0; i < intTab.length; i++) {
            intTab[i] = intTab[i] * a;
            addedRut += Integer.parseInt(String.valueOf(intTab[i]));
            if (a == 7) {
                a = 1;
            }
            a++;
        }
        int rest = addedRut % 11;
        String digit = String.valueOf(11 - rest);

        if (digit.equals("11")) {
            digit = "0";
        }

        if (digit.equals("10")) {
            digit = "K";
        }

        String rutComplete = String.valueOf(rut) + "" + digit;

        return rutComplete;
    }

    public WebElement getNombreInput(){
        return wait.until(ExpectedConditions.visibilityOf(nombreInput));
    }

    public WebElement getApellidoInput(){
        return wait.until(ExpectedConditions.visibilityOf(primerApellidoInput));
    }

    public WebElement getRutInput(){
        return wait.until(ExpectedConditions.visibilityOf(rutInput));
    }

    public WebElement getCeluarInput(){
        return wait.until(ExpectedConditions.visibilityOf(celularInput));
    }

    public WebElement getEmailInput(){
        return wait.until(ExpectedConditions.visibilityOf(correoInput));
    }

    public WebElement getPasswordInput(){
        return wait.until(ExpectedConditions.visibilityOf(passwordInput));
    }


}
