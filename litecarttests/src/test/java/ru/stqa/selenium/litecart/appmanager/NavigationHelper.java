package ru.stqa.selenium.litecart.appmanager;

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
}
