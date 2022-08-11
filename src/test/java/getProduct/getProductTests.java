package test.java.getProduct;

import com.aventstack.extentreports.MediaEntityBuilder;
import main.java.home.HomeActions;
import main.java.pdp.PdpActions;
import main.java.shoppingCart.ShoppingCartActions;
import main.java.utils.Common;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.java.BaseTests;

public class getProductTests extends BaseTests {
    String screenshotTemp;

    @Test
    public void SearchProductAndCompareItsPriceBetweenPages(){
        HomeActions homeActions = new HomeActions(driver);
        if(homeActions.popUp().isDisplayed()){
            homeActions.closePopUpHome();
        }
        homeActions.searchProduct();
        String productPrice = homeActions.getFirstProductPrice();
        PdpActions pdpActions = homeActions.clickOnFirstProduct();
        String pdpPrice = pdpActions.getPdpPrice().getText();
        Assert.assertEquals(productPrice, pdpPrice, "Prices doesn't match");

        ShoppingCartActions shoppingCartActions = pdpActions.clickOnAgregarAlCarroButton();
        Assert.assertEquals(shoppingCartActions.getPriceInShoppingCart().getText(), pdpPrice);
        screenshotTemp = Common.getScreenshot(driver);
        BaseTests.logger.info("Comparing PDP price vs shopping cart price",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotTemp).build());

    }
}
