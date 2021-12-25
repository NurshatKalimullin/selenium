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
        elements.get(i).click();
    }

    public void clickOnCountry(WebElement element) {
        element.click();
    }

    public void cancelCountryForm() {
        wd.findElement(By.xpath("//button[@name='cancel']"));
    }

    public void openMerchandisePage(List<WebElement> merchandise, int i) {
        merchandise.get(i).click();
    }
}
