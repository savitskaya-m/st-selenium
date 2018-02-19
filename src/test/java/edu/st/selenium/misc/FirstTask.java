package edu.st.selenium.misc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTask {

    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void firstTask() {
        driver.get("https://yandex.ru/");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}