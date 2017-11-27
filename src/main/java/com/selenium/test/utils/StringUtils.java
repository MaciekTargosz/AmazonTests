package com.selenium.test.utils;

/**
 * Created by Maciej Targosz on 11/27/2017.
 */
public class StringUtils {

    public static final String getMeaningfulPartOfString(String text, int meaningfulPartLength){
        if(text.length() > meaningfulPartLength){
            return text.substring(0, meaningfulPartLength);
        }
        return text;
    }
}
