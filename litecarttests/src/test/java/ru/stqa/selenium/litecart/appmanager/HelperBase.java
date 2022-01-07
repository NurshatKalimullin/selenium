package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public void checkNewWindow(WebElement element){
        WebDriverWait wait = new WebDriverWait(wd, 10/*seconds*/);
        String originalWindow = wd.getWindowHandle();
        Set<String> oldWindows = wd.getWindowHandles();
        element.click();
        wait.until(numberOfWindowsToBe(oldWindows.size() + 1));
        String newWindow = findNewWindow(oldWindows);
        wd.switchTo().window(newWindow);
        wd.close();
        wd.switchTo().window(originalWindow);
    }

    private String findNewWindow(Set<String> oldWindows) {
        String window = null;
        Set<String> newWindows = wd.getWindowHandles();
        for (String newWindow : newWindows) {
            if (!oldWindows.contains(newWindow)) {
                window = newWindow;
                break;
            }
        }
        return window;
    }

    public static boolean booleanEqual(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    public void isElementClickable(By locator){
        WebDriverWait wait = new WebDriverWait(wd, 10/*seconds*/);
        wait.until(elementToBeClickable(locator));
    }

    public void isElementDeleted(WebElement element) {
        WebDriverWait wait = new WebDriverWait(wd, 10/*seconds*/);
        wait.until(stalenessOf(element));
    }

    public void openUrl(String URL) {
        wd.navigate().to(URL);
    }

    public Set<String> getBrowserLogs() {
        Set<String> logLevels = wd.manage().logs().getAvailableLogTypes();
        Set<String > logs = new HashSet<>();
        for (String logLevel : logLevels) {
            for (LogEntry l : wd.manage().logs().get(logLevel).getAll()) {
                logs.add(String.valueOf(l));
            }
        }
        return logs;
    }


}