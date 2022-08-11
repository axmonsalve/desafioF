package main.java.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        return new LoginActions(driver);
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

}
