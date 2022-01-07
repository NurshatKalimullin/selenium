package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.selenium.litecart.model.Product;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class AdminHelper extends HelperBase{

    private final Properties properties;
    private SessionHelper sessionHelper;

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public AdminHelper(WebDriver wd) {
        super(wd);
        properties = new Properties();
        sessionHelper = new SessionHelper(wd);
    }

    public void fillProductData(List<WebElement> tabs, Product product) {
        click(By.xpath("//input[@type='radio' and @value='1']"));
        if (booleanEqual(getAttributeValue(wd.findElement(
                By.xpath("//input[@name='categories[]' and @value='0']")),"checked"), "true")) {
            click(By.xpath("//input[@name='categories[]' and @value='0']"));
        }
        if (booleanEqual(getAttributeValue(wd.findElement(
                By.xpath("//input[@name='categories[]' and @value='1']")),"checked"), "null")){
            click(By.xpath("//input[@name='categories[]' and @value='1']"));
        }
        type(By.xpath("//input[@name='name[en]']"), product.getProductName());
        type(By.xpath("//input[@name='code']"), product.getProductCode());
        type(By.xpath("//input[@name='quantity']"), product.getQuantity());
        select(By.name("quantity_unit_id"), product.getQuantityUnit());
        select(By.name("delivery_status_id"), product.getDeliveryStatus());
        select(By.name("sold_out_status_id"), product.getSoldOutStatus());
        attache(By.name("new_images[]"), product.getProductImage());
        type(By.name("date_valid_from"), product.getDateValidFrom());
        type(By.name("date_valid_to"), product.getDateValidTo());
        clickByIndex(tabs, 1);
        select(By.name("manufacturer_id"), product.getManufacturer());
        type(By.name("short_description[en]"), product.getShortDescription());
        type(By.className("trumbowyg-editor"), product.getDescription());
        clickByIndex(tabs, 3);
        type(By.name("purchase_price"), product.getPurchasePriceAmount());
        select(By.name("purchase_price_currency_code"), product.getPurchasePriceCurrency());
        type(By.name("prices[USD]"), product.getUsdPriceAmount());
        click(By.id("add-campaign"));
        typeCampaignsDate(By.name("campaigns[new_1][start_date]"), product.getCampaignStartDate(), product.getCampaignStartTime())  ;
        typeCampaignsDate(By.name("campaigns[new_1][end_date]"), product.getCampaignEndDate(), product.getCampaignEndTime());
        type(By.name("campaigns[new_1][USD]"), product.getCampaignUSDPriceAmount());
    }

    private void typeCampaignsDate(By locator, String date, String time) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(Keys.LEFT, Keys.LEFT, Keys.LEFT +  date, Keys.RIGHT + time);
    }


    public void submitProductFrom() {
        click(By.name("save"));
    }

    public void openAdminUrl() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        wd.get(properties.getProperty("web.adminUrl"));
    }


    public void turnCaptchaOff() throws IOException {
        openAdminUrl();
        getSessionHelper().loginAdminUser(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
        List<WebElement> elements = getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
        clickByIndex(elements, 11);
        click(By.id("doc-security"));
        List<WebElement> rows = getElementsList(By.xpath("//form[@name='settings_form']//tr[@class='row']"));
        rows.get(5).findElement(By.xpath(".//i[@class='fa fa-pencil']")).click();
        click(By.xpath("//input[@type='radio' and @value='0']"));
        click(By.name("save"));
    }

    public void clickAddNewCountryButton() {
        click(By.xpath("//a[@class='button']"));
    }


}
