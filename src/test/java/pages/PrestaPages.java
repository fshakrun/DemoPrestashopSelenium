//package pages;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//
//import org.openqa.selenium.support.PageFactory;
//
//import static org.openqa.selenium.By.cssSelector;
//import static org.openqa.selenium.By.xpath;
//
//
//public class PrestaPages {
//    public WebDriver driver;
//
//    public PrestaPages(WebDriver driver) {
//        PageFactory.initElements(driver, this);
//        this.driver = driver;
//    }
//
//    public final WebElement itemAdd;
//
//    {
//        assert false;
//        itemAdd = driver.findElement(By.cssSelector("#content > section:nth-child(2) > div > div:nth-child(1)"));
//    }
//
//    public final WebElement addToCart = driver.findElement(By.xpath("//button[@type='submit']"));
//    public final WebElement proceedToBasket = driver.findElement(By.xpath("//button[@type='button']"));
//    public final WebElement reallyProceedToCheckout = driver.findElement(By.cssSelector(".btn.btn-primary"));
//    public final WebElement cart = driver.findElement(By.cssSelector(".logo.img-fluid"));
//    public final WebElement cartGo = driver.findElement(By.xpath("//span[text()='Cart']"));
//    public final WebElement finallyProceedToCheckout = driver.findElement(By.cssSelector(".btn.btn-primary"));
//// Заполнение формы с личными данными и выбор платежных методов и методов доставки
//
//    public final WebElement socialTitle = driver.findElement(By.xpath("//*[@id='field-id_gender-1']"));
//    public final WebElement firstName = driver.findElement(By.xpath("//*[@id='field-firstname']"));
//    public final WebElement lastName = driver.findElement(By.xpath("//*[@id='field-lastname']"));
//    public final WebElement emailField = driver.findElement(By.xpath("//*[@id='field-email']"));
//    public final WebElement agreeCheckbox = driver.findElement(By.xpath("//*[@id='customer-form']/div/div[8]/div[1]/span"));
//    public final WebElement customerDataCheckbox = driver.findElement(By.xpath("//*[@id='customer-form']/div/div[10]/div[1]/span"));
//    public final WebElement continueButton = driver.findElement(By.cssSelector(".btn.btn-primary.float-xs-right"));
//    public final WebElement addressField = driver.findElement(By.xpath("//*[@id='field-address1']"));
//    public final WebElement zipCode = driver.findElement(By.xpath("//*[@id='field-postcode']"));
//    public final WebElement cityName = driver.findElement(By.xpath("//*[@id='field-city']"));
//    public final WebElement deliveryButton = driver.findElement(By.xpath("//*[@id='js-delivery']/button"));
//    public final WebElement bankWireSelecting = driver.findElement(By.xpath("//*[@id='payment-option-1']"));
//    public final WebElement adreedTherms = driver.findElement(By.xpath("//*[@id='conditions_to_approve[terms-and-conditions]']"));
//    public final WebElement placeOrder = driver.findElement(By.xpath("//*[@id='payment-confirmation']/div[1]/button"));
//}
//
