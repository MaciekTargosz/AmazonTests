package com.selenium.test.abstracts;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Maciej Targosz on 11/27/2017.
 */
public class Price {

    private BigDecimal value;
    private char currency;

    public Price(BigDecimal value, char currency){
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public char getCurrency() {
        return currency;
    }

    public void setCurrency(char currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;

        Price price = (Price) o;

        if (getCurrency() != price.getCurrency()) return false;
        if (getValue().compareTo(price.getValue()) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getValue().hashCode();
        result = 31 * result + (int) getCurrency();
        return result;
    }
}
