package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.litecart.model.CustomerData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

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

    public void addMerchandiseIntoCart() {
        click(By.name("add_cart_product"));
    }

    public void removeMerchandiseFromCart() {
        click(By.name("remove_cart_item"));
    }

    public void selectSize(By locator, String size) {
        click(locator);
        select(locator, size);
    }

    public void inputMerchandiseQuantity(String number) {
        type(By.name("quantity"), number);
    }

    public String getMerchandiseQuantity() {
        return getElement(By.name("quantity")).getAttribute("value");
    }

    public void chooseMerchandiseToDelete(List<WebElement> elements, int index) {
        clickByIndex(elements, index);
    }
}
