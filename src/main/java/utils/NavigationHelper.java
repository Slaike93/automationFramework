package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//Verifica che la selezione del menu ricolleghi alla pagina corretta sfruttando le proprietà dell'elemento
public class NavigationHelper {
    public static boolean isCurrentPage(WebDriver driver, String expectedPage){
        WebElement item = driver.findElement(By.id(expectedPage));
        String classAttribute = item.getAttribute("class");
        return classAttribute.contains("current-menu-item") && classAttribute.contains("current_page_item");
    }
}
