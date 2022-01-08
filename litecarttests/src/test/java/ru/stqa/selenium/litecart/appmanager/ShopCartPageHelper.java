package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShopCartPageHelper extends HelperBase {

    public ShopCartPageHelper(WebDriver wd) {
        super(wd);
    }

    public void chooseMerchandiseToDelete(List<WebElement> elements, int index) {
        clickByIndex(elements, index);
    }

    public void removeMerchandiseFromCart() {
        isElementClickable(By.name("remove_cart_item"));
        WebElement element = wd.findElement(By.name("remove_cart_item"));
        element.click();
        isElementDeleted(element);
    }
}
