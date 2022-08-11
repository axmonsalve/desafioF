package main.java.home;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeObjects {

    @FindBy(id = "testId-UserAction-userinfo")
    protected WebElement userInfoButton;

    @FindBy(id = "testId-loggedout-item-1")
    protected WebElement registrateButton;

    @FindBy(id = "testId-loggedout-item-0")
    protected WebElement iniciaSesionButton;

    @FindBy(xpath = "//div[@data-wrapper='MO-Popup']")
    protected WebElement popUpHome;

    //div[@style='visibility: visible;']//div[@class='dy-lb-close']

    @FindBy(xpath = "//div[@class='dy-modal-contents']/child::div[@class='dy-lb-close']")
    protected WebElement closePopUpHomeButton;

    @FindBy(id = "testId-SearchBar-Input")
    protected WebElement inputSearch;

}
