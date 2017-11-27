package com.selenium.test.pages;

import com.selenium.test.configuration.properties.Property;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

import static com.selenium.test.utils.ActionBot.moveMouseToElement;
import static com.selenium.test.utils.WaitUtils.waitUntilElementVisible;
import static com.selenium.test.utils.WaitUtils.waitUntilPageTitleIs;

/**
 * Created by Maciej Targosz on 11/26/2017.
 */
public class ShoppingCartPageView extends BasePage {

    @FindBy(css = "span.a-inline-block.hlb-price")
    WebElement cartSubtotalContainer;

    @Property("shopping.cart.page.title")
    private String shoppingCartPageTitle = "EMPTY";

    private static final String PRODUCT_IN_CART_IMAGE_SELECTOR = "div.a-box-inner img";

    private static final String PRODUCT_POPOVER_HYPERLINK_SELECTOR = "div#mdp-title a";

    public ShoppingCartPageView(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    @Override
    public void openPage() {
        //TODO
    }

    @Override
    public boolean isPageOpened() {
        return waitUntilPageTitleIs(shoppingCartPageTitle);
    }

    public String getProductInCartName() {
        moveMouseToElement(By.cssSelector(PRODUCT_IN_CART_IMAGE_SELECTOR));
        return waitUntilElementVisible(By.cssSelector(PRODUCT_POPOVER_HYPERLINK_SELECTOR)).getText();
    }

    public BigDecimal getShoppingValue() {
        return new BigDecimal(cartSubtotalContainer.getText().substring(1));
    }

    public char getShoppingCurrency() {
        return cartSubtotalContainer.getText().charAt(0);
    }
}
