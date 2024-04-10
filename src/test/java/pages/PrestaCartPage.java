package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PrestaCartPage {
    public WebDriver driver;

    public PrestaCartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (css = "#content > section:nth-child(2) > div > div:nth-child(1)")
    public WebElement itemAdd;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement addToCard;

    @FindBy (xpath = "//button[@type='submit']" )
    public WebElement addToCartButton;

    @FindBy (xpath = "//button[@type='button']" )
    public WebElement proceedToBasketButton;

    @FindBy (css = ".btn.btn-primary")
    public WebElement reallyProceedToCheckout;

    @FindBy (xpath = "//*[@id='blockcart-modal']/div/div")
    public WebElement popUp;

    @FindBy(css ="#_desktop_cart > div > div")
    public WebElement goToCart;
    @FindBy (css = ".logo.img-fluid" )
    public WebElement shopLogo;

    @FindBy (xpath ="//span[text()='Cart']" )
    public WebElement cartGo;

    @FindBy (css = ".btn.btn-primary")
    public WebElement finallyProceedToCheckout;

}

