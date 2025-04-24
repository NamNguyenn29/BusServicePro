package com.example.models;

public class Customer extends User{


    public Customer(String name, String email, String username, String password, String phone) {
        super( name, email, username, password, phone);
    }

    public void  viewBookingHistory () {

    }
    public void makeBooking() {

    }

    @Override
    public String getName() {
        return super.getName();
    }
}

