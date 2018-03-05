package edu.st.selenium.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import static org.junit.Assert.*;


public class Task13Cart extends TestBase {

    @Test
    public void TestCart() {
        int productCartCount = 3;
        int productMainPageCount = driver.findElements(By.cssSelector(".product")).size();

        for (int i = 0; i < productCartCount; i++) {
            driver.get("http://localhost/litecart/en/");
            driver.findElements(By.cssSelector(".product")).get(generateRandomInt(0, productMainPageCount - 1)).click();
            AddItemToCart();
        }

        driver.findElement(By.cssSelector("a[href*=checkout]")).click();

        int countShortcut = driver.findElements(By.cssSelector(".shortcuts .shortcut")).size();

        for (int i = 0; i < countShortcut - 1; i++) {
            driver.findElement(By.cssSelector(".shortcuts .shortcut")).click();
            RemoveItemFromCart();
        }
    }

    public void AddItemToCart() {
        int quantity = Integer.valueOf(driver.findElement(By.cssSelector(".quantity")).getAttribute("textContent"));

        if (isElemetPresent(By.cssSelector("select[name='options[Size]']"))) {
            Select select = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
            select.selectByIndex(generateRandomInt(1, select.getOptions().size() - 1));
        }

        driver.findElement(By.cssSelector("[name=add_cart_product]")).click();
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.cssSelector(".quantity")), "textContent", String.valueOf(quantity + 1)));
    }

    public void RemoveItemFromCart() {
        if (isElemetPresent(By.cssSelector("[name=remove_cart_item]"))) {
            WebElement table = driver.findElement(By.cssSelector(".dataTable"));
            int rowCount = driver.findElements(By.cssSelector(".dataTable tr")).size();
            driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();
            wait.until(ExpectedConditions.stalenessOf(table));
            assertEquals(rowCount - 1, driver.findElements(By.cssSelector(".dataTable tr")).size() );
        }
    }

    public static int generateRandomInt(int start, int end) {
        return start + (int) (Math.random() * end);
    }
}
