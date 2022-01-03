package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void clickOnMenuItem(List<WebElement> elements, int i) {
        clickByIndex(elements, i);
    }

    public void clickOnCountry(WebElement element) {
        element.click();
    }

    public void cancelCountryForm() {
        wd.findElement(By.xpath("//button[@name='cancel']"));
    }

    public void openMerchandisePage(List<WebElement> merchandise, int i) {
        clickByIndex(merchandise, i);
    }

    public void startAddingNewProduct(List<WebElement> button, int i) {
        clickByIndex(button, i);
    }

    public void clickOnLoginFormItem(List<WebElement> loginFormElements, int i) {
        clickByIndex(loginFormElements, i);
    }

    public void submitAccountCreation() {
        click(By.name("create_account"));
    }

    public void goToShopHomePage() {
        click(By.xpath("//i[@class='fa fa-home']"));
    }

    public void openShopCart() {
        click(By.xpath("//div[@id='cart']"));
    }

    public void openMerchandiseFolder(By locator) {
        click(locator);
    }
}
