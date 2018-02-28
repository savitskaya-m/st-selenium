package edu.st.selenium.litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class Task11CreateAccountLoginLogout extends TestBase{

    @Test
    public void createAccountLoginLogout() {
        driver.get("http://localhost/litecart/en/create_account");

        Select selectCountry = new Select(driver.findElement(By.cssSelector("select[name=country_code]")));
        selectCountry.selectByVisibleText("United States");

        Account customerAccount = new Account();
        customerAccount.setFirstName(generateRandomString());
        customerAccount.setLastName(generateRandomString());
        customerAccount.setAddress1(generateRandomString());
        customerAccount.setPostcode(generateRandomIntString(5));
        customerAccount.setCity(generateRandomString());
        customerAccount.setCountry("United States");
        customerAccount.setZone(generateRandomInt(0, 64));
        customerAccount.setEmail(generateRandomEmail());
        customerAccount.setPhone(generateRandomIntString(10));
        customerAccount.setPassword(generateRandomString());

        driver.findElement(By.cssSelector("[name=firstname")).sendKeys(customerAccount.getFirstName());
        driver.findElement(By.cssSelector("[name=lastname]")).sendKeys(customerAccount.getLastName());
        driver.findElement(By.cssSelector("[name=address1]")).sendKeys(customerAccount.getAddress1());
        driver.findElement(By.cssSelector("[name=postcode]")).sendKeys(customerAccount.getPostcode());
        driver.findElement(By.cssSelector("[name=city]")).sendKeys(customerAccount.getCity());

        Select selectZone = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        selectZone.selectByIndex(generateRandomInt(0, selectZone.getOptions().size() - 1));

        driver.findElement(By.cssSelector("[name=email]")).sendKeys(customerAccount.getEmail());
        driver.findElement(By.cssSelector("[name=phone]")).sendKeys(customerAccount.getPhone());
        driver.findElement(By.cssSelector("[name=password]")).sendKeys(customerAccount.getPassword());
        driver.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys(customerAccount.getPassword());
        driver.findElement(By.cssSelector("[name=create_account]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*=logout]")));

        logout();

        login(customerAccount.getEmail(), customerAccount.getPassword());
    }

    public void login(String login, String password) {
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(login);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=login]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*=logout]")));
    }

    public void logout() {
        driver.findElement(By.cssSelector("a[href*=logout]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name=login_form]")));
    }

    public static String generateRandomString() {
        Random rnd = new Random();
        return "test-" + String.valueOf(rnd.nextInt(1000));
    }

    public static String generateRandomEmail() {
        Random rnd = new Random();
        return "email-" + String.valueOf(rnd.nextInt(1000)) + "@mail.ru";
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

