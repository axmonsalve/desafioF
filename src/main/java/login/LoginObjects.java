package main.java.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginObjects {

    @FindBy(id = "testId-cc-login-form-email-input")
    protected WebElement emailInput;

    @FindBy(id = "testId-cc-login-form-password-input")
    protected WebElement passwordInput;

    @FindBy(id = "testId-cc-login-form-submit")
    protected WebElement ingresarButton;

    @FindBy(xpath = "//div[@data-testid='testId-FPayIframe']")
    protected WebElement fPayPopUp;

    @FindBy(xpath = "//p[text()='En otro momento']")
    protected WebElement fPayPopUpEnOtroMomentoText;

    @FindBy(xpath = "//div[contains(text(),'Lo sentimos')]")
    protected WebElement loSentimosLoginMessage;

}
