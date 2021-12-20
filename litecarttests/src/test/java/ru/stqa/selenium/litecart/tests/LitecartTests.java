package ru.stqa.selenium.litecart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LitecartTests extends TestBase {

    @Test
    public void testMenuNavigation(){
        List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
        String[] headers = {" Template", " Catalog", " Countries", " Currencies", " Customers", " Geo Zones", " Languages", " Job Modules", " Orders", " Pages", " Monthly Sales", " Settings",
        " Slides", " Tax Classes", " Search Translations", " Users", " vQmods"};
        for (int i = 0; i < elements.size(); i++) {
            app.getNavigationHelper().clickOnMenuItem(elements, i);
            elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
            assertTrue(app.getNavigationHelper().isElementPresent(
                    By.xpath(String.format("//h1[contains(text(),'%s')]", headers[i]))));
        }
    }

    @Test
    public void testGoodsStickers() {
        String[] boxes = {"box-most-popular", "box-campaigns", "box-latest-products"};
        for (String box : boxes ) {
            List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath(String.format("//div[@id='%s']//li[@class='product column shadow hover-light']", box)));
            for (WebElement element : elements) {
                List<WebElement> stickers = element.findElements(By.xpath(".//div[contains(@class, 'sticker')]"));
                System.out.println(stickers.size());
                assertEquals(stickers.size(), 1);
            }
        }
    }
}
