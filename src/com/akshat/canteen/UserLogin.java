package com.akshat.canteen;

import java.util.ArrayList;
import java.util.List;

public class UserLogin {
    static List<UserLogin> users = new ArrayList<>();

    private int user_id;
    private String username;
    private String password;

    public UserLogin(String username, String password) {
        new UserLogin(users.size(), username, password);
    }

    public UserLogin(int user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    static {
        users.add(new UserLogin( 0,"admin", "admin"));
        users.add(new UserLogin(1, "akash", "password"));
        users.add(new UserLogin(2, "vignesh", "Admin@123"));
    }
}
