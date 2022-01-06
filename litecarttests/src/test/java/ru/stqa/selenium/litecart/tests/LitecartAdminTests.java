package ru.stqa.selenium.litecart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.selenium.litecart.model.ProductData;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class LitecartAdminTests extends AdminTestBase {

    @Test
    public void testMenuNavigation(){
        List<WebElement> menuItems = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
        for (int i = 0; i < menuItems.size(); i++) {
            app.getNavigationHelper().clickOnMenuItem(menuItems, i);
            List<WebElement> submenuItems = app.getNavigationHelper().getElementsList(By.xpath("//li[@id='app-']//li"));
            for (int j = 0; j < submenuItems.size(); j++) {
                app.getNavigationHelper().clickOnMenuItem(submenuItems, j);
                submenuItems = app.getNavigationHelper().getElementsList(By.xpath("//li[@id='app-']//li"));
                assertTrue(app.getNavigationHelper().isElementPresent(
                        By.xpath("//h1")));
            }
            menuItems = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
            assertTrue(app.getNavigationHelper().isElementPresent(
                    By.xpath("//h1")));
        }
    }


    @Test
    public void testCountriesListSorting() {
        List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
        app.getNavigationHelper().clickOnMenuItem(elements, 2);
        List<WebElement> rows = app.getNavigationHelper().getElementsList(By.xpath("//tr[@class='row']/td[5]"));
        String[] countriesArray  = new String[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            countriesArray[i] = app.getSessionHelper().getAttributeValue(rows.get(i), "textContent");
        }
        String[] copiedCountriesArray = Arrays.copyOf(countriesArray, rows.size());
        Arrays.sort(countriesArray);
        assertTrue(Arrays.equals(countriesArray, copiedCountriesArray));
    }

    @Test
    public void testCountryZonesListSorting(){
        List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
        app.getNavigationHelper().clickOnMenuItem(elements, 2);
        List<WebElement> rows = app.getNavigationHelper().getElementsList(By.xpath("//tr[@class='row']"));
        int i = 0;
        while (i < rows.size()){
            if (!rows.get(i).findElement(By.xpath("./td[6]")).getAttribute("textContent").equals("0")) {
                app.getNavigationHelper().clickOnCountry(rows.get(i).findElement(By.xpath(".//i[@class='fa fa-pencil']")));
                List<WebElement> zonesTableRows = app.getNavigationHelper().getElementsList(By.xpath("//table[@id='table-zones']//tr"));
                String[] zonesArray  = new String[zonesTableRows.size() - 2];
                for (int j = 1; j < zonesTableRows.size() - 1; j++) {
                    zonesArray[j - 1] = app.getSessionHelper().getAttributeValue(zonesTableRows.get(j).findElement(By.xpath("./td[3]")), "textContent");
                }
                String[] copiedZonesArray = Arrays.copyOf(zonesArray, zonesArray.length);
                Arrays.sort(zonesArray);
                assertTrue(Arrays.equals(zonesArray, copiedZonesArray));
                elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
                app.getNavigationHelper().clickOnMenuItem(elements, 2);
                //app.getNavigationHelper().cancelCountryForm(); //this click does not work
                rows = app.getNavigationHelper().getElementsList(By.xpath("//tr[@class='row']"));
            }
            i = i + 1;
        }
    }


    @Test
    public void testGeoZonesListSorting(){
        List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
        app.getNavigationHelper().clickOnMenuItem(elements, 5);
        List<WebElement> rows = app.getNavigationHelper().getElementsList(By.xpath("//tr[@class='row']"));
        int i = 0;
        while (i < rows.size()) {
            app.getNavigationHelper().clickOnCountry(rows.get(i).findElement(By.xpath(".//i[@class='fa fa-pencil']")));
            List<WebElement> zonesTableRows = app.getNavigationHelper().getElementsList(By.xpath("//table[@id='table-zones']//tr"));
            String zones = "";
            for (int j = 1; j < zonesTableRows.size() - 1; j++) {
                zones = zones + app.getSessionHelper().getAttributeValue(zonesTableRows.get(j).findElement(By.xpath("./td[3]//option[@selected='selected']")), "textContent") + ",";
            }
            String[] zonesArray = zones.split(",");
            Arrays.sort(zonesArray);
            System.out.println(Arrays.toString(zonesArray));
            assertTrue(Arrays.equals(zonesArray, zones.split(",")));
            elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
            app.getNavigationHelper().clickOnMenuItem(elements, 5);
            rows = app.getNavigationHelper().getElementsList(By.xpath("//tr[@class='row']"));
            i = i + 1;
        }
    }

    @Test
    public void testProductCreation(){
        List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
        app.getNavigationHelper().clickOnMenuItem(elements, 1);
        app.getNavigationHelper().openMerchandiseFolder(By.xpath("//tr[@class='row'][2]//a"));
        List<WebElement> before = app.getNavigationHelper().getElementsList(By.xpath("//tr[@class='row']"));
        List<WebElement> buttons = app.getNavigationHelper().getElementsList(By.xpath("//td[@id='content']//a[@class='button']"));
        app.getNavigationHelper().startAddingNewProduct(buttons, 1);
        File productImage = new File("src/test/resources/redDuck.png");
        String shortDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue.";
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue. Cras scelerisque dui non consequat sollicitudin. Sed pretium tortor ac auctor molestie. Nulla facilisi. Maecenas pulvinar nibh vitae lectus vehicula semper. Donec et aliquet velit. Curabitur non ullamcorper mauris. In hac habitasse platea dictumst. Phasellus ut pretium justo, sit amet bibendum urna. Maecenas sit amet arcu pulvinar, facilisis quam at, viverra nisi. Morbi sit amet adipiscing ante. Integer imperdiet volutpat ante, sed venenatis urna volutpat a. Proin justo massa, convallis vitae consectetur sit amet, facilisis id libero.";
        List<WebElement> tabs = app.getNavigationHelper().getElementsList(By.xpath("//ul[@class='index']/li"));
        app.getAdminHelper().fillProductData(tabs, new ProductData("Luxury Red Duck", "RD003",
                "1", "pcs", "3-5 days", "Temporary sold out",
                productImage, "01012022", "31012022", "ACME Corp.",
                shortDescription, description, "25", "US Dollars",
                "30", "05012022", "0000",
                "15012022", "2300", "22"));
        app.getAdminHelper().submitProductFrom();
        List<WebElement> after = app.getNavigationHelper().getElementsList(By.xpath("//tr[@class='row']"));
        Assert.assertEquals(before.size() + 1, after.size());
    }


    @Test
    public void testLinks() {
        List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
        app.getNavigationHelper().clickOnMenuItem(elements, 2);
        app.getAdminHelper().clickAddNewCountryButton();
        List<WebElement> links = app.getNavigationHelper().getElementsList(By.xpath("//i[@class='fa fa-external-link']"));
        int i = 0;
        while (i < links.size()) {
            app.getAdminHelper().checkNewWindow(links.get(i));
            i++;
        }
    }

    @Test
    public void checkBrowserLogs(){
        app.getShopHelper().openUrl("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> rows = app.getNavigationHelper().getElementsList(By.xpath("//tr[@class='row']"));
        for (int i = 3; i < rows.size(); i++) {
            rows.get(i).findElement(By.xpath(".//a")).click();
            Assert.assertTrue(app.getShopHelper().getBrowserLogs().isEmpty());
            app.getShopHelper().openUrl("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            rows = app.getNavigationHelper().getElementsList(By.xpath("//tr[@class='row']"));

        }
    }

}
