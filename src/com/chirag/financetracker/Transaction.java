package com.chirag.financetracker;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
        private LocalDate   date;
        private String      type;      // "INCOME" or "EXPENSE"
        private String      category;
        private String      notes;
        private BigDecimal  amount;
    }

