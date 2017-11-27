package com.selenium.test.assertions;

import com.selenium.test.abstracts.Price;
import org.testng.AssertJUnit;

/**
 * Created by Maciej Targosz on 11/27/2017.
 */
public class PriceAssertions extends AssertJUnit {

    /**
     * Asserts that two Price objects are equal. If they are not
     * an AssertionFailedError is thrown with the given message.
     */
    static public void assertEquals(String message, Price expected, Price actual) {
        if ((expected == null) && (actual == null)) {
            return;
        }
        if ((expected != null) && expected.equals(actual)) {
            return;
        }
        failNotEquals(message, expected, actual);
    }

    static private void failNotEquals(String message, Price expected, Price actual) {
        fail(format(message, expected, actual));
    }

    static String format(String message, Price expected, Price actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        return formatted + "expected:<" + expected.getValue() + expected.getCurrency() + "> but was:<" + actual.getValue() + actual.getCurrency() + ">";
    }

}
