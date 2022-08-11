package main.java.home;

import com.aventstack.extentreports.MediaEntityBuilder;
import main.java.login.LoginActions;
import main.java.pdp.PdpActions;
import main.java.register.RegisterActions;
import main.java.utils.Common;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.BaseTests;

import java.time.Duration;

public class HomeActions extends HomeObjects {
    WebDriver driver;
    Actions action;
    WebDriverWait wait;
    String screenshotTemp = "";

    public HomeActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    private void hoverUserMenu() {
        action = new Actions(driver);
        action.moveToElement(userInfoButton).perform();
        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Open user menu",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());
    }

    public RegisterActions clickOnRegistrate() {
        hoverUserMenu();
        registrateButton.click();
        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Click on 'Registrate'",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());
        return new RegisterActions(driver);
    }

    public WebElement popUp() {
            return wait.until(ExpectedConditions.visibilityOf(popUpHome));
    }

    public void closePopUpHome() {
        try {
            wait.until(ExpectedConditions.visibilityOf(popUpHome));
            wait.until(ExpectedConditions.visibilityOf(closePopUpHomeButton)).click();
            System.out.println("Si se abrio el popup");
        } catch (Exception e) {
            System.out.println("NO se abrio el popup");
            System.out.println(e);
        }
    }

    public LoginActions clickOnIniciarSesion(){
        hoverUserMenu();
        iniciaSesionButton.click();
        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Click on 'Iniciar sesion'",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());
        return new LoginActions(driver);
    }

    public String getFirstProductPrice(){
        return driver.findElement(By.xpath("(//div[@id='testId-searchResults-products']//div)[1]//li//span")).getText();
    }

    public void searchProduct(){
        inputSearch.sendKeys("zapatos" + Keys.ENTER);
        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Searching a product",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());
    }

    public PdpActions clickOnFirstProduct(){
        WebElement product = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@id='testId-searchResults-products']//div)[1]//img")))) ;
        product.click();
        return new PdpActions(driver);
        //retornar a pdp
    }

}
