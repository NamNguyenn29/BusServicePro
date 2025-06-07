package com.example.models;

public class User {
    private int userID;
    private String username, password, name, email, phone;

    public User(int userID, String username) {
        this.userID = userID;
        this.username = username;

    }
//    load user
    public User(int userID, String username, String password, String name, String email, String phone) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // tao user
   public User(String username, String password, String name, String email, String phone) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
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



    @Override
    public String toString() {
        return name ;
    }
}


