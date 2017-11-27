package com.selenium.test.webtestsbase;

import com.selenium.test.configuration.TestsData;
import com.selenium.test.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Sidelnikov Mikhail on 19.09.14.
 * This is the main class for pages. When you create your page - you must extend your class from this
 */
public abstract class BasePage extends TestsData{

    /**
     * In subclasses  should be used for page opening
     */
    public abstract void openPage();

    /**
     * checks is page opened
     * @return true if opened
     */
    public abstract boolean isPageOpened();

    public BasePage(boolean openPageByUrl){
        TestsData.getData();
        if(openPageByUrl){
            openPage();
        }
        PageFactory.initElements(getDriver(), this);
    }

    /**
     * getting webdriver instance
     * @return initialized in tests webdriver instance
     */
    protected WebDriver getDriver(){
        return WebDriverFactory.getDriver();
    }

}
