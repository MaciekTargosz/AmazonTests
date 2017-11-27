package com.selenium.test.pages;

import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.selenium.test.utils.ActionBot.click;
import static com.selenium.test.utils.WaitUtils.waitUntilElementClickableAndClickOnIt;
import static com.selenium.test.utils.WaitUtils.waitUntilPageTitleIs;
import static org.testng.Assert.assertTrue;

/**
 * Created by Maciej Targosz on 11/26/2017.
 */
public class SearchResultsPageView extends BasePage {

    @FindBy(css = "a.s-access-detail-page")
    List<WebElement> products;

    private String textToBeSearchFor;

    public SearchResultsPageView() {
        super(false);
    }

    public SearchResultsPageView(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    public SearchResultsPageView(String textToBeSearchFor) {
        super(false);
        this.textToBeSearchFor = textToBeSearchFor;
    }

    @Override
    public void openPage() {
        //TODO
    }

    @Override
    public boolean isPageOpened() {
        return waitUntilPageTitleIs(textToBeSearchFor);
    }

    public String clickProductName(Integer productOrdinalNumber) {
        Integer productsQuantity = products.size();
        assertTrue(productsQuantity > 0 && productOrdinalNumber.compareTo(productsQuantity) < 0);
        WebElement product = products.get(productOrdinalNumber - 1);
        String productName = product.getAttribute("title");
        click(product);
        return productName;
    }

    public void clickProductName(String productName) {
        waitUntilElementClickableAndClickOnIt(By.linkText(productName));
    }
}
