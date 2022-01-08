package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ShopHomePageHelper extends HelperBase {

    public ShopHomePageHelper(WebDriver wd) {
        super(wd);
    }

    public void openMerchandisePage(List<WebElement> merchandise, int i) {
        clickByIndex(merchandise, i);
    }

    public void goToShopHomePage() {
        click(By.xpath("//i[@class='fa fa-home']"));
    }

    public void openShopCart() {
        click(By.xpath("//div[@id='cart']"));
    }
}
