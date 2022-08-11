package test.java.loginTests;

import com.aventstack.extentreports.MediaEntityBuilder;
import main.java.home.HomeActions;
import main.java.login.LoginActions;
import main.java.utils.Common;
import main.java.utils.DataProviderHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.java.BaseTests;

import java.util.NoSuchElementException;

public class LoginTests extends BaseTests {
    HomeActions homeActions;
    String screenshotTemp;

    @Test()
    public void verifyIfLoginFormAreDisplayed(){
        homeActions = new HomeActions(driver);
        homeActions.closePopUpHome();
        LoginActions loginActions = homeActions.clickOnIniciarSesion();
        Assert.assertTrue(loginActions.getEmailInput().isDisplayed());
        Assert.assertTrue(loginActions.getPasswordInput().isDisplayed());
        Assert.assertTrue(loginActions.getLoginButton().isDisplayed());
    }

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviderHelper.class)
    public void login(String nombre, String apellido, String rut, String celular, String email, String password) throws InterruptedException {
        homeActions = new HomeActions(driver);

        if (homeActions.popUp().isDisplayed()){
            try{
                homeActions.closePopUpHome();
            }catch (NoSuchElementException ex){
                System.out.println(ex);
            }
        }

        LoginActions loginActions = homeActions.clickOnIniciarSesion();
        loginActions.login(email, password);
        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Typing user credentials for login",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());

//        if (loginActions.popUpFpay().isDisplayed()){
//            try{
//                loginActions.closePopUpFpay();
//            }catch (NoSuchElementException ex){
//                System.out.println(ex);
//            }
//        }
    }
}
