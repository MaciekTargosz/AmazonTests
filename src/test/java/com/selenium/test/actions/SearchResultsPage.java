package com.selenium.test.actions;

import com.selenium.test.pages.SearchResultsPageView;

/**
 * Created by Maciej Targosz on 11/26/2017.
 */
public class SearchResultsPage {

    private SearchResultsPageView searchResultsPageView;

    public SearchResultsPage(){
        searchResultsPageView = new SearchResultsPageView();
    }

    public SearchResultsPage(String textToSearch){
        searchResultsPageView = new SearchResultsPageView(textToSearch);
    }

    public ProductDetailsPage showProductDetails(int productOrdinalNumber){
        String productName = searchResultsPageView.clickProductName(productOrdinalNumber);
        return new ProductDetailsPage(productName);
    }

    public ProductDetailsPage showProductDetails(String productName){
        searchResultsPageView.clickProductName(productName);
        return new ProductDetailsPage(productName);
    }

}
