package main.java.register;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterObjects {

    @FindBy(id = "testId-Input-firstName")
    protected WebElement nombreInput;
    @FindBy(xpath = "//span[contains(text(),'Ingresa un nombre v')]")
    protected WebElement invalidNombreMessage;


    @FindBy(id = "testId-Input-lastName")
    protected WebElement primerApellidoInput;
    @FindBy(xpath = "//span[contains(text(),'Ingresa un apellido v')]")
    protected WebElement invalidApellidoMessage;

    @FindBy(id = "testId-Input-document")
    protected WebElement rutInput;
    @FindBy(xpath = "//span[contains(text(),'de documento v')]")
    protected WebElement invalidRutMessage;

    @FindBy(id = "testId-Input-phoneNumber")
    protected WebElement celularInput;
    @FindBy(xpath = "//span[contains(text(),'mero de celular v')]")
    protected WebElement invalidPhoneMessage;

    @FindBy(id = "testId-Input-email")
    protected WebElement correoInput;
    @FindBy(xpath = "//span[contains(text(),'nico v')]")
    protected WebElement invalidEmailMessage;

    @FindBy(name = "password")
    protected WebElement passwordInput;

    @FindBy(xpath = "//input[@id='testId-consent-PdP-eco_consentTemplateRegistroTyC_FAL_CL-input']")
    protected WebElement aceptoTerminoCheckbox;

    @FindBy(xpath = "//input[@id='testId-consent-PdP-BU_consentTemplateRegistroPdP_FAL_CL-input']")
    protected WebElement autorizoCheckbox;

    @FindBy(id = "testId-Button-submit")
    protected WebElement registrameButton;
}
