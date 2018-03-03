package edu.st.selenium.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.Random;
import static org.junit.Assert.*;

public class Task12AddProduct extends TestBase{

    @Test
    public void addProduct() {
        loginToAdmin("admin", "admin");
        driver.findElement(By.cssSelector("#app- a[href*=catalog]")).click();

        int productCount = getCatalogProductsCount();

        driver.findElement(By.cssSelector("a[href*=edit_product]")).click();

        File img = new File("src/test/resources/qiwi.jpg");

        Product randomProduct = new Product();
        randomProduct.setStatus(true);
        randomProduct.setName(produceRandomString(7));
        randomProduct.setCode(generateRandomIntString(5));
        randomProduct.setDefaultCategory("Rubber Ducks");
        randomProduct.setGroups(new String[] {"Female", "Unisex"});
        randomProduct.setQuantity(generateRandomInt(1, 10));
        randomProduct.setQuantityUnit("pcs");
        randomProduct.setDeliveryStatus("3-5 days");
        randomProduct.setSoldOutStatus("Sold out");
        randomProduct.setImagePath(img.getAbsolutePath());
        randomProduct.setDateFrom("01.01.2018");
        randomProduct.setDateTo("31.01.2018");
        randomProduct.setManufacturer("ACME Corp.");
        randomProduct.setKeywords(produceRandomString(10));
        randomProduct.setShortDescription(produceRandomString(10));
        randomProduct.setDescription(produceRandomString(20));
        randomProduct.setHeadTitle("qiwi");
        randomProduct.setMetaDescription(produceRandomString(10));
        randomProduct.setPurchasePrice(Float.valueOf(generateRandomInt(20, 100)));
        randomProduct.setCurrency("Euros");
        randomProduct.setPriceUsd(Float.valueOf(generateRandomInt(100, 200)));
        randomProduct.setPriceEur(randomProduct.getPriceUsd() * 1.2f);

        //Fill General Tab
        setRadioBtnStatus(randomProduct.getStatus());
        driver.findElement(By.cssSelector("[name='name[en]']")).sendKeys(randomProduct.getName());
        driver.findElement(By.cssSelector("[name='code']")).sendKeys(randomProduct.getCode());
        setCheckBoxStatus(driver.findElement(By.cssSelector("[type='checkbox'][data-name='" + randomProduct.getDefaultCategory() + "']")), true);

        Select selectDefaultCategory = new Select( driver.findElement(By.cssSelector("select[name='default_category_id']")));
        selectDefaultCategory.selectByVisibleText(randomProduct.getDefaultCategory());

        for (String group: randomProduct.getGroups()) {
            setCheckBoxStatus(driver.findElement(By.xpath(".//td[contains(text(),'" + group + "')]/..//input[@type='checkbox']")), true);
        }

        Select selectSoldOutStatus = new Select( driver.findElement(By.cssSelector("select[name='sold_out_status_id']")));
        selectSoldOutStatus.selectByVisibleText(randomProduct.getSoldOutStatus());

        driver.findElement(By.cssSelector("[name='quantity']")).sendKeys(String.valueOf(randomProduct.getQuantity()));
        driver.findElement(By.cssSelector("[name='new_images[]']")).sendKeys(img.getAbsolutePath());
        driver.findElement(By.cssSelector("[name='date_valid_from']")).sendKeys(randomProduct.getDateFrom());
        driver.findElement(By.cssSelector("[name='date_valid_to']")).sendKeys(randomProduct.getDateTo());

        //Fill Information Tab
        driver.findElement(By.cssSelector("a[href='#tab-information']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name='manufacturer_id']")));

        Select selectManufacturer = new Select( driver.findElement(By.cssSelector("select[name='manufacturer_id']")));
        selectManufacturer.selectByVisibleText(randomProduct.getManufacturer());

        driver.findElement(By.cssSelector("[name='keywords']")).sendKeys(randomProduct.getKeywords());
        driver.findElement(By.cssSelector("[name='short_description[en]']")).sendKeys(randomProduct.getShortDescription());
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys(randomProduct.getDescription());
        driver.findElement(By.cssSelector("[name='head_title[en]']")).sendKeys(randomProduct.getHeadTitle());
        driver.findElement(By.cssSelector("[name='meta_description[en]']")).sendKeys(randomProduct.getMetaDescription());

        //Fill Prices Tab
        driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='purchase_price']")));

        Actions setPurchasePrice = new Actions(driver).doubleClick(driver.findElement(By.cssSelector("[name='purchase_price']"))).sendKeys(String.valueOf(randomProduct.getPurchasePrice()));
        setPurchasePrice.build().perform();

        Select selectPurchaseCurrCode = new Select( driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")));
        selectPurchaseCurrCode.selectByVisibleText(randomProduct.getCurrency());

        driver.findElement(By.cssSelector("[name='prices[USD]']")).sendKeys(String.valueOf(randomProduct.getPriceUsd()));
        driver.findElement(By.cssSelector("[name='prices[EUR]']")).sendKeys(String.valueOf(randomProduct.getPriceEur()));
        driver.findElement(By.cssSelector("[name='save']")).click();

        //CheckCatalog
        assertEquals(getCatalogProductsCount(), productCount + 1);
        assertEquals(1, driver.findElements(By.linkText(randomProduct.getName())).size());
    }

    public int getCatalogProductsCount() {
        WebElement tblFooter = driver.findElement(By.cssSelector("[name='catalog_form'] .dataTable .footer td"));
        String count = tblFooter.getText().split("Products: ")[1];

        return Integer.valueOf(count);
    }

    public void setRadioBtnStatus(Boolean status) {
        if (status) {
            driver.findElement(By.cssSelector("input[name='status'][value='1']")).click();
        } else {
            driver.findElement(By.cssSelector("input[name='status'][value='0']")).click();
        }
    }

    public void setCheckBoxStatus(WebElement checkBox, Boolean status) {
        if (checkBox.isSelected() != (status)) {
            checkBox.click();
        }
    }

    public String produceRandomString(int length) {
        String symbols = "abcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        String str = "";

        for( int i = 0; i < length; i++ ) {
            str += symbols.charAt(rnd.nextInt(symbols.length()));
        }

        return str;
    }

    public static String generateRandomIntString(int length) {
        Random rnd = new Random();
        String str = "";
        for (int i = 0; i < length; i++) {
            str = str + String.valueOf(rnd.nextInt(10));
        }
        return str;
    }

    public static int generateRandomInt(int start, int end) {
        return start + (int) (Math.random() * end);
    }
}
