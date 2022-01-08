package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShopProductPageHelper extends HelperBase {

    public ShopProductPageHelper(WebDriver wd) {
        super(wd);
    }

    public void selectSize(By locator, String size) {
        click(locator);
        select(locator, size);
    }


    public void inputMerchandiseQuantity(String number) {
        type(By.name("quantity"), number);
    }

    public void addMerchandiseIntoCart() {
        click(By.name("add_cart_product"));
    }
}
