package com.chirag.financetracker;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;


public class TransactionManager {

    /* ─── Fields ─────────────────────────────────────────────────────────── */
    private final List<Transaction> transactions;        // Master ledger
    private final Queue<Transaction> recentTransactions; // 30‑day rolling queue
    private final DateTimeFormatter formatter;           // yyyy‑MM‑dd

    private static final int DAYS_30 = 30;

    /* ─── Constructor ────────────────────────────────────────────────────── */
    public TransactionManager() {
        this.transactions = new ArrayList<>();
        this.recentTransactions = new LinkedList<>();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    /* ─── CRUD ───────────────────────────────────────────────────────────── */
    public void addTransaction(String date, String type, String category, String notes, BigDecimal amount) {
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        Transaction tx = new Transaction(parsedDate, type, category, notes, amount);
        transactions.add(tx);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public void printAllTransactions() {
        transactions.forEach(System.out::println);
        System.out.println("Balance: " + calculateBalance());
    }

    private String calculateBalance() {
        return "";
    }

    public List<Transaction> searchByDate(String date) {
        LocalDate target = LocalDate.parse(date, formatter);
        return transactions.stream()
                .filter(t -> t.getDate().equals(target))
                .collect(Collectors.toList());
    }

    public boolean editTransaction(String date,
                                   String type,
                                   String category,
                                   String notes,
                                   BigDecimal amount) {
        LocalDate target = LocalDate.parse(date, formatter);
        for (Transaction t : transactions) {
            if (t.getDate().equals(target)) {
                if (type != null && !type.isEmpty()) t.setType(type);
                if (category != null && !category.isEmpty()) t.setCategory(category);
                if (notes != null && !notes.isEmpty()) t.setNotes(notes);
                if (amount != null) t.setAmount(amount);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTransaction(String date) {
        LocalDate target = LocalDate.parse(date, formatter);
        return transactions.removeIf(t -> t.getDate().equals(target));
    }

}

