package com.chirag.financetracker;

public class TransactionManager {
    // Member variables
    private List<Record> records;                   // List to store all records.
    private Queue<Record> recentRecordsQueue;       // Queue to store recent records for quick access.
    private SimpleDateFormat dateFormat;            // Date format for parsing and formatting dates.
    private static final int DAYS_30 = 30;          // Constant for 30 days.

    // Constructor to initialize the TransactionManager.
    public TransactionManager() {
        this.records = new ArrayList<>();           // Initialize the records list.
        this.recentRecordsQueue = new LinkedList<>(); // Initialize the recent records queue.
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Initialize the date format.
    }
}
