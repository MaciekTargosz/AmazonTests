package com.selenium.test.abstracts;

import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

/**
 * Created by Maciej Targosz on 11/27/2017.
 */
public class PriceTest {

    @Test
    public void pricesWithDifferentCurrencyShouldNotBeEqual() throws Exception {
        Price p1 = new Price(new BigDecimal("2.00"), '$');
        Price p2 = new Price(new BigDecimal("2.00"), '€');
        assertFalse(p1.equals(p2));
    }
    @Test
    public void pricesWithSameValueAndDifferentPrecisionShouldBeEqual() throws Exception {
        Price p1 = new Price(new BigDecimal("2.0"), '$');
        Price p2 = new Price(new BigDecimal("2.000"), '$');
        assertTrue(p1.equals(p2));
    }

    @Test
    public void pricesWithSlightlyDifferentValuesInTermsOfScaleShouldNotBeEqual() throws Exception {
        Price p1 = new Price(new BigDecimal("2.00"), '$');
        Price p2 = new Price(new BigDecimal("1.994"), '$');
        assertFalse(p1.equals(p2));
    }

    @Test
    public void pricesWithDifferentValuesButEqualInTermsOfScaleShouldBeEqual() throws Exception {
        Price p1 = new Price(new BigDecimal("2.00"), '$');
        Price p2 = new Price(new BigDecimal("1.995"), '$');
        assertTrue(p1.equals(p2));
    }



    @Test
    public void pricesWithDifferentValuesShouldNotBeEqual() throws Exception {
        Price p1 = new Price(new BigDecimal("2.00"), '$');
        Price p2 = new Price(new BigDecimal("3.01"), '$');
        assertFalse(p1.equals(p2));
    }

}