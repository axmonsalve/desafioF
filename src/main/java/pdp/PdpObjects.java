package main.java.pdp;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PdpObjects {
    @FindBy(xpath = "//div[@data-variant='PDP_MAIN']//span[1]")
    protected WebElement pdpPrice;

    @FindBy(xpath = "//span[text()='Selecciona Talla']/parent::div/following-sibling::div//button[not(@disabled='')][1]")
    protected WebElement pdpTalla;

    @FindBy(xpath = "//button[text()='Agregar al Carro']")
    protected WebElement pdpAgregarAlCarroButton;
}
