package edu.st.selenium.litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task7LeftMenu {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void openLeftMenu() {
        String login = "admin";
        String password = "admin";

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*='logout']")));

        List<WebElement> topCategories = driver.findElements(By.cssSelector("ul.list-vertical li[id*='app']"));

        for (int i = 0; i < topCategories.size(); i++) {
            topCategories.get(i).click();
            assertEquals(1,driver.findElements(By.cssSelector("h1")).size());

            topCategories = driver.findElements(By.cssSelector("ul.list-vertical li[id*='app']"));
            List<WebElement> subCategories = topCategories.get(i).findElements(By.cssSelector("li"));

            for (int j = 0; j < subCategories.size(); j++) {
                subCategories.get(j).click();
                assertEquals(1,driver.findElements(By.cssSelector("h1")).size());

                topCategories = driver.findElements(By.cssSelector("ul.list-vertical li[id*='app']"));
                subCategories = topCategories.get(i).findElements(By.cssSelector("li"));
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
