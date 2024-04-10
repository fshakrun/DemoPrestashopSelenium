package test.chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ConfProperties;
import pages.LogInForm;

import java.io.IOException;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class OnlineShopAvailibilityChromeTest {

    public static LogInForm logInForm;
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setUp() throws Exception {
        // "--headless",
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        logInForm = new LogInForm(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
        driver.switchTo().frame("framelive");


    }

    @Test
    @Tag("Chrome")
    @DisplayName("0. Online Shop Availablility Test")
    public void shouldOpenOnlineShop() throws InterruptedException, IOException {
        wait.until(ExpectedConditions.visibilityOf(logInForm.signInLink));
        String expectedTitle = "PrestaShop Live Demo";
        String pageTitle = driver.getTitle();
        assertEquals(expectedTitle, pageTitle);
        driver.quit();
    }
}
