package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.*;

import java.util.List;


public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (InvalidSelectorException e) {
            throw e;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public List<WebElement> getElementsList(By locator){
        List<WebElement> elements = wd.findElements(locator);
        return elements;
    }

    public String getAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public WebElement getElement(By locator) {
        WebElement element = wd.findElement(locator);
        return element;
    }
}