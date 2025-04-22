package com.example.models;

import java.time.LocalDate;

public class Feedback {
    static int count = 0;
    private int feedbackID;
    private String message;
    private LocalDate submmitDate;

    public Feedback( String message) {
        this.feedbackID = ++count;
        this.message = message;
        this.submmitDate = LocalDate.now();
    }
}
