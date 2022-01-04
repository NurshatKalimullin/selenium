package ru.stqa.selenium.litecart.appmanager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private String browser;

    EventFiringWebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private ShopHelper shopHelper;
    private AdminHelper adminHelper;


    public static class MyListener extends AbstractWebDriverEventListener {

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by +  " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screen = new File("screen-" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tempFile, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(screen);
        }
    }


    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();

    }

    public void initShop() throws IOException {
        initBrowser();
        wd.get(properties.getProperty("web.shopUrl"));
    }

    public void initAdmin() throws IOException {
        initBrowser();
        wd.get(properties.getProperty("web.adminUrl"));
        sessionHelper.loginAdminUser(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

    }

    private void initBrowser() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (browser.equals(BrowserType.FIREFOX)) {
            //let's use FireFox Nightly
            /*
            FirefoxBinary firefoxBinary = new FirefoxBinary(
                    new File("C:\\Program Files\\Firefox Nightly\\firefox.exe"));
            DesiredCapabilities desired = DesiredCapabilities.firefox();
            FirefoxOptions options = new FirefoxOptions();
            desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
             */

            wd = new EventFiringWebDriver(new FirefoxDriver());
        } else if (browser.equals(BrowserType.CHROME)) {
            //open maximized Chrome explorer
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");

            //set browser setting and add it to browser
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(ChromeOptions.CAPABILITY, options);

            wd = new EventFiringWebDriver(new ChromeDriver(caps));
        } else if (browser.equals(BrowserType.EDGE)) {
            System.setProperty("webdriver.edge.driver", "C:\\Windows\\System32\\msedgedriver.exe");
            wd = new EventFiringWebDriver(new EdgeDriver());
        }

        wd.register(new MyListener());

        //let's print browser settings on console
        System.out.println(((HasCapabilities) wd).getCapabilities());

        //if element is not presented test will wait 4 seconds for element to load
        wd.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        adminHelper = new AdminHelper(wd);
        shopHelper = new ShopHelper(wd);
    }


    public void stop() {
        wd.quit();
    }


    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public AdminHelper getAdminHelper() {
        return adminHelper;
    }

    public ShopHelper getShopHelper() {
        return shopHelper;
    }

}
