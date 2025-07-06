package com.chirag.financetracker;

import java.util.Scanner;

public class FinanceTrackerApp {

    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();   // core service
        Scanner             scanner = new Scanner(System.in);    // user input

        while (true) {
            // ───────────── Main Menu ─────────────
            System.out.println("\nMain Menu");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. Edit Transaction");
            System.out.println("4. Delete Transaction");
            System.out.println("5. Summary");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();          // clear newline

            switch (choice) {
                case 1 -> System.out.println("Add — TODO");
                case 2 -> System.out.println("View — TODO");
                case 3 -> System.out.println("Edit — TODO");
                case 4 -> System.out.println("Delete — TODO");
                case 5 -> System.out.println("Summary — TODO");
                case 6 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}

