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

    public Admin(String username, String password) {
        this.adminID = adminID;
        this.username = username;
        this.password = password;
    }

    public int getAdminID() {
        return adminID;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public void setUsername(String username) { this.username = username; }

    public boolean addRoute(Route route) {
        return false;
    }
    public List<Feedback> viewFeedback() {
        return null;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminID=" + adminID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}




// add min có thể addbus
// xoá bus by id
// updateBus


// có thể xem all booking

// có thể get all feedback


// có thể add route , khi add route đồng thời add các routeStop
// có thể xóa route


// add trip , updatetrip ,delete trip