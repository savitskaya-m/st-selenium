package edu.st.selenium.litecart.Task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get("http://localhost/litecart/en/");
        return this;
    }

    public int cartItemsCount() {
        return Integer.valueOf(driver.findElement(By.cssSelector(".quantity")).getAttribute("textContent"));
    }

    private int productMainPageCount() {
        return driver.findElements(By.cssSelector(".product")).size();
    }

    public WebElement getRandomProduct() {
        return driver.findElements(By.cssSelector(".product")).get(generateRandomInt(0, productMainPageCount() - 1));
    }

}
