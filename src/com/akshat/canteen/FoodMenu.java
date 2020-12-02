package com.akshat.canteen;

import java.util.ArrayList;
import java.util.List;

public class FoodMenu {
    final static int UNLIMITED = Integer.MAX_VALUE;
    static List<FoodMenu> foodMenus = new ArrayList<>();

    private String item;
    private int available_count;
    private int price;
    private int maxPerCust;

    public FoodMenu(String item, int available_count, int price, int maxPerCust) {
        this.item = item;
        this.available_count = available_count;
        this.price = price;
        this.maxPerCust = maxPerCust;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAvailable_count() {
        return available_count;
    }

    public void setAvailable_count(int available_count) {
        this.available_count = available_count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaxPerCust() {
        return maxPerCust;
    }

    public void setMaxPerCust(int maxPerCust) {
        this.maxPerCust = maxPerCust;
    }

    @Override
    public String toString() {
        return "FoodMenu{" +
                "item='" + item + '\'' +
                ", available_count=" + available_count +
                ", price=" + price +
                ", maxPerCust=" + (maxPerCust==Integer.MAX_VALUE ? "Unlimited" : maxPerCust) +
                '}';
    }

    static {
        foodMenus.add(new FoodMenu("Dosa", 5, 30, UNLIMITED));
        foodMenus.add(new FoodMenu("Idli", 15, 10, UNLIMITED));
        foodMenus.add(new FoodMenu("Masala Dosa", 3, 50, 1));
        foodMenus.add(new FoodMenu("Vada", 10, 10, 2));
        foodMenus.add(new FoodMenu("Pongal", 8, 25, UNLIMITED));

    }
}
