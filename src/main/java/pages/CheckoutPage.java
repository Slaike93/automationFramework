package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="#billing_first_name")
    private WebElement firstName;

    @FindBy(css="#billing_last_name")
    private WebElement lastName;

    @FindBy(css="#select2-billing_country-container")
    private WebElement country;

    @FindBy(css = "#billing_address_1")
    private WebElement address;

    @FindBy(css= "#billing_postcode")
    private WebElement zipcode;

    @FindBy(css = "#billing_city")
    private WebElement town;

    @FindBy(css = "#order_review > table > tfoot > tr.order-total > td > strong > span > bdi")
    private WebElement totalAmount;

    @FindBy(css = "#place_order")
    private WebElement placeOrder;

    public void provideBillingDetails(){
        //actions.moveToElement(address);
        //actions.perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(address));
        address.sendKeys("abc");
    }

    public String getTotalAmount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(totalAmount));
        return totalAmount.getText();
    }

    public void placeOrder(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrder);
    }


}
