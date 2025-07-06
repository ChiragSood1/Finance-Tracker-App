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
    public List<Transaction> filterByType(String type) {
        List<Transaction> list = new ArrayList<>();
        for (Transaction t : transactions) if (t.getType().equalsIgnoreCase(type)) list.add(t);
        return list;
    }

    public List<Transaction> filterByCategory(String category) {
        List<Transaction> list = new ArrayList<>();
        for (Transaction t : transactions) if (t.getCategory().equalsIgnoreCase(category)) list.add(t);
        return list;
    }

    public List<Transaction> filterByDateRange(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end   = LocalDate.parse(endDate,   formatter);
        List<Transaction> list = new ArrayList<>();
        for (Transaction t : transactions) {
            LocalDate d = t.getDate();
            if (!d.isBefore(start) && !d.isAfter(end)) list.add(t);
        }
        return list;
    }

    public List<Transaction> sortByDateDesc() {
        List<Transaction> list = new ArrayList<>(transactions);
        list.sort((a, b) -> b.getDate().compareTo(a.getDate()));
        return list;
    }

    public List<Transaction> sortByAmountDesc() {
        List<Transaction> list = new ArrayList<>(transactions);
        list.sort((a, b) -> b.getAmount().compareTo(a.getAmount()));
        return list;
    }

    public List<Transaction> searchByNotes(String keyword) {
        List<Transaction> list = new ArrayList<>();
        for (Transaction t : transactions) if (t.getNotes().contains(keyword)) list.add(t);
        return list;
    }

    /* ─── Analytics ──────────────────────────────────────────────────────── */

    public BigDecimal calculateBalance() {
        BigDecimal balance = BigDecimal.ZERO;
        for (Transaction t : transactions) {
            balance = "INCOME".equalsIgnoreCase(t.getType())
                    ? balance.add(t.getAmount())
                    : balance.subtract(t.getAmount());
        }
        return balance;
    }
    public void printSummary(int days) {
        LocalDate cutoff = LocalDate.now().minusDays(days);
        BigDecimal income = BigDecimal.ZERO;
        BigDecimal expense = BigDecimal.ZERO;
        int count = 0;

        for (Transaction t : recentTransactions) {
            if (!t.getDate().isBefore(cutoff)) {
                if (t.getType().equalsIgnoreCase("INCOME")) {
                    income = income.add(t.getAmount());
                } else {
                    expense = expense.add(t.getAmount());
                }
                count++;
            }
        }

        BigDecimal avgSpend = count > 0 ? expense.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;

        System.out.println("Summary (Last " + days + " days):");
        System.out.println("Income: " + income);
        System.out.println("Expense: " + expense);
        System.out.println("Avg Daily Expense: " + avgSpend);
    }


}

