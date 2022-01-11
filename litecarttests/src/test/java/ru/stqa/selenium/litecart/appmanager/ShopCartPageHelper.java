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

    public void dropMerchandiseFromCart() {
        isElementClickable(By.name("remove_cart_item"));
        WebElement element = wd.findElement(By.name("remove_cart_item"));
        element.click();
        isElementDeleted(element);
    }

    public int getNumberOfGoodsInCart() {
        int goodsInCart = getElementsList(By.xpath("//a[@class='image-wrapper shadow']")).size();
        return goodsInCart;
    }

    public List<WebElement> getGoodsInCard() {
        return getElementsList(By.xpath("//ul[@class='shortcuts']//a"));
    }

    public boolean isThereMultipleNumberOfGoodsInCart() {
        return isElementPresent(By.xpath("//ul[@class='shortcuts']"));
    }
}
