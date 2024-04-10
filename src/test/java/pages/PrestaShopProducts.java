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

public class PrestaShopProducts {
    public WebDriver driver;

    public PrestaShopProducts(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='search_widget']/form/input[2]" )
    public WebElement searchInCatalog;

    @FindBy (xpath = "//*[@id='js-product-list']/div[1]/div[1]/article/div/div[2]/h2")
    public WebElement firstItemInSearchResult;
}
