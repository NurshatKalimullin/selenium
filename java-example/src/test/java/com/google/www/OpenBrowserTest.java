package com.google.www;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class OpenBrowserTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private JavascriptExecutor js;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    //driver = new FirefoxDriver();
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("something", true);

    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized");
    options.merge(capabilities);
    driver = new ChromeDriver(options);
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testOpenBrowser() throws Exception {
    driver.get("https://www.google.com/maps?q=maps&sxsrf=AOaemvKUW0xtVPDBpBd8JaR2qC10zcb2bA:1639675328523&iflsig=ALs-wAMAAAAAYbuD0JvJ01CaeGEfUbIpFAuTXpsQpHFs&uact=5&gs_lcp=Cgdnd3Mtd2l6EAMyCAgAEIAEELEDMggIABCABBCxAzIICAAQgAQQsQMyCAgAEIAEELEDMggIABCABBCxAzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCC4QgAQyBQgAEIAEOgsIABCABBCxAxCDAToOCC4QgAQQsQMQxwEQowI6EQguEIAEELEDEIMBEMcBEKMCOggILhCABBCxAzoLCC4QgAQQxwEQowI6DgguEIAEELEDEMcBENEDUABYvAVglwdoAHAAeACAAVmIAYcCkgEBNJgBAKABAQ&um=1&ie=UTF-8&sa=X&ved=2ahUKEwi1pbnP6uj0AhWvlYsKHYhHD_IQ_AUoAXoECAIQAw");
    Thread.sleep(3000);
    //driver.findElement(By.xpath("//span[@class='uEubGf gm2-subtitle-alt-2']")).click();
    WebElement ele = driver.findElement(By.xpath("//span[@class='uEubGf gm2-subtitle-alt-2']"));
    Thread.sleep(3000);
    //Javascript command
    JavascriptExecutor js = (JavascriptExecutor)driver;
    ((JavascriptExecutor) driver).executeScript("ele = document.elementFromPoint(200, -500); ele.click();");
    //ele.click();
    Thread.sleep(5000);
    System.out.printf("Test");
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
