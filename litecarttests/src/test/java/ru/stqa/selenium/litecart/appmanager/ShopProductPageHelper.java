package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;

public class ShopProductPageHelper extends HelperBase {

    public ShopProductPageHelper(WebDriver wd) {
        super(wd);
    }

    public List<WebElement> getNumberOfGoods() {
        List<WebElement> goods = getElementsList(By.xpath("//div[@class='image-wrapper']"));
        return goods;
    }


    public boolean isSizePickerPresent() {
        return isElementPresent(By.xpath("//select[@name='options[Size]']"));
    }


    public void selectSize(String size) {
        click(By.xpath("//select[@name='options[Size]']"));
        select(By.xpath("//select[@name='options[Size]']"), size);
    }

    public void checkGoodsQuantityInCart(String attribute, String expectedValue) {
        WebDriverWait wait = new WebDriverWait(wd, 10/*seconds*/);
        wait.until(attributeContains(By.xpath("//span[@class='quantity']"), attribute, expectedValue));
    }

    public void inputMerchandiseQuantity(String number) {
        type(By.name("quantity"), number);
    }

    public void addMerchandiseIntoCart() {
        click(By.name("add_cart_product"));
    }
}
