package ru.stqa.selenium.litecart.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void loginAdminUser(String username, String password) {
        type(By.name("username"), username );
        type(By.name("password"), password);
        click(By.xpath("//button[@name='login']"));
    }

    public void logOut() {
        click(By.xpath("//a[contains(text(),'Logout')]"));
    }

    public void loginCustomer(String email, String password) {
        type(By.name("email"), email);
        type(By.name("password"), password);
        click(By.xpath("//button[@name='login']"));
    }

    public void logOutFromShop() {
        click(By.xpath("//div[@id='box-account']//li[4]/a"));
    }
}
