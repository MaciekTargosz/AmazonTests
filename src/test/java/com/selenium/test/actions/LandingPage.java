package com.selenium.test.actions;

import com.selenium.test.pages.LandingPageView;

import javax.naming.directory.SearchResult;

/**
 * Created by Maciej Targosz on 11/26/2017.
 */
public class LandingPage {

    private LandingPageView landingPageView;

    public LandingPage(){
        landingPageView = new LandingPageView();
    }

    public LandingPage open(){
        landingPageView.openPage();
        return this;
    }

    public SearchResultsPage search(String textToSearch) {
        landingPageView.setSearchInputValue(textToSearch);
        landingPageView.clickSearchBtn();
        return new SearchResultsPage(textToSearch);
    }
}
