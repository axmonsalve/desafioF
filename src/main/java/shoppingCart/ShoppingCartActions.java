package main.java.shoppingCart;

import com.aventstack.extentreports.MediaEntityBuilder;
import main.java.utils.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.BaseTests;

import java.time.Duration;

public class ShoppingCartActions extends ShoppingCartObjects{
    WebDriver driver;
    Actions action;
    WebDriverWait wait;
    String screenshotTemp = "";

    public ShoppingCartActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public WebElement getPriceInShoppingCart(){
        return wait.until(ExpectedConditions.visibilityOf(productPriceShoppingCart));
    }
}
