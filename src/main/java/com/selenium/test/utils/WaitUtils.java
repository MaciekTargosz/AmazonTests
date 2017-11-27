package com.selenium.test.utils;

import com.selenium.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.selenium.test.utils.ActionBot.click;
import static com.selenium.test.webtestsbase.WebDriverFactory.getDriver;

/**
 * Created by Maciej Targosz on 11/26/2017.
 */
public class WaitUtils {

    private static final long DEFAULT_TIMEOUT_IN_SECONDS = 5;
    private static final long LONG_TIMEOUT_IN_SECONDS = 10;

    public static Boolean waitUntilPageTitleIs(String pageTitle) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), DEFAULT_TIMEOUT_IN_SECONDS);
        return wait.until(ExpectedConditions.titleIs(pageTitle));
    }

    public static Boolean waitUntilPageTitleContains(String pageTitle) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), DEFAULT_TIMEOUT_IN_SECONDS);
        return wait.until(ExpectedConditions.titleContains(pageTitle));
    }

    public static void waitUntilElementClickableAndClickOnIt(By locator) throws TimeoutException{
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), DEFAULT_TIMEOUT_IN_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        click(getDriver().findElement(locator));
    }

    public static WebElement waitUntilElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), LONG_TIMEOUT_IN_SECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitUntilElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), DEFAULT_TIMEOUT_IN_SECONDS);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
