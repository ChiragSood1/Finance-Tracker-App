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


    // Returns the date of the record.
    public LocalDate getDate() {
        return date;
    }

    // Returns the type (e.g., "INCOME" or "EXPENSE").
    public String getType() {
        return type;
    }

    // Returns the category (e.g., Groceries, Rent).
    public String getCategory() {
        return category;
    }

    // Returns extra notes or description attached to the record.
    public String getNotes() {
        return notes;
    }

    // Returns the monetary amount of the record.
    public BigDecimal getAmount() {
        return amount;
    }

    // Sets/updates the date of the record.
    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Sets/updates the type of the record.
    public void setType(String type) {
        this.type = type;
    }

    // Sets/updates the category of the record.
    public void setCategory(String category) {
        this.category = category;
    }

    // Sets/updates the notes/description of the record.
    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Sets/updates the monetary amount of the record.
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return String.format(
                "Record[date=%s, type=%s, category=%s, description=%s, amount=%.2f]",
                date, type, category, notes, amount);
    }


}



