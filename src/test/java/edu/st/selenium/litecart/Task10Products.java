package edu.st.selenium.litecart;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

public class Task10Products extends TestBase {

    @Before
    public void start() {
    }

    @Test
    public void checkProductChrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        checkProduct();
    }

    @Test
    public void checkProductFireFox() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        checkProduct();
    }

    @Test
    public void checkProductIE() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        driver = new InternetExplorerDriver(caps);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        checkProduct();
    }

    public void checkProduct() {
        driver.get("http://localhost/litecart/en/");
        WebElement productMainPage = driver.findElements(By.cssSelector("#box-campaigns .product")).get(0);
        String nameMainPage = productMainPage.findElement(By.cssSelector(".name")).getText();
        WebElement priceMainPage = productMainPage.findElement(By.cssSelector(".regular-price"));
        String priceMainPageTxt = priceMainPage.getText();
        WebElement campaignPriceMainPage = productMainPage.findElement(By.cssSelector(".campaign-price"));
        String campaignPriceMainPageTxt = campaignPriceMainPage.getText();
        Float priceMainPageFontSize = Float.valueOf(priceMainPage.getCssValue("font-size").split("px")[0]);
        Float campaignPriceMainPageFontSize = Float.valueOf(campaignPriceMainPage.getCssValue("font-size").split("px")[0]);

        assertTrue(isRegularStyle(priceMainPage));
        assertTrue(isCampaignStyle(campaignPriceMainPage));
        assertTrue(campaignPriceMainPageFontSize > priceMainPageFontSize);

        productMainPage.click();

        WebElement productPage = driver.findElement(By.cssSelector("#box-product"));
        String nameProductPage = productPage.findElement(By.cssSelector(".title")).getText();
        WebElement priceProductPage = productPage.findElement(By.cssSelector(".regular-price"));
        WebElement campaignPriceProductPage = productPage.findElement(By.cssSelector(".campaign-price"));
        Float priceProductPageFontSize = Float.valueOf(priceProductPage.getCssValue("font-size").split("px")[0]);
        Float campaignPriceProductPageFontSize = Float.valueOf(campaignPriceProductPage.getCssValue("font-size").split("px")[0]);

        assertTrue(isRegularStyle(priceProductPage));
        assertTrue(isCampaignStyle(campaignPriceProductPage));
        assertTrue(campaignPriceProductPageFontSize > priceProductPageFontSize);

        assertEquals(nameMainPage, nameProductPage);
        assertEquals(priceMainPageTxt, priceProductPage.getText());
        assertEquals(campaignPriceMainPageTxt, campaignPriceProductPage.getText());
    }

    public boolean isRegularStyle(WebElement price) {
        String[] color = price.getCssValue("Color").split("\\(" )[1].split("\\)")[0].split(", ");
        String red = color[0];
        String green = color[1];
        String blue = color[2];

        return price.getCssValue("text-decoration").contains("line-through") && red.equals(green) && red.equals(blue);
    }

    public boolean isCampaignStyle(WebElement price) {
        String[] color = price.getCssValue("Color").split("\\(" )[1].split("\\)")[0].split(", ");
        String red = color[0];
        String green = color[1];
        String blue = color[2];

        return price.getTagName().equals("strong") && !red.equals("0") && green.equals("0") && blue.equals("0");
    }
}
