package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.selenium.litecart.model.CustomerData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ShopHelper extends HelperBase {

    private final Properties properties;

    public ShopHelper(WebDriver wd) {
        super(wd);
        properties = new Properties();
    }

    public void fillCustomerForm(CustomerData CustomerData) {
        type(By.name("firstname"), CustomerData.getFirstName());
        type(By.name("lastname"), CustomerData.getLastName());
        type(By.name("address1"), CustomerData.getAddress());
        type(By.name("postcode"), CustomerData.getPostCode());
        type(By.name("city"), CustomerData.getCity());
        selectByInput(By.className("select2-selection__arrow"), By.className("select2-search__field"),
                By.xpath("//li[contains(@id, 'select2-country') and contains(@id, 'US')]"), CustomerData.getCountry());
        type(By.name("email"), CustomerData.getEmail());
        type(By.name("phone"), CustomerData.getPhoneNumber());
        type(By.name("password"), CustomerData.getPassword());
        type(By.name("confirmed_password"), CustomerData.getPassword());
    }


    public void openShopUrl() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        wd.get(properties.getProperty("web.shopUrl"));
    }
}
