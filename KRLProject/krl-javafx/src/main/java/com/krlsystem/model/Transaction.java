package com.krlsystem.model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int userId;
    private Station origin;
    private Station destination;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(int id, int userId, Station origin, Station destination, double amount,
            LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public Station getOrigin() {
        return origin;
    }

    public Station getDestination() {
        return destination;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
