package com.selenium.test.pages;

import com.selenium.test.configuration.properties.Property;
import com.selenium.test.configuration.properties.PropertyFile;
import com.selenium.test.webtestsbase.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.selenium.test.utils.ActionBot.setInputValue;

/**
 * Created by Maciej Targosz on 11/26/2017.
 */
@PropertyFile("language_en.properties")
public class LandingPageView extends BasePage {

    public LandingPageView(){
        super(false);
    }

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchInput;

    @FindBy(css = "input[value=\"Go\"]")
    private WebElement searchBtn;

    @Property("sut.url")
    private String landingPageUrl;

    @Property("landing.page.title")
    private String landingPageTitle = "EMPTY";

    @Override
    public void openPage() {
        getDriver().get(landingPageUrl);
    }

    @Override
    public boolean isPageOpened() {
        return getDriver().getTitle().equalsIgnoreCase(landingPageTitle);
    }

    public void setSearchInputValue(String textToSearch) {
        setInputValue(searchInput, textToSearch);
    }

    public void clickSearchBtn() {
        searchBtn.click();
    }
}
