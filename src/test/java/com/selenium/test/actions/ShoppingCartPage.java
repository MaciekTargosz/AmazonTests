package com.selenium.test.actions;

import com.selenium.test.abstracts.Price;
import com.selenium.test.pages.ShoppingCartPageView;

/**
 * Created by Maciej Targosz on 11/26/2017.
 */
public class ShoppingCartPage {

    private ShoppingCartPageView shoppingCartPageView;

    public ShoppingCartPage(){
        shoppingCartPageView = new ShoppingCartPageView(false);
    }

    public String getProductInCartName() {
        return shoppingCartPageView.getProductInCartName();
    }

    public Price getSubtotalPriceSum() {
        return new Price(shoppingCartPageView.getShoppingValue(), shoppingCartPageView.getShoppingCurrency());
    }
}
