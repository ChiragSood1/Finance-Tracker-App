package com.chirag.financetracker;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String type;      // "INCOME" or "EXPENSE"
    private String category;
    private String notes;
    private BigDecimal amount;

    public Transaction(LocalDate date, String type, String category, String description, BigDecimal amount) {
        this.date = date;               // Set the date.
        this.type = type;               // Set the type.
        this.category = category;       // Set the category.
        this.notes = notes;             // Extra details
        this.amount = amount;           // Set the amount.
    }
}



