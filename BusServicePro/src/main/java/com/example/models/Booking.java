package com.example.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {
    private int bookingID;
    private LocalDateTime bookingTime;
    private int noSeat;
    private Trip trip;
    private Customer customer;
    public void cancle() {

    }
}
// khi người dùng book thì booking sẽ được tạo dựa trên user và trip , sau đó trong trip sẽ xử lí trừ ghế đi và booking mới sẽ đc tạo ra