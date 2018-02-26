package edu.st.selenium.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class Task9TestSorting extends TestBase {

    @Test
    public void checkCountriesSorting() {
        loginToAdmin("admin", "admin");
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<String> countries = new ArrayList<>();
        List<WebElement> tableCountriesRows = driver.findElements(By.cssSelector(".dataTable .row"));

        for (int i = 0; i < tableCountriesRows.size(); i++) {
            List<WebElement> tableCountriesCells = tableCountriesRows.get(i).findElements(By.tagName("td"));
            countries.add(tableCountriesCells.get(4).getText());

            System.out.println(tableCountriesCells.get(5).getText());

            if (!tableCountriesCells.get(5).getText().equals("0")) {
                tableCountriesCells.get(4).findElement(By.cssSelector("a")).click();
                wait.until(ExpectedConditions.titleContains("Edit Country"));

                List<String> zones = new ArrayList<>();

                WebElement tableZones = driver.findElement(By.id("table-zones"));
                List<WebElement> tableZonesRows = tableZones.findElements(By.cssSelector("tr:not([class=header])"));

                for (WebElement tableZonesRow: tableZonesRows) {
                    List<WebElement> tableZonesCells = tableZonesRow.findElements(By.cssSelector("td input"));

                    if (!tableZonesRow.getAttribute("outerText").contains("Add")) {
                        zones.add(tableZonesCells.get(2).getAttribute("value"));
                    }
                }
                assertListSorted(zones);

                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                tableCountriesRows = driver.findElements(By.cssSelector(".dataTable .row"));
            }
        }
        assertListSorted(countries);
    }

    @Test
    public void checkZonesSorting() {
        loginToAdmin("admin", "admin");
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> tableCountriesRows = driver.findElements(By.cssSelector(".dataTable .row"));

        for (int i = 0; i < tableCountriesRows.size(); i++) {
            List<WebElement> tableCountriesCells = tableCountriesRows.get(i).findElements(By.tagName("td"));
            tableCountriesCells.get(2).findElement(By.cssSelector("a")).click();
            wait.until(ExpectedConditions.titleContains("Edit Geo Zone"));

            List<String> zones = new ArrayList<>();

            WebElement tableZones = driver.findElement(By.id("table-zones"));
            List<WebElement> tableZonesRows = tableZones.findElements(By.cssSelector("tr:not([class=header])"));

            for (WebElement tableZonesRow: tableZonesRows) {
                List<WebElement> tableZonesCells = tableZonesRow.findElements(By.cssSelector("td"));

                if (!tableZonesRow.getAttribute("innerText").equals("")){
                    zones.add(tableZonesCells.get(2).findElement(By.cssSelector("option[selected=selected]")).getAttribute("innerText"));
                }
            }
            assertListSorted(zones);

            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            tableCountriesRows = driver.findElements(By.cssSelector(".dataTable .row"));
        }
    }

}
