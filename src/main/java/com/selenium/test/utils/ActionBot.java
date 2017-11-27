package com.selenium.test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.selenium.test.utils.WaitUtils.waitUntilElementVisible;
import static com.selenium.test.webtestsbase.WebDriverFactory.getDriver;

/**
 * Created by Maciej Targosz on 11/26/2017.
 */
public class ActionBot {

    public static void click(WebElement element){
        element.click();
    }

    public static void setInputValue(WebElement input, String textToSearch) {
        input.clear();
        input.sendKeys(textToSearch);
    }

    public static void moveMouseToElement(By selector){
        waitUntilElementVisible(selector);
        Actions action = new Actions(getDriver());
        action
                .moveToElement(getDriver()
                        .findElement(selector))
                .build()
                .perform();
    }

    public static List<String> getAvailableOptions(Select select){
        List<WebElement> webElements = select.getOptions();
        if(webElements.size() > 0){
            List<String> options = new ArrayList<>();
            for(WebElement element : webElements){
                options.add(element.getAttribute("value"));
            }
            return options;
        }
        return Collections.EMPTY_LIST;
    }
}
