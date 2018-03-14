package edu.st.selenium.litecart.Task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public boolean isElementPresentOnPage(By locator){
        return driver.findElements(locator).size()>0;
    }

    public static int generateRandomInt(int start, int end) {
        return start + (int) (Math.random() * end);
    }
}
