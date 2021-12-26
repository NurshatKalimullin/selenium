package ru.stqa.selenium.litecart.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.selenium.litecart.appmanager.ApplicationManager;


public class AdminTestBase {


    protected static final ApplicationManager app
            = new ApplicationManager (System.getProperty("browser", BrowserType.CHROME));

    @BeforeMethod(alwaysRun = true)
    public void setUp(ITestContext context) throws Exception {
        app.initAdmin();
        context.setAttribute("app", app);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }



}










