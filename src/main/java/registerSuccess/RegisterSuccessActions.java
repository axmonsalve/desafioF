package main.java.registerSuccess;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class RegisterSuccessActions extends RegisterSuccessObjects{
    WebDriver driver;
    Actions action;
    WebDriverWait wait;
    String screenshotTemp = "";
    Random rnd = new Random(System.currentTimeMillis());


    public RegisterSuccessActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    //Capturar te damos la bienvenida a falabella.com
    public WebElement getTitleSuccess(){
        return wait.until(ExpectedConditions.visibilityOf(welcomeMessaggeTag));
    }
}
