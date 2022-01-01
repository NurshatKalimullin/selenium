package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.selenium.litecart.model.ProductData;

import java.util.List;

public class AdminHelper extends HelperBase{

    public AdminHelper(WebDriver wd) {
        super(wd);
    }

    public void fillProductData(List<WebElement> tabs,ProductData ProductData) {
        click(By.xpath("//input[@type='radio' and @value='1']"));
        click(By.xpath("//input[@name='categories[]' and @value='0']"));
        click(By.xpath("//input[@name='categories[]' and @value='1']"));
        type(By.xpath("//input[@name='name[en]']"), ProductData.getProductName());
        type(By.xpath("//input[@name='code']"), ProductData.getProductCode());
        type(By.xpath("//input[@name='quantity']"), ProductData.getQuantity());
        select(By.name("quantity_unit_id"), ProductData.getQuantityUnit());
        select(By.name("delivery_status_id"), ProductData.getDeliveryStatus());
        select(By.name("sold_out_status_id"), ProductData.getSoldOutStatus());
        attache(By.name("new_images[]"), ProductData.getProductImage());
        type(By.name("date_valid_from"), ProductData.getDateValidFrom());
        type(By.name("date_valid_to"), ProductData.getDateValidTo());
        clickByIndex(tabs, 1);
        select(By.name("manufacturer_id"), ProductData.getManufacturer());
        type(By.name("short_description[en]"), ProductData.getShortDescription());
        type(By.className("trumbowyg-editor"), ProductData.getDescription());
        clickByIndex(tabs, 3);
        type(By.name("purchase_price"), ProductData.getPurchasePriceAmount());
        select(By.name("purchase_price_currency_code"), ProductData.getPurchasePriceCurrency());
        type(By.name("prices[USD]"), ProductData.getUsdPriceAmount());
        click(By.id("add-campaign"));
        typeCampaignsDate(By.name("campaigns[new_1][start_date]"), ProductData.getCampaignStartDate(), ProductData.getCampaignStartTime())  ;
        typeCampaignsDate(By.name("campaigns[new_1][end_date]"), ProductData.getCampaignEndDate(), ProductData.getCampaignEndTime());
        type(By.name("campaigns[new_1][USD]"), ProductData.getCampaignUSDPriceAmount());
    }

    private void typeCampaignsDate(By locator, String date, String time) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(Keys.LEFT, Keys.LEFT, Keys.LEFT +  date, Keys.RIGHT + time);
    }


    public void submitProductFrom() {
        click(By.name("save"));
    }
}
