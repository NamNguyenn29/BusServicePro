package com.example.models;

import java.util.List;

public class Admin {
    private int adminID;
    private String username, password;

    public Admin(int adminID, String username, String password) {
        this.adminID = adminID;
        this.username = username;
        this.password = password;
    }

    public int getAdminID() {
        return adminID;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public boolean addRoute(Route route) {
        return false;
    }
    public List<Feedback> viewFeedback() {
        return null;
    }
}
