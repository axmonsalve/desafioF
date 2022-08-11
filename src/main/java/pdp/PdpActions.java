package main.java.pdp;

import com.aventstack.extentreports.MediaEntityBuilder;
import main.java.shoppingCart.ShoppingCartActions;
import main.java.utils.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.BaseTests;

import java.time.Duration;

public class PdpActions extends PdpObjects{
    WebDriver driver;
    Actions action;
    WebDriverWait wait;
    String screenshotTemp = "";

    public PdpActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public WebElement getPdpPrice(){
        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Comparing PDP price vs list of product price",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());
        return wait.until(ExpectedConditions.visibilityOf(pdpPrice));
    }

    public ShoppingCartActions clickOnAgregarAlCarroButton(){
        pdpTalla.click();
        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Selecting size",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());

        if(pdpAgregarAlCarroButton.isEnabled()){
            screenshotTemp = Common.getScreenshot(driver);
            BaseTests.logger.info("Click on 'Agregar al Carro'",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());
            pdpAgregarAlCarroButton.click();
        }


        return new ShoppingCartActions(driver);
    }
}
