package test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.function.BooleanSupplier;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Description;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPathTest {


    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }


    @Test
    @DisplayName("1. Online Shop Availablility Test")
    public void shouldOpenOnlineShop() throws InterruptedException {


        driver.get("https://demo.prestashop.com/#/en/front");

        Thread.sleep(11000);

        driver.switchTo().frame("framelive");

        String expectedTitle = "PrestaShop Live Demo";
        String pageTitle = driver.getTitle();

        assertEquals(expectedTitle, pageTitle);

        driver.quit();
    }

    @Test
    @DisplayName("2. Placing Order Test")
    public void shouldPlace1Order() throws InterruptedException, AWTException, IOException {


        driver.get("https://demo.prestashop.com/#/en/front");

        Thread.sleep(11000);

        driver.switchTo().frame("framelive");

        WebElement itemAdd = driver.findElement(By.cssSelector("#content > section:nth-child(2) > div > div:nth-child(1)"));
        itemAdd.click();
        WebElement addToCard = driver.findElement(By.xpath("//button[@type='submit']"));
        addToCard.click();
        WebElement proceedToBasket = driver.findElement(By.xpath("//button[@type='button']"));
        proceedToBasket.click();
        WebElement reallyProceedToCheckout = driver.findElement(By.cssSelector(".btn.btn-primary"));
        reallyProceedToCheckout.click();
        Thread.sleep(1100);
        Robot robot = new Robot();
        robot.keyPress((KeyEvent.VK_ESCAPE));
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        Thread.sleep(1100);
        // Возвращаемся к списку товаров для проверки того что выбранный нами товар остался в корзине
        WebElement cart = driver.findElement(By.cssSelector(".logo.img-fluid"));
        cart.click();
        WebElement cartGo = driver.findElement(By.xpath("//span[text()='Cart']"));
        cartGo.click();
        WebElement finallyProceedToCheckout = driver.findElement(By.cssSelector(".btn.btn-primary"));
        finallyProceedToCheckout.click();
        // Заполнение формы валидными пользовательскими данными
        WebElement socialTitle = driver.findElement(By.xpath("//*[@id='field-id_gender-1']"));
        socialTitle.click();
        WebElement firstName = driver.findElement(By.xpath("//*[@id='field-firstname']"));
        firstName.sendKeys("Antonin");
        WebElement lastName = driver.findElement(By.xpath("//*[@id='field-lastname']"));
        lastName.sendKeys("Artaud");
        WebElement emailField = driver.findElement((By.xpath("//*[@id='field-email']")));
        emailField.sendKeys("anartaud@fortest.net");
        WebElement agreeCheckbox = driver.findElement(By.xpath("//*[@id='customer-form']/div/div[8]/div[1]/span"));
        agreeCheckbox.click();
        WebElement customerDataCheckbox = driver.findElement(By.xpath("//*[@id='customer-form']/div/div[10]/div[1]/span"));
        customerDataCheckbox.click();
        WebElement continueButton = driver.findElement(By.cssSelector(".btn.btn-primary.float-xs-right"));
        continueButton.click();
        WebElement addressField = driver.findElement(By.xpath("//*[@id='field-address1']"));
        addressField.sendKeys("31 rue de Gagarin");
        WebElement zipCode = driver.findElement(By.xpath("//*[@id='field-postcode']"));
        zipCode.sendKeys("75000");
        WebElement cityName = driver.findElement(By.xpath("//*[@id='field-city']"));
        cityName.sendKeys("Cergy");
        Robot robotClick = new Robot();
        // Проверка нажатия кнопок с помощью клавиатуры
        robotClick.keyPress((KeyEvent.VK_ENTER));
        robotClick.keyRelease(KeyEvent.VK_ENTER);
        Robot robotClickToContinue = new Robot();
        robotClickToContinue.keyPress((KeyEvent.VK_ENTER));
        robotClickToContinue.keyRelease(KeyEvent.VK_ENTER);
        WebElement deliveryButton = driver.findElement(By.xpath("//*[@id='js-delivery']/button"));
        deliveryButton.click();

        WebElement bankWireSelecting = driver.findElement(By.xpath("//*[@id='payment-option-1']"));
        bankWireSelecting.click();
        WebElement adreedTherms = driver.findElement(By.xpath("//*[@id='conditions_to_approve[terms-and-conditions]']"));
        adreedTherms.click();
        WebElement placeOrder = driver.findElement(By.xpath("//*[@id='payment-confirmation']/div[1]/button"));
        placeOrder.click();

        Thread.sleep(5000);
        // Проверка наличия сообщения об успешном подтверждении заказа и его скриншот
        String succes = driver.findElement(By.xpath("//*[@id='content-hook_order_confirmation']/div/div/div/h3")).getText();
        assertThat(succes).contains("YOUR ORDER IS CONFIRMED");
        // Скриншот заказа
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(src, new File("OrderPageScreenshot.png"));

        driver.quit();

    }


    @Test
    @DisplayName("3. Wrong Credential Authentification Failed Test")
    public void testInvalidCreds() throws InterruptedException {


        driver.get("https://demo.prestashop.com/#/en/front");

        Thread.sleep(11000);

        driver.switchTo().frame("framelive");

        WebElement signInLink = driver.findElement(By.xpath("//span[text()='Sign in']"));

        signInLink.click();

        WebElement emailInput = driver.findElement(By.xpath("//input[@autocomplete='email']"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@aria-label='Password input']"));
        WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        // Ввод преднамеренно невалидных пользовательских данных
        emailInput.sendKeys("wrongcredentialstest@test.com");
        passwordInput.sendKeys("justfortesting");
        submitBtn.click();

        String alertMessage = driver.findElement(By.className("alert-danger")).getText();

        assertThat(alertMessage).contains("Authentication failed.");

        driver.quit();
    }

    @Test
    @DisplayName("4.Searching Items Relevant Result Check")
    public void shouldBeRelevantResult() throws InterruptedException, AWTException, IOException {


        driver.get("https://demo.prestashop.com/#/en/front");

        Thread.sleep(11000);

        driver.switchTo().frame("framelive");

        WebElement seacrchInCatalog = driver.findElement(By.xpath("//*[@id='search_widget']/form/input[2]"));

        // Ввод названия товара

        seacrchInCatalog.sendKeys("Mug");

        Robot robotClickToSearch = new Robot();
        robotClickToSearch.keyPress((KeyEvent.VK_ENTER));
        robotClickToSearch.keyRelease(KeyEvent.VK_ENTER);
        // Проверка релевантности выдачи пользовательскому запросу
        String searchResult = driver.findElement(By.xpath("//*[@id='js-product-list']/div[1]/div[1]/article/div/div[2]/h2")).getText();
        assertThat(searchResult).contains("Mug");

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(src, new File("SearchItemsResult.png"));

        driver.quit();
    }

    @Test
    @DisplayName("5. Password Recovery Test")
    public void shouldRecoverPassword() throws InterruptedException {


        driver.get("https://demo.prestashop.com/#/en/front");

        Thread.sleep(11000);

        driver.switchTo().frame("framelive");

        WebElement signInLink = driver.findElement(By.xpath("//span[text()='Sign in']"));
        signInLink.click();
        WebElement forgotPassword = driver.findElement(By.xpath("//*[@id='login-form']/div/div[3]"));
        forgotPassword.click();
        WebElement emailAdress = driver.findElement(By.xpath("//*[@id='email']"));
        emailAdress.sendKeys("wrongcredentialstest@test.com");
        WebElement sendLinkButton = driver.findElement(By.xpath("//button[@type='submit']"));
        sendLinkButton.click();
        String recoveryMessage = driver.findElement(By.xpath("//*[@id='content']/ul/li/p")).getText();
        assertThat(recoveryMessage).contains("If this email address has been registered in our store, you will receive a link to reset your password at");

        driver.quit();
    }
}

//    @Test
//    @DisplayName(" Valid Credential Authentification Passed Test")
//    public void shouldLoginValidCredentials() throws InterruptedException {
//
//
//        driver.get("https://demo.prestashop.com/#/en/front");
//
//        Thread.sleep(11000);
//
//        driver.switchTo().frame("framelive");
//
//        WebElement signInLink = driver.findElement(By.xpath("//span[text()='Sign in']"));
//
//        signInLink.click();
//
//        WebElement emailField = driver.findElement(By.xpath("//input[@autocomplete='email']"));
//        WebElement passwordField = driver.findElement(By.xpath("//input[@aria-label='Password input']"));
//        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
//
//        emailField.sendKeys("yomaheev@gmail.com");
//        passwordField.sendKeys("/*46233dfd");
//        submitButton.click();
//
//        String alertMessage = driver.findElement(By.className("user-info")).getText();
//
//        assertThat(alertMessage).contains("Sign out");
//
//        driver.quit();
//    }