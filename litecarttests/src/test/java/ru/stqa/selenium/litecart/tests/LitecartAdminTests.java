package ru.stqa.selenium.litecart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class LitecartAdminTests extends AdminTestBase {

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
}
