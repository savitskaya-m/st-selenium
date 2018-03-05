package edu.st.selenium.litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task08Stickers {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkStickersOnMainPage() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> products = driver.findElements(By.cssSelector(".product"));

        for (int i = 0; i < products.size(); i++) {
            assertEquals(1,products.get(i).findElements(By.cssSelector(".sticker")).size());
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
