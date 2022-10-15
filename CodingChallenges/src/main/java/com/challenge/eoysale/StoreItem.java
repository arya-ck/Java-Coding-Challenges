package com.challenge.eoysale;

import java.util.List;
import java.util.Optional;

public class StoreItem {
    String name;
    double retailPrice;
    double discount;

    public StoreItem(String name, double retailPrice, double discount) {
        this.name = name;
        this.retailPrice = retailPrice;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public static Optional<StoreItem> findLeastExpensive(List<StoreItem> items){

        Optional<StoreItem> storeItem =
                items.stream()
                        .sorted((item1, item2) -> // sort items in ascending order of sale price
                                (item1.calculateCurrentPrice() > item2.calculateCurrentPrice())? 1: -1)
                        .findFirst();
        return storeItem;
    }

    private double calculateCurrentPrice() {
        return retailPrice - (retailPrice * discount);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", " + "Retail price: " +
                retailPrice + ", " + "Discount " + discount;
    }
}
