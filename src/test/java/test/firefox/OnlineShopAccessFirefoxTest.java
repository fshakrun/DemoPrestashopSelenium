package test.firefox;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ConfProperties;
import pages.LogInForm;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import static org.testng.Assert.assertEquals;

public class OnlineShopAccessFirefoxTest {

    public static LogInForm logInForm;
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setUp() throws InterruptedException, TimeoutException {

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        logInForm = new LogInForm(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
        driver.switchTo().frame("framelive");
    }


    @Test
    @Tag("Firefox")
    @DisplayName("0. Online Shop Availablility Test")
    public void shouldOpenOnlineShop() throws TimeoutException {
        wait.until(ExpectedConditions.visibilityOf(logInForm.signInLink));
        String expectedTitle = "PrestaShop Live Demo";
        String pageTitle = driver.getTitle();
        assertEquals(expectedTitle, pageTitle);
    }
}
