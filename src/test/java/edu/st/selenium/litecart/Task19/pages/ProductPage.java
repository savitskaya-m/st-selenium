package edu.st.selenium.litecart.Task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private Select getSizeSelector() {
        return new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
    }

    @FindBy(css = "[name='add_cart_product']")
    private WebElement addToCartBtn;

    @FindBy(css = ".quantity")
    private WebElement qantityInCart;

    private int cartItemsCount() {
        return Integer.valueOf(qantityInCart.getAttribute("textContent"));
    }

    private void selectRandomSize() {
        getSizeSelector().selectByIndex(generateRandomInt(1, getSizeSelector().getOptions().size() - 1));
    }

    public void addToCart() {
        int cartCount = cartItemsCount();

        if (isElementPresentOnPage(By.cssSelector("select[name='options[Size]']"))) {
            selectRandomSize();
        }

        addToCartBtn.click();
        wait.until(ExpectedConditions.attributeContains(qantityInCart, "textContent", String.valueOf(cartCount + 1)));
    }

}
