package edu.st.selenium.litecart.Task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.*;

import java.util.List;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage open(){
        driver.get("http://localhost/litecart/en/checkout");
        return this;
    }

    @FindBy(css="[name='remove_cart_item']")
    private List<WebElement> removeButtonsList;

    @FindBy(css = ".shortcuts .shortcut")
    private List<WebElement> shortcuts;

    private WebElement getTable() {
        return driver.findElement(By.cssSelector(".dataTable"));
    }

    @FindBy(css=".dataTable tr")
    private List<WebElement> tableRows;

    public void removeItemsOneByOne() {
        int itemsCount = shortcuts.size();

        for (int i = 0; i < itemsCount; i++){

            if (shortcuts.size() != 0) {
                shortcuts.get(0).click();
            }

            WebElement cartTable = getTable();
            int rowCount = tableRows.size();

            removeButtonsList.get(0).click();
            wait.until(ExpectedConditions.stalenessOf(cartTable));

            if (i == itemsCount - 1) {
                assertEquals(0, tableRows.size());
            } else {
                assertEquals(rowCount - 1, tableRows.size());
            }
        }
    }
}
