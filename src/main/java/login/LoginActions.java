package main.java.login;

import com.aventstack.extentreports.MediaEntityBuilder;
import main.java.utils.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.BaseTests;

import java.time.Duration;

public class LoginActions extends LoginObjects {
    WebDriver driver;
    Actions action;
    WebDriverWait wait;
    String screenshotTemp = "";

    public LoginActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public LoginActions login(String email, String password) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(ingresarButton)).click();
        if (loSentimosLoginMessage.isDisplayed()){
            Thread.sleep(3000);
            ingresarButton.click();
        }

        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Filling login form fileds",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());
        return new LoginActions(driver);
    }

    public String typeWrongUser(){
        emailInput.sendKeys(" " + Keys.TAB);
        return getEmailErrorMessage().getText();
    }

    public String typeWrongPassword(){
        passwordInput.sendKeys("" + Keys.TAB);
        return getPasswordErrorMessage().getText();
    }

    public WebElement popUpFpay() {
        return wait.until(ExpectedConditions.visibilityOf(fPayPopUp));
    }

    public void closePopUpFpay() {
        fPayPopUpEnOtroMomentoText.click();
    }

    public WebElement getEmailInput(){
        return wait.until(ExpectedConditions.visibilityOf(emailInput));
    }

    public WebElement getPasswordInput(){
        return wait.until(ExpectedConditions.visibilityOf(passwordInput));
    }

    public WebElement getLoginButton(){
        return wait.until(ExpectedConditions.visibilityOf(ingresarButton));
    }

    public WebElement getEmailErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOf(invalidEmailMessage));
    }
    public WebElement getPasswordErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOf(invalidPasswordMessage));
    }




}
