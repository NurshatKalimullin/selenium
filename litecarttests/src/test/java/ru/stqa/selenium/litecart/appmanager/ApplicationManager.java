package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private String browser;

    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private ShopHelper shopHelper;


    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (browser.equals(BrowserType.FIREFOX)) {
            //let's use FireFox Nightly
            FirefoxBinary firefoxBinary = new FirefoxBinary(
                    new File("C:\\Program Files\\Firefox Nightly\\firefox.exe"));
            DesiredCapabilities desired = DesiredCapabilities.firefox();
            FirefoxOptions options = new FirefoxOptions();
            desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));

            wd = new FirefoxDriver(options);
        } else if (browser.equals(BrowserType.CHROME)) {
            //open maximized Chrome explorer
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");

            //set browser setting and add it to browser
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(ChromeOptions.CAPABILITY, options);
            wd = new ChromeDriver(caps);
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }

        //let's print browser settings on console
        System.out.println(((HasCapabilities) wd).getCapabilities());


        //if element is not presented test will wait 1 seconds for element to load
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        shopHelper = new ShopHelper(wd);
        if (properties.getProperty("web.baseUrl").equals("http://localhost/litecart/admin")) {
            sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
        }
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

    public ShopHelper getShopHelper() {
        return shopHelper;
    }
}
