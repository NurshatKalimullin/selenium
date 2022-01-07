package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.selenium.litecart.model.Customer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


public class ShopHelper extends HelperBase {

    private final Properties properties;

    public ShopHelper(WebDriver wd) {
        super(wd);
        properties = new Properties();
    }

    public void fillCustomerForm(Customer customer) {
        type(By.name("firstname"), customer.getFirstName());
        type(By.name("lastname"), customer.getLastName());
        type(By.name("address1"), customer.getAddress());
        type(By.name("postcode"), customer.getPostCode());
        type(By.name("city"), customer.getCity());
        selectByInput(By.className("select2-selection__arrow"), By.className("select2-search__field"),
                By.xpath("//li[contains(@id, 'select2-country') and contains(@id, 'US')]"), customer.getCountry());
        type(By.name("email"), customer.getEmail());
        type(By.name("phone"), customer.getPhoneNumber());
        type(By.name("password"), customer.getPassword());
        type(By.name("confirmed_password"), customer.getPassword());
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
        WebElement element = wd.findElement(By.name("remove_cart_item"));
        element.click();
        isElementDeleted(element);
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
