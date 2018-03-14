package edu.st.selenium.litecart.Task19.app;

import edu.st.selenium.litecart.Task19.pages.CartPage;
import edu.st.selenium.litecart.Task19.pages.MainPage;
import edu.st.selenium.litecart.Task19.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Application {

    private WebDriver driver;

    public MainPage mainPage;
    public ProductPage productPage;
    private CartPage cartPage;

    public Application() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public int getCartItemsCount() {
        return mainPage.open().cartItemsCount();
    }
    
    public void addRandomProductsToCart(int count) {
        for (int i = 0; i < count; i++) {
            mainPage.open().getRandomProduct().click();
            productPage.addToCart();
        }
    }

    public void removeAllItemsFromCart() {
        cartPage.open().removeItemsOneByOne();
    }
}
