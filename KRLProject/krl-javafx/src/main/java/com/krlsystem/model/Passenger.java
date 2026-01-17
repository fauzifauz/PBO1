package com.krlsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends User {
    private double balance;
    private List<Transaction> history;

    public Passenger(int id, String username, String password, double balance) {
        super(id, username, password, "USER");
        this.balance = balance;
        this.history = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getHistory() {
        return history;
    }

    public void addTransaction(Transaction trx) {
        this.history.add(trx);
    }
}
