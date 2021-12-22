package ru.stqa.selenium.litecart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LitecartTests extends TestBase {

    @Test
    public void testMenuNavigation(){
        List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
        String[] headers = {" Template", " Catalog", " Countries", " Currencies", " Customers", " Geo Zones", " Languages", " Job Modules", " Orders", " Pages", " Monthly Sales", " Settings",
        " Slides", " Tax Classes", " Search Translations", " Users", " vQmods"};
        for (int i = 0; i < elements.size(); i++) {
            app.getNavigationHelper().clickOnMenuItem(elements, i);
            elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
            assertTrue(app.getNavigationHelper().isElementPresent(
                    By.xpath(String.format("//h1[contains(text(),'%s')]", headers[i]))));
        }
    }

    @Test
    public void testGoodsStickers() {
        List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//li[@class='product column shadow hover-light']"));
        for (WebElement element : elements) {
            List<WebElement> stickers = element.findElements(By.xpath(".//div[contains(@class, 'sticker')]"));
            System.out.println(stickers.size());
            assertEquals(stickers.size(), 1);
        }
    }

    @Test
    public void testCountriesListSorting() {
        List<WebElement> elements = app.getNavigationHelper().getElementsList(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']"));
        app.getNavigationHelper().clickOnMenuItem(elements, 2);
        List<WebElement> rows = app.getNavigationHelper().getElementsList(By.xpath("//tr[@class='row']/td[5]"));
        String countries = "";
        for (WebElement row : rows) {
            countries = countries + app.getSessionHelper().getAttributeValue(row, "textContent") + ",";
        }
        String[] countriesArray = countries.split(",");
        Arrays.sort(countriesArray);
        assertTrue(Arrays.equals(countriesArray, countries.split(",")));
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
                String zones = "";
                for (int j = 1; j < zonesTableRows.size(); j++) {
                    zones = zones + app.getSessionHelper().getAttributeValue(zonesTableRows.get(j).findElement(By.xpath("./td[3]")), "textContent") + ",";
                }
                String[] zonesArray = zones.split(",");
                Arrays.sort(zonesArray);
                System.out.println(Arrays.toString(zonesArray));
                assertTrue(Arrays.equals(zonesArray, zones.split(",")));
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
}
