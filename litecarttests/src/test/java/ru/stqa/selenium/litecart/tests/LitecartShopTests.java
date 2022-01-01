package ru.stqa.selenium.litecart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.selenium.litecart.model.CustomerData;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class LitecartShopTests extends ShopTestBase {


    @Test
    public void testGoodsStickers() {
        List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//div[@class='image-wrapper']"));
        for (WebElement element : elements) {
            List<WebElement> stickers = element.findElements(By.xpath("./div[contains(@class, 'sticker')]"));
            assertEquals(stickers.size(), 1);
        }
    }

    @Test
    public void testMerchandisePage() {
        String expectedLineThroughStyleValue = "line-through";
        String expectedMerchandiseCampaignPriceColor = "#cc0000";

        List<WebElement> goods = app.getNavigationHelper().getElementsList(By.xpath("//div[@id='box-campaigns']//li[contains(@class,'product')]"));
        String merchandiseName = app.getSessionHelper().getAttributeValue(goods.get(0).findElement(By.xpath(".//div[@class='name']")), "textContent");
        String merchandiseRegularPrice = app.getSessionHelper().getAttributeValue(goods.get(0).findElement(By.xpath(".//s[@class='regular-price']")), "textContent");
        String merchandiseCampaignPrice = app.getSessionHelper().getAttributeValue(goods.get(0).findElement(By.xpath(".//strong[@class='campaign-price']")), "textContent");
        String merchandiseRegularPriceColor = Color.fromString(goods.get(0).findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("color")).asHex();
        String merchandiseRegularPriceLine = goods.get(0).findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("text-decoration-line");
        String merchandiseCampaignPriceColor = Color.fromString(goods.get(0).findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("color")).asHex();
        String merchandiseCampaignPriceStyle = goods.get(0).findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-weight");
        String merchandiseRegularPriceSizeString = goods.get(0).findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("font-size");
        String merchandiseCampaignPriceSizeString = goods.get(0).findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-size");
        Float merchandiseRegularPriceSize = Float.parseFloat(merchandiseRegularPriceSizeString.substring(0, merchandiseRegularPriceSizeString.length() - 2));
        Float merchandiseCampaignPriceSize = Float.parseFloat(merchandiseCampaignPriceSizeString.substring(0, merchandiseCampaignPriceSizeString.length() - 2));

        Assert.assertEquals(merchandiseRegularPriceColor, "#777777");
        Assert.assertEquals(merchandiseRegularPriceLine, expectedLineThroughStyleValue);
        Assert.assertEquals(merchandiseCampaignPriceColor, expectedMerchandiseCampaignPriceColor);
        Assert.assertTrue(merchandiseCampaignPriceStyle.equals("700") || merchandiseCampaignPriceStyle.equals("900"));
        Assert.assertTrue(merchandiseRegularPriceSize < merchandiseCampaignPriceSize);


        app.getNavigationHelper().openMerchandisePage(goods, 0);
        WebElement merchandise = app.getSessionHelper().getElement(By.xpath("//div[@id='box-product']"));
        Assert.assertEquals(app.getSessionHelper().getAttributeValue(merchandise.findElement(By.xpath(".//h1[@class='title']")), "textContent"), merchandiseName);
        Assert.assertEquals(app.getSessionHelper().getAttributeValue(merchandise.findElement(By.xpath(".//s[@class='regular-price']")), "textContent"), merchandiseRegularPrice);
        Assert.assertEquals(app.getSessionHelper().getAttributeValue(merchandise.findElement(By.xpath(".//strong[@class='campaign-price']")), "textContent"), merchandiseCampaignPrice);

        merchandiseRegularPriceColor = Color.fromString(merchandise.findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("color")).asHex();
        Assert.assertEquals(merchandiseRegularPriceColor, "#666666");

        merchandiseRegularPriceLine = merchandise.findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("text-decoration-line");
        Assert.assertEquals(merchandiseRegularPriceLine, expectedLineThroughStyleValue);

        merchandiseCampaignPriceColor = Color.fromString(merchandise.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("color")).asHex();
        Assert.assertEquals(merchandiseCampaignPriceColor, expectedMerchandiseCampaignPriceColor);

        merchandiseCampaignPriceStyle = merchandise.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-weight");
        Assert.assertTrue(merchandiseCampaignPriceStyle.equals("700") || merchandiseCampaignPriceStyle.equals("900"));

        merchandiseRegularPriceSizeString = merchandise.findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("font-size");
        merchandiseRegularPriceSize = Float.parseFloat(merchandiseRegularPriceSizeString.substring(0, merchandiseRegularPriceSizeString.length() - 2));
        merchandiseCampaignPriceSizeString = merchandise.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-size");
        merchandiseCampaignPriceSize = Float.parseFloat(merchandiseCampaignPriceSizeString.substring(0, merchandiseCampaignPriceSizeString.length() - 2));
        Assert.assertTrue(merchandiseRegularPriceSize < merchandiseCampaignPriceSize);
    }

    @Test
    public void testCustomerOnboarding() throws IOException {
        app.getAdminHelper().turnCaptchaOff();
        app.getShopHelper().openShopUrl();
        long now = System.currentTimeMillis();
        String customerEmail = String.format("user%s@mail.com", now);
        String password = "12345";
        List<WebElement> loginFormElements = app.getNavigationHelper().getElementsList(By.xpath("//form[@name='login_form']//tr"));
        System.out.println(loginFormElements.size());
        app.getNavigationHelper().clickOnLoginFormItem(loginFormElements, 4);
        app.getShopHelper().fillCustomerForm(new CustomerData("Frodo", "Baggins",
                "Shire, The Hole", "09475", "Hobbiton", customerEmail,
                "United States", "+18143511244", password));
        app.getNavigationHelper().submitAccountCreation();
        app.getSessionHelper().logOutFromShop();
        app.getSessionHelper().loginCustomer(customerEmail, password);
        app.getSessionHelper().logOutFromShop();
    }
}
