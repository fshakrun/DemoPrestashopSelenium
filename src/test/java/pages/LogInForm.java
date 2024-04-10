package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInForm {

    public WebDriver driver;

    public LogInForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath = "//*[@id='loadingMessage']/img")
    public WebElement loader;

    @FindBy (className = "user-info")
    public WebElement signInLink;

    @FindBy (id = "field-email")
    public WebElement emailInput;

    @FindBy (id ="field-password" )
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitBtn;

    @FindBy (className = "alert-danger")
    public WebElement alertMessage;

    @FindBy (xpath = "//*[@id='login-form']/div/div[3]/a")
    public WebElement forgotPasswordButton;

    @FindBy (xpath = "//*[@id='email']")
    public WebElement recoverPassEmailfield;

    @FindBy (xpath = "//*[@id='send-reset-link']")
    public WebElement resetPassLinkButton;

    @FindBy (xpath = "//*[@id='content']/ul/li/p")
    public WebElement recoveryMessage;
}
