package test.firefox;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ConfProperties;
import pages.LogInForm;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAuthorizationFirefoxTest {

    public static LogInForm logInForm;
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        logInForm = new LogInForm(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
        driver.switchTo().frame("framelive");
    }

    @Test
    @Tag("Firefox")
    @DisplayName("1. Wrong Credential Authentification Failed Test")
    public void testInvalidCreds() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(logInForm.signInLink));
        logInForm.signInLink.click();
        logInForm.emailInput.sendKeys("wrongcredentialstest@test.com");
        logInForm.passwordInput.sendKeys("justfortesting");
        logInForm.submitBtn.click();
        assertThat(logInForm.alertMessage.isDisplayed());
    }

    @Test
    @Tag("Firefox")
    @DisplayName("2. Password Recovery Test")
    public void shouldRecoverPassword() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(logInForm.signInLink));
        logInForm.signInLink.click();
        logInForm.forgotPasswordButton.click();
        wait.until(ExpectedConditions.visibilityOf(logInForm.recoverPassEmailfield));
        logInForm.recoverPassEmailfield.sendKeys("wrongcredentialstest@test.com");
        logInForm.resetPassLinkButton.click();
        wait.until(ExpectedConditions.visibilityOf(logInForm.recoveryMessage));
        String recoveryMessage = logInForm.recoveryMessage.getText();
        assertThat(recoveryMessage).contains("If this email address has been registered in our store, you will receive a link to reset your password at");

    }

}