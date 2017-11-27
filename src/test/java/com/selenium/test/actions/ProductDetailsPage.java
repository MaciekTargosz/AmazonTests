package com.selenium.test.actions;

import com.selenium.test.abstracts.Price;
import com.selenium.test.pages.ProductDetailsPageView;

import java.math.BigDecimal;

/**
 * Created by Maciej Targosz on 11/26/2017.
 */
public class ProductDetailsPage {

    private ProductDetailsPageView productDetailsPageView;

    public ProductDetailsPage(String productName){
        productDetailsPageView = new ProductDetailsPageView(productName);
    }

    public ShoppingCartPage addProductToCartWithDefaultOptionsProposed(int quantity) {
        addProductToCart(quantity);
        return new ShoppingCartPage();
    }

    public ShoppingCartPage addProductToCartWithoutAdditionalOptionsProposed(int quantity) {
        addProductToCart(quantity);
        dismissAdditionalProtectionPlanOption();
        return new ShoppingCartPage();
    }

    private void dismissAdditionalProtectionPlanOption(){
        productDetailsPageView.dismissPCPeripheralsProtectionPlan();
    }

    private void addProductToCart(int quantity) {
        productDetailsPageView.selectQuantity(quantity);
        productDetailsPageView.clickAddToCartBtn();
    }

    public String getProductName() {
        return productDetailsPageView.getProductName();
    }

    public Price calculateExpectedPrice(int quantity) {
        return new Price(new BigDecimal(productDetailsPageView.getPrice()).multiply(new BigDecimal(quantity)), productDetailsPageView.getCurrency());
    }
}
