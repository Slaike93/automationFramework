package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import java.time.Duration;

public class OrderConfirmed {
    private WebDriver driver;

    @FindBy(css = "#post-207 > header > h1")
    private WebElement orderStatus;

    public OrderConfirmed(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    public String getOrderStatus(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(orderStatus));
        return orderStatus.getText();
    }
}
