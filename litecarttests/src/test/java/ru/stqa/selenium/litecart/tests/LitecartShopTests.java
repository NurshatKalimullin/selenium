package ru.stqa.selenium.litecart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static ru.stqa.selenium.litecart.tests.TestBase.app;

public class LitecartShopTests {


    @Test
    public void testGoodsStickers() {
        List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//div[@class='image-wrapper']"));
        for (WebElement element : elements) {
            List<WebElement> stickers = element.findElements(By.xpath("./div[contains(@class, 'sticker')]"));
            assertEquals(stickers.size(), 1);
        }
    }


}
