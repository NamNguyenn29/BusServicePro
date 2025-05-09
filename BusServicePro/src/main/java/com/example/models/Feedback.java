package com.example.models;

import java.time.LocalDate;

public class Feedback {
    private int feedbackId;
    private String message;
    private LocalDate submitDate;

    public Feedback(int feedbackId, String message, LocalDate submitDate) {
        this.feedbackId = feedbackId;
        this.message = message;
        this.submitDate = submitDate;
    }
}
