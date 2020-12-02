package com.akshat.canteen;

public class Order {
    private int orderId;
    private String item_name;
    private int quantity;
    private int price;

    public Order(int orderId, String item_name, int quantity, int price) {
        this.orderId = orderId;
        this.item_name = item_name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
