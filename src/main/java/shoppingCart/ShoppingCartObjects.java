package main.java.shoppingCart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartObjects {
    @FindBy(xpath = "//div[@data-variant='CART']//span[1]")
    protected WebElement productPriceShoppingCart;
}
