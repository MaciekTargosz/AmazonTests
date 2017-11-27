package com.selenium.test.pages;

import com.selenium.test.utils.WaitUtils;
import com.selenium.test.webtestsbase.BasePage;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.selenium.test.utils.ActionBot.click;
import static com.selenium.test.utils.ActionBot.getAvailableOptions;
import static com.selenium.test.utils.ActionBot.moveMouseToElement;
import static com.selenium.test.utils.WaitUtils.waitUntilElementClickable;
import static com.selenium.test.utils.WaitUtils.waitUntilElementClickableAndClickOnIt;
import static com.selenium.test.utils.WaitUtils.waitUntilPageTitleContains;
import static com.selenium.test.webtestsbase.WebDriverFactory.getDriver;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Maciej Targosz on 11/26/2017.
 */
public class ProductDetailsPageView extends BasePage {

    @FindBy(id = "quantity")
    private WebElement quantitySelector;

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartBtn;

    @FindBy(id = "priceblock_ourprice")
    private WebElement priceContainer;

    private String productName;

    public ProductDetailsPageView(boolean openPageByUrl, String productName) {
        super(openPageByUrl);
        this.productName = productName;
    }

    public ProductDetailsPageView(String productName) {
        super(false);
        this.productName = productName;
    }

    @Override
    public void openPage() {

    }

    @Override
    public boolean isPageOpened() {
        return waitUntilPageTitleContains(productName);
    }

    public void selectQuantity(Integer quantity) {
        Select quantitySelect = new Select(quantitySelector);
        assertTrue("There is no quantity '" + quantity + "' available for product '" + productName + "'.\n" +
                "There are only " + getLastOption(quantitySelect) + " piece(s) available." ,
                getAvailableOptions(quantitySelect).contains(quantity.toString()));
        quantitySelect.selectByValue(quantity.toString());
    }

    private String getLastOption(Select quantitySelect) {
        Integer size = quantitySelect.getOptions().size();
        return quantitySelect.getOptions().get(size-1).getText().trim();
    }

    public void clickAddToCartBtn() {
        click(addToCartBtn);
    }

    /**
     * This is a "hack" for Modal window which appears or not depending on product selected
     */
    public void dismissPCPeripheralsProtectionPlan() {
        try {
            waitUntilElementClickableAndClickOnIt(By.id("siNoCoverage-announce"));//this is optional - depending on product selected
            moveMouseToElement(By.cssSelector("div.a-box-inner img"));
            System.out.println("Modal Windows with PC Peripherials Protection Plan DISMISSED"); //Simple logging solution, usually I'd use Log4j or similar tool
        }
        catch (TimeoutException te){
            System.out.println("Modal Windows with PC Peripherials Protection Plan didn't show"); //Simple logging solution, usually I'd use Log4j or similar tool
        }
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return priceContainer.getText().substring(1);
    }

    public char getCurrency() {
        return priceContainer.getText().charAt(0);
    }
}
