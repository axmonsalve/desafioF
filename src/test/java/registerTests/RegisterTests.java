package test.java.registerTests;

import com.aventstack.extentreports.MediaEntityBuilder;
import main.java.home.HomeActions;
import main.java.register.RegisterActions;
import main.java.register.RegisterObjects;
import main.java.registerSuccess.RegisterSuccessActions;
import main.java.utils.Common;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.java.BaseTests;

import java.io.IOException;
import java.util.Random;

public class RegisterTests extends BaseTests {
    String screenshotTemp;
    HomeActions homeActions;
    RegisterActions registerActions;

    @Test()
    public void checkRegisterFormFieldsAreDisplayed(){
        homeActions = new HomeActions(driver);
        homeActions.closePopUpHome();
        registerActions = homeActions.clickOnRegistrate();
        Assert.assertTrue(registerActions.getNombreInput().isDisplayed());
        Assert.assertTrue(registerActions.getApellidoInput().isDisplayed());
        Assert.assertTrue(registerActions.getRutInput().isDisplayed());
        Assert.assertTrue(registerActions.getCeluarInput().isDisplayed());
        Assert.assertTrue(registerActions.getEmailInput().isDisplayed());
        Assert.assertTrue(registerActions.getPasswordInput().isDisplayed());
        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Verifying if register form fields are displayed",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());
    }

    @Test()
    public void registerOnSiteSuccessfully() throws IOException {
        homeActions = new HomeActions(driver);
        homeActions.closePopUpHome();
        registerActions = homeActions.clickOnRegistrate();
        RegisterSuccessActions registerSuccessActions = registerActions.fillRegisterForm("Juanito", "Perez");
//        Assert.assertTrue(registerActions.getRegistrameButton().isEnabled());
        String welcomeMesgge = registerSuccessActions.getTitleSuccess().getText();

        Assert.assertEquals(welcomeMesgge, "Te damos la bienvenida a falabella.com");
        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Verifying title on register success",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());

    }

    @Test()
    public void registerOnSiteWithErrosInFields(){
        homeActions = new HomeActions(driver);
        homeActions.closePopUpHome();
        registerActions = homeActions.clickOnRegistrate();

        Assert.assertTrue(registerActions.fillInvalidName().contains("Ingresa un nombre v"));
        Assert.assertTrue(registerActions.fillInvalidLastName().contains("Ingresa un apellido v"));
        Assert.assertTrue(registerActions.fillInvalidRut().contains("de documento v"));
        Assert.assertTrue(registerActions.fillInvalidPhone().contains("mero de celular v"));
        Assert.assertTrue(registerActions.fillInvalidEmail().contains("nico v"));
    }
}
