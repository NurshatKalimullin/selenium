package ru.stqa.selenium.litecart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.selenium.litecart.model.Customer;

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
        String expectedMerchandiseCampaignPriceColor = "0";

        List<WebElement> goods = app.getNavigationHelper().getElementsList(By.xpath("//div[@id='box-campaigns']//li[contains(@class,'product')]"));
        String merchandiseName = app.getSessionHelper().getAttributeValue(goods.get(0).findElement(By.xpath(".//div[@class='name']")), "textContent");
        String merchandiseRegularPrice = app.getSessionHelper().getAttributeValue(goods.get(0).findElement(By.xpath(".//s[@class='regular-price']")), "textContent");
        String merchandiseCampaignPrice = app.getSessionHelper().getAttributeValue(goods.get(0).findElement(By.xpath(".//strong[@class='campaign-price']")), "textContent");

        String[] merchandiseRegularPriceColor = goods.get(0).findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("color")
                .replace("rgba(", "").replace(")", "").replace(" ", "").split(",");

        String merchandiseRegularPriceLine = goods.get(0).findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("text-decoration-line");

        String[] merchandiseCampaignPriceColor = goods.get(0).findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("color")
                .replace("rgba(", "").replace(")", "").replace(" ", "").split(",");

        String merchandiseCampaignPriceStyle = goods.get(0).findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-weight");
        String merchandiseRegularPriceSizeString = goods.get(0).findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("font-size");
        String merchandiseCampaignPriceSizeString = goods.get(0).findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-size");
        Float merchandiseRegularPriceSize = Float.parseFloat(merchandiseRegularPriceSizeString.substring(0, merchandiseRegularPriceSizeString.length() - 2));
        Float merchandiseCampaignPriceSize = Float.parseFloat(merchandiseCampaignPriceSizeString.substring(0, merchandiseCampaignPriceSizeString.length() - 2));

        Assert.assertTrue(merchandiseRegularPriceColor[0].equals(merchandiseRegularPriceColor[1])
                && merchandiseRegularPriceColor[0].equals(merchandiseRegularPriceColor[2]));
        Assert.assertEquals(merchandiseRegularPriceLine, expectedLineThroughStyleValue);
        Assert.assertTrue(merchandiseCampaignPriceColor[1].equals(expectedMerchandiseCampaignPriceColor)
                && merchandiseCampaignPriceColor[2].equals(expectedMerchandiseCampaignPriceColor));
        Assert.assertTrue(merchandiseCampaignPriceStyle.equals("700") || merchandiseCampaignPriceStyle.equals("900"));
        Assert.assertTrue(merchandiseRegularPriceSize < merchandiseCampaignPriceSize);


        app.getNavigationHelper().openMerchandisePage(goods, 0);
        WebElement merchandise = app.getSessionHelper().getElement(By.xpath("//div[@id='box-product']"));
        Assert.assertEquals(app.getSessionHelper().getAttributeValue(merchandise.findElement(By.xpath(".//h1[@class='title']")), "textContent"), merchandiseName);
        Assert.assertEquals(app.getSessionHelper().getAttributeValue(merchandise.findElement(By.xpath(".//s[@class='regular-price']")), "textContent"), merchandiseRegularPrice);
        Assert.assertEquals(app.getSessionHelper().getAttributeValue(merchandise.findElement(By.xpath(".//strong[@class='campaign-price']")), "textContent"), merchandiseCampaignPrice);

        merchandiseRegularPriceColor = merchandise.findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("color")
                .replace("rgba(", "").replace(")", "").replace(" ", "").split(",");
        Assert.assertTrue(merchandiseRegularPriceColor[0].equals(merchandiseRegularPriceColor[1])
                && merchandiseRegularPriceColor[0].equals(merchandiseRegularPriceColor[2]));

        merchandiseRegularPriceLine = merchandise.findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("text-decoration-line");
        Assert.assertEquals(merchandiseRegularPriceLine, expectedLineThroughStyleValue);

        merchandiseCampaignPriceColor = merchandise.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("color")
                .replace("rgba(", "").replace(")", "").replace(" ", "").split(",");;
        Assert.assertTrue(merchandiseCampaignPriceColor[1].equals(expectedMerchandiseCampaignPriceColor)
                && merchandiseCampaignPriceColor[2].equals(expectedMerchandiseCampaignPriceColor));

        merchandiseCampaignPriceStyle = merchandise.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-weight");
        Assert.assertTrue(merchandiseCampaignPriceStyle.equals("700") || merchandiseCampaignPriceStyle.equals("900"));

        merchandiseRegularPriceSizeString = merchandise.findElement(By.xpath(".//s[@class='regular-price']")).getCssValue("font-size");
        merchandiseRegularPriceSize = Float.parseFloat(merchandiseRegularPriceSizeString.substring(0, merchandiseRegularPriceSizeString.length() - 2));
        merchandiseCampaignPriceSizeString = merchandise.findElement(By.xpath(".//strong[@class='campaign-price']")).getCssValue("font-size");
        merchandiseCampaignPriceSize = Float.parseFloat(merchandiseCampaignPriceSizeString.substring(0, merchandiseCampaignPriceSizeString.length() - 2));
        Assert.assertTrue(merchandiseRegularPriceSize < merchandiseCampaignPriceSize);
    }


    @Test(dataProvider = "validCustomer", dataProviderClass = DataProviders.class)
    public void testCustomerOnboarding(Customer customer) throws IOException {
        app.getAdminHelper().turnCaptchaOff();
        app.getShopHelper().openShopUrl();
        List<WebElement> loginFormElements = app.getNavigationHelper().getElementsList(By.xpath("//form[@name='login_form']//tr"));
        app.getNavigationHelper().clickOnLoginFormItem(loginFormElements, 4);
        app.getShopHelper().fillCustomerForm(customer);
        app.getNavigationHelper().submitAccountCreation();
        app.getSessionHelper().logOutCustomer();
        app.getSessionHelper().loginCustomer(customer.getEmail(), customer.getPassword());
        app.getSessionHelper().logOutCustomer();
    }

    @Test
    public void testCart() {
        List<WebElement> goods = app.getNavigationHelper().getElementsList(By.xpath("//div[@class='image-wrapper']"));
        int i = 0;
        while (i < 3) {
            app.getNavigationHelper().openMerchandisePage(goods, 1);
            if (app.getShopHelper().isElementPresent(By.xpath("//select[@name='options[Size]']"))) {
                app.getShopHelper().selectSize(By.xpath("//select[@name='options[Size]']"), "Small");
            }
            app.getShopHelper().inputMerchandiseQuantity("1");
            app.getShopHelper().addMerchandiseIntoCart();
            app.getShopHelper().checkElementValue(By.xpath("//span[@class='quantity']"), "textContent", String.valueOf(i + 1));
            app.getNavigationHelper().goToShopHomePage();
            i++;
            goods = app.getNavigationHelper().getElementsList(By.xpath("//div[@class='image-wrapper']"));
        }
        app.getNavigationHelper().openShopCart();
        int goodsInCart = app.getNavigationHelper().getElementsList(By.xpath("//a[@class='image-wrapper shadow']")).size();
        while (goodsInCart > 0){
            if (app.getShopHelper().isElementPresent(By.xpath("//ul[@class='shortcuts']"))) {
                List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@class='shortcuts']//a"));
                app.getShopHelper().chooseMerchandiseToDelete(elements, 0);
            } else if (goodsInCart == 1) {
                break;
            }
            app.getAdminHelper().isElementClickable(By.xpath("//button[@name='remove_cart_item']"));
            app.getShopHelper().removeMerchandiseFromCart();
            goodsInCart = app.getNavigationHelper().getElementsList(By.xpath("//a[@class='image-wrapper shadow']")).size();
        }
    }
}
