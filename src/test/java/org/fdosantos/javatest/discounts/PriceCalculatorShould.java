package org.fdosantos.javatest.discounts;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PriceCalculatorShould {

    @Test
    public void total_zero_when_there_are_no_prices() {
        PriceCalculator priceCalculator = new PriceCalculator();
        assertThat(priceCalculator.getTotal(), is((double)0));
    }
    @Test
    public void total_is_the_sum_of_prices() {
        PriceCalculator calculator = new PriceCalculator();
        calculator.add(25.2);
        calculator.add(12.8);

        assertThat(calculator.getTotal(), is((double)38));
    }

    @Test
    public void apply_discount_to_prices() {
        PriceCalculator calculator = new PriceCalculator();

        calculator.add(15);
        calculator.add(45);

        calculator.setDiscount(50);

        assertThat(calculator.getTotal(), is((double)30));

    }
}