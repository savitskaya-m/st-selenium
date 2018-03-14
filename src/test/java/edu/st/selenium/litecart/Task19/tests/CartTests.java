package edu.st.selenium.litecart.Task19.tests;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartTests extends TestBase {

    @Test
    public void addRemoveItemsFromCart() {
        int itemsInCartCount = app.getCartItemsCount();
        int itemsToAddCount = 3;

        app.addRandomProductsToCart(itemsToAddCount);
        assertEquals(itemsInCartCount + itemsToAddCount, app.getCartItemsCount());
        app.removeAllItemsFromCart();
        assertEquals(0, app.getCartItemsCount());
    }
}
