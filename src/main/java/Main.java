import drivers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import utils.Constants;
import utils.FrameworkProperties;
import utils.NavigationHelper;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws Exception {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));
        WebDriver driver = DriverSingleton.getDriver();
        driver.get("https://bitheap.tech");

        HomePage homePage = new HomePage();
        SignInPage signInPage = new SignInPage();
        ShopPage shopPage = new ShopPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();
        OrderConfirmed orderConfirmed = new OrderConfirmed();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.fc-consent-root > div.fc-dialog-container > div.fc-dialog.fc-choice-dialog > div.fc-footer-buttons-container > div.fc-footer-buttons > button.fc-button.fc-cta-consent.fc-primary-button")));
        cookieButton.click();

        //TEST ACCESSO
            homePage.clickSignIn();
            signInPage.logIn(frameworkProperties.getProperty((Constants.EMAIL)), frameworkProperties.getProperty(Constants.PASSWORD));

            if(homePage.getUsername().equals("Hello, Laurentiu")){
                System.out.println("Test Passed");
            } else {
                System.out.println("Test Failed");
            }

        //wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        //WebElement avviso = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > p > a.woocommerce-store-notice__dismiss-link")));
        //avviso.click();

        //TEST AGGIUNTA AL CARRELLO
            homePage.clickShopButton();
            //(Scelta mia) Verifica che la pagina sia quella giusta
            if (NavigationHelper.isCurrentPage(driver, "menu-item-1310")){
                System.out.println("Shop page is the current page");
            } else System.out.println("Incorrect page");
            shopPage.goToPageTwo();
            shopPage.addFirstElementToCart();
            shopPage.proceedToCheckout();

        //TEST CHECKOUT
            cartPage.procedeToCheckout();
            checkoutPage.provideBillingDetails();
            checkoutPage.placeOrder();
            Thread.sleep(5000); //Per qualche motivo è necessario

            if(orderConfirmed.getOrderStatus().equals("Order received")){
                System.out.println("Test passed");
            } else throw new Exception();

        DriverSingleton.closeObjectInstance();
    }
}
