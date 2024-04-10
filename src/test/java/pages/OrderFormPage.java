package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderFormPage {

    public WebDriver driver;

    public OrderFormPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='checkout-personal-information-step']/h1")
    public WebElement personalInfosForm;

    @FindBy(xpath = "//*[@id='field-id_gender-1']" )
    public WebElement socialTitle;

    @FindBy (xpath = "//*[@id='field-firstname']")
    public WebElement firstName;

    @FindBy (xpath = "//*[@id='field-lastname']")
    public WebElement lastName;

    @FindBy (xpath = "//*[@id='field-email']")
    public WebElement emailField;

    @FindBy (xpath = "//*[@id='customer-form']/div/div[8]/div[1]/span")
    public WebElement agreeCheckbox;

    @FindBy (xpath = "//*[@id='customer-form']/div/div[10]/div[1]/span")
    public  WebElement customerDataCheckbox;

    @FindBy (css = ".btn.btn-primary.float-xs-right")
    public WebElement continueButton;

    @FindBy (xpath = "//*[@id='field-address1']" )
    public WebElement addressField;

    @FindBy (xpath = "//*[@id='field-postcode']" )
    public WebElement zipCode;

    @FindBy(xpath = "//*[@id='field-city']" )
    public WebElement cityName;

    @FindBy(xpath = "//*[@id='js-delivery']/button" )
    public WebElement deliveryButton;

    @FindBy (xpath = "//*[@id='payment-option-1']")
    public WebElement bankWireSelecting;

    @FindBy(xpath = "//*[@id='conditions_to_approve[terms-and-conditions]']")
    public WebElement adreedTherms;

    @FindBy(xpath = "//*[@id='payment-confirmation']/div[1]/button")
    public WebElement placeOrder;

    @FindBy (xpath = "//*[@id='content-hook_order_confirmation']/div/div/div/h3")
    public WebElement orderPlacingMessage;
}
