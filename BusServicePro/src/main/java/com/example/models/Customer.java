package com.example.models;

public class Customer extends User{


    public Customer(String name, String email, String username, String password, String phone) {
        super( name, email, username, password, phone);
    }
    public Customer(int id, String name, String email, String username, String password, String phone) {
        super(id, name, email, username, password, phone);
    }

    public void  viewBookingHistory () {

    }
    public void makeBooking() {

    }

    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public String getEmail() {
        return super.getEmail();
    }



    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }
}

