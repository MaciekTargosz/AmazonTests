package com.selenium.test.testng.tests;

import com.selenium.test.abstracts.Price;
import com.selenium.test.actions.LandingPage;
import com.selenium.test.actions.ProductDetailsPage;
import com.selenium.test.actions.ShoppingCartPage;
import com.selenium.test.assertions.PriceAssertions;
import com.selenium.test.pages.LandingPageView;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.selenium.test.assertions.PriceAssertions.assertEquals;
import static com.selenium.test.assertions.ProductsAssertions.assertProductNamesEquals;
import static com.selenium.test.utils.StringUtils.getMeaningfulPartOfString;
import static com.selenium.test.webtestsbase.WebDriverFactory.getDriver;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class AmazonTest {

    @BeforeTest
    public void beforeTest() {
        WebDriverFactory.startBrowser(true);
    }

    /**
     *
     1. Navigate to "www.amazon.com"
     2. Go into "Best sellers in Digital Cameras"
     3. Open details of 5-th product
     4. Add 8 pieces to the shopping cart (don't accept any other options that are proposed by the e-shop)
     5. Check that correct product was added
     6. Check that subtotal price sum is correct
     *
     * NOTE: In my opinion Test Case is not good ;) - it's not deterministic, because content of Amazon's page is dynamic
     * and sometimes for 5-th product shop doesn't allow any additional options
     *
     */
    @Test
    public void shouldAddProductToCartWithoutAnyAdditionalOptions() {
        int quantity = 8;
        ProductDetailsPage productDetailsPage = new LandingPage()
                .open()
                .search("Best sellers in Digital Cameras")
                    .showProductDetails(5);

        String expectedProductName = productDetailsPage.getProductName();
        Price expectedPrice = productDetailsPage.calculateExpectedPrice(quantity);

        ShoppingCartPage shoppingCartPage = productDetailsPage
                        .addProductToCartWithoutAdditionalOptionsProposed(quantity);

        assertProductNamesEquals("Correct product should be added", 50, expectedProductName, shoppingCartPage.getProductInCartName());
        assertEquals("Subtotal price sum should be correct", expectedPrice, shoppingCartPage.getSubtotalPriceSum());
    }

    @DataProvider(name = "addPCPeripheralsProductData")
    public Object[][] createData() {
        String productThatShouldBeAdded = "Logitech M705 Wireless Marathon Mouse";
        String textToSearch = "Best sellers in Digital Cameras";
        int quantity = 8;

        return new Object[][] {
                {productThatShouldBeAdded, textToSearch, quantity}
        };
    }

    /**
     * Similar, but deterministic scenario can look like this
     */
    @Test(dataProvider = "addPCPeripheralsProductData")
    public void shouldAddPCPeripheralsProductToCartWithoutAnyAdditionalOptions(String productThatShouldBeAddedName, String textToSearch, int quantity) {

        ProductDetailsPage productDetailsPage = new LandingPage()
                .open()
                .search(textToSearch)
                .showProductDetails(productThatShouldBeAddedName);

        Price expectedPrice = productDetailsPage.calculateExpectedPrice(quantity);

        ShoppingCartPage shoppingCartPage = productDetailsPage
                .addProductToCartWithoutAdditionalOptionsProposed(quantity);

        assertProductNamesEquals("Correct product should be added", 50, productThatShouldBeAddedName, shoppingCartPage.getProductInCartName());
        assertEquals("Subtotal price sum should be correct", expectedPrice, shoppingCartPage.getSubtotalPriceSum());
    }

    @AfterTest
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }
}
