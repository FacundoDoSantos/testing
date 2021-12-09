package org.fdosantos.javatest.discounts;

import java.util.ArrayList;
import java.util.List;

public class PriceCalculator {

    private double total = 0;
    private List<Double> prices = new ArrayList<>();
    private boolean actived = false;
    private double discount;

    public double getTotal() {
        for (Double price : prices) total += price;
        if (actived){
            total = total - (total * (discount / 100));
        }
        return total;
    }
    public void add(double price){
        prices.add(price);
    }
    public void setDiscount(double discount) {
        actived = true;
        this.discount = discount;
    }
}
