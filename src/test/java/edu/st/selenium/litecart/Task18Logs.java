package edu.st.selenium.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import static org.junit.Assert.*;

public class Task18Logs extends TestBase {

    @Test
    public void CheckLogs() {
        loginToAdmin("admin", "admin");
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> tableRows = driver.findElements(By.cssSelector(".dataTable .row"));
        int tableRowCount = tableRows.size();

        for (int i = 0; i < tableRowCount; i++) {

            List<WebElement> tableCells = tableRows.get(i).findElements(By.tagName("td"));

            if (!isElementPresent(By.cssSelector("i[class*='fa fa-folder']"), tableCells.get(2))) {
                tableCells.get(2).findElement(By.cssSelector("a")).click();

                List<LogEntry> logEntries = driver.manage().logs().get("browser").getAll();

                if (logEntries.size() > 0) {
                    for (LogEntry logEntry : logEntries) {
                        System.out.println(logEntry);
                    }
                }

                assertEquals(0, logEntries.size());
                driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
                wait.until(ExpectedConditions.titleIs("Catalog | My Store"));
                tableRows = driver.findElements(By.cssSelector(".dataTable .row"));
            }
        }

    }

    public boolean isElementPresent(By locator, WebElement parent) {
        try {
            parent.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
