package edu.st.selenium.misc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class Task4RunBrowsers {
    private WebDriver driver;

    @Before
    public void start() {
        /*DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        driver = new InternetExplorerDriver(caps);
        */

        //driver = new ChromeDriver();

        DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, false); //for old version FF
        driver = new FirefoxDriver();


        /*DesiredCapabilities caps = new DesiredCapabilities();
        driver = new FirefoxDriver(new FirefoxBinary(new File("c:\\Program Files (x86)\\Nightly\\firefox.exe")), new FirefoxProfile(), caps);
        */

        System.out.println(((HasCapabilities) driver).getCapabilities());
    }

    @Test
    public void firstTask() {
        driver.get("https://ya.ru/");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
