package com.selenium.test.assertions;

import com.selenium.test.abstracts.Price;
import org.testng.AssertJUnit;

import static com.selenium.test.utils.StringUtils.getMeaningfulPartOfString;

/**
 * Created by Maciej Targosz on 11/27/2017.
 */
public class ProductsAssertions extends AssertJUnit {

    /**
     * Asserts that two productNames objects are equal. If they are not
     * an AssertionFailedError is thrown with the given message.
     */
    static public void assertProductNamesEquals(String message, int meaningfulPartLength ,String expected, String actual) {
        if ((expected == null) && (actual == null)) {
            return;
        }
        if ((expected != null) && getMeaningfulPartOfString(expected, meaningfulPartLength).equals(getMeaningfulPartOfString(actual, meaningfulPartLength))) {
            return;
        }
        new AssertionError(format(message, expected, actual));
    }

    static String format(String message, Object expected, Object actual) {
        String formatted = "";
        if(message != null) {
            formatted = message + " ";
        }

        return formatted + "expected:<" + expected + "> but was:<" + actual + ">";
    }
}
