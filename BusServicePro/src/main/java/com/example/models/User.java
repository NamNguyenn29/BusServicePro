package com.example.models;

public class User {
    private int userID;
    private String username, password, name, email, phone;

    public User(int userID, String username) {
        this.userID = userID;
        this.username = username;

    }

    public User(int customerID, String username, String password, String name, String email, String phone) {
        this.userID = customerID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getUserID() {
        return userID;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

}


