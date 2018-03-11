package edu.st.selenium.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class Task14Windows extends TestBase {

    @Test
    public void OpenLinks() {
        loginToAdmin("admin", "admin");
        driver.findElement(By.cssSelector("#app- a[href*=countries]")).click();

        int countriesCount = driver.findElements(By.cssSelector(".dataTable tr.row")).size();
        WebElement country = driver.findElements(By.cssSelector(".dataTable tr.row")).get(generateRandomInt(0, countriesCount - 1)).findElements(By.tagName("td")).get(4);
        country.findElement(By.cssSelector("a")).click();

        List<WebElement> externalLinks = driver.findElements(By.cssSelector("[class='fa fa-external-link']"));

        for (int i = 0; i < externalLinks.size(); i++) {
            String mainWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();

            externalLinks.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(oldWindows.size() + 1));

            Set<String> newWindows = driver.getWindowHandles();
            newWindows.removeAll(oldWindows);
            assertEquals(1, newWindows.size());

            String newWindow = newWindows.iterator().next();

            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    public static int generateRandomInt(int start, int end) {
        return start + (int) (Math.random() * end);
    }
}
