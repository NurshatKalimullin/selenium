package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


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

    protected void select(By locator, String value) {
        wd.findElement(locator).click();
        new Select(wd.findElement(locator)).selectByVisibleText(value);
    }

    protected void selectByInput(By locator1, By locator2, By locator3, String value) {
        wd.findElement(locator1).click();
        type(locator2, value);
        click(locator3);
    }

    protected void attache(By locator, File file) {
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
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


    //this function will check element existence with no waiting
    boolean isElementNotPresent(WebDriver wd, By locator) {
        try {
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return wd.findElements(locator).size() == 0;
        } finally {
            wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }


    public List<WebElement> getElementsList(By locator){
        List<WebElement> elements = wd.findElements(locator);
        return elements;
    }

    public void clickByIndex(List<WebElement> elements, int index){
        elements.get(index).click();
    }

    public String getAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public WebElement getElement(By locator) {
        WebElement element = wd.findElement(locator);
        return element;
    }


    public void checkElementValue(By locator, String attribute, String expectedValue) {
        WebDriverWait wait = new WebDriverWait(wd, 10/*seconds*/);
        wait.until(attributeContains(locator, attribute, expectedValue));
    }

    public void waitElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(wd, 10/*seconds*/);
        wait.until(visibilityOfAllElementsLocatedBy(locator));
    }


}