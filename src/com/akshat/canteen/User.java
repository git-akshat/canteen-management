package com.akshat.canteen;

import java.util.ArrayList;
import java.util.List;

public class User {
    static List<User> users = new ArrayList<>();

    private int balance;
    private int user_id;
    private List<Integer> order_ids;

    public User(int balance, int user_id, List<Integer> order_ids) {
        this.balance = balance;
        this.user_id = user_id;
        this.order_ids = order_ids;
    }

    @Override
    public String toString() {
        return "User{" +
                "balance=" + balance +
                ", user_id=" + user_id +
                ", order_ids=" + order_ids +
                '}';
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<Integer> getOrder_ids() {
        return order_ids;
    }

    public void setOrder_ids(List<Integer> order_ids) {
        this.order_ids = order_ids;
    }

    static {
        users.add(new User(300, 1, new ArrayList<>()));
        users.add(new User(300, 2, new ArrayList<>()));
    }


}
