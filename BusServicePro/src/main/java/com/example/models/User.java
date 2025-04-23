package com.example.models;

public abstract class User {
    static  int count = 0;
    protected int id;
    protected String name;
    protected String email;
    protected String username;
    protected String password;
    protected String phone;



    public User( String name, String email, String username, String password, String phone) {
        this.id = ++count;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

}
