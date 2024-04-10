package test.firefox;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.aspectj.bridge.Version.getText;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserPathFirefoxTest {

    public static LogInForm logInForm;

    public static PrestaCartPage prestaCartPage;

    public static OrderFormPage orderFormPage;

    public static PrestaShopProducts prestaShopProducts;
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setUp() throws Exception {

        // "--headless",

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        logInForm = new LogInForm(driver);
        prestaCartPage = new PrestaCartPage(driver);
        orderFormPage = new OrderFormPage(driver);
        prestaShopProducts = new PrestaShopProducts(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
        driver.switchTo().frame("framelive");

    }

    @Test
    @Tag("Firefox")
    @DisplayName("3. Placing Order Test")
    public void shouldPlace1Order() throws InterruptedException, AWTException, IOException {
        wait.until(ExpectedConditions.visibilityOf(prestaCartPage.itemAdd));
        prestaCartPage.itemAdd.click();
        prestaCartPage.addToCard.click();
        // Ждем появление всплывающего окна
        wait.until(ExpectedConditions.visibilityOf(prestaCartPage.popUp));
        // Закрываем всплывающее окно нажатием клавиши ESC
        Robot closePopUp = new Robot();
        closePopUp.keyPress(KeyEvent.VK_ESCAPE);
        closePopUp.keyRelease(KeyEvent.VK_ESCAPE);
        // Переходим в корзину
        wait.until(ExpectedConditions.visibilityOf(prestaCartPage.goToCart));
        prestaCartPage.goToCart.click();
        prestaCartPage.finallyProceedToCheckout.click();
        wait.until(ExpectedConditions.visibilityOf(orderFormPage.personalInfosForm));
        orderFormPage.socialTitle.click();
        orderFormPage.firstName.sendKeys("Antonin");
        orderFormPage.lastName.sendKeys("Artaud");
        orderFormPage.emailField.sendKeys("anartaud@fortest.net");
        orderFormPage.agreeCheckbox.click();
        orderFormPage.customerDataCheckbox.click();
        orderFormPage.continueButton.click();
        orderFormPage.addressField.sendKeys("31 rue de Gagarin");
        orderFormPage.zipCode.sendKeys("75000");
        orderFormPage.cityName.sendKeys("Cergy");
        Robot robotClick = new Robot();
        // Проверка нажатия кнопок с помощью клавиатуры
        robotClick.keyPress((KeyEvent.VK_ENTER));
        robotClick.keyRelease(KeyEvent.VK_ENTER);
        Robot robotClickToContinue = new Robot();
        robotClickToContinue.keyPress((KeyEvent.VK_ENTER));
        robotClickToContinue.keyRelease(KeyEvent.VK_ENTER);
        orderFormPage.deliveryButton.click();
        orderFormPage.bankWireSelecting.click();
        orderFormPage.adreedTherms.click();
        orderFormPage.placeOrder.click();

        // Проверка наличия сообщения об успешном подтверждении заказа и его скриншот
        wait.until(ExpectedConditions.visibilityOf(orderFormPage.orderPlacingMessage));
        String confirmationOrderMessage = orderFormPage.orderPlacingMessage.getText();
        assertThat(confirmationOrderMessage).contains("YOUR ORDER IS CONFIRMED");
        // Скриншот заказа
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(src, new File("OrderPageScreenshotFirefox.png"));
    }


    @Test
    @Tag("Firefox")
    @DisplayName("4. Searching Items Relevant Result Check")
    public void shouldBeRelevantResult() throws InterruptedException, AWTException, IOException {

        wait.until(ExpectedConditions.visibilityOf(prestaShopProducts.searchInCatalog));
        prestaShopProducts.searchInCatalog.click();

        // Ввод названия товара
        prestaShopProducts.searchInCatalog.sendKeys("Mug");
        Robot robotClickToSearch = new Robot();
        robotClickToSearch.keyPress((KeyEvent.VK_ENTER));
        robotClickToSearch.keyRelease(KeyEvent.VK_ENTER);

        // Проверка релевантности выдачи пользовательскому запросу
        String searchResult = (prestaShopProducts.firstItemInSearchResult).getText();
        assertThat(searchResult).contains("Mug");

        //Сохранение скриншота поисковой выдачи
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(src, new File("SearchItemsResultFirefox.png"));
    }
}

