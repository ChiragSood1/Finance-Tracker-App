package com.chirag.financetracker;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class FinanceTrackerApp {

    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1. Add Transaction");
            System.out.println("2. View All");
            System.out.println("3. Edit Transaction");
            System.out.println("4. Delete Transaction");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {
                case 1 -> add(manager, scanner);
                case 2 -> printList(manager.getAllTransactions());
                case 3 -> edit(manager, scanner);
                case 4 -> delete(manager, scanner);
                case 5 -> {
                    System.out.println("Good‑bye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    /* ─── Add ─────────────────────────────────────────────────────────── */
    private static void add(TransactionManager m, Scanner sc) {
        System.out.print("Date (yyyy‑MM‑dd): ");
        String date = sc.nextLine();
        System.out.print("Type (INCOME/EXPENSE): ");
        String type = sc.nextLine();
        System.out.print("Category: ");
        String category = sc.nextLine();
        System.out.print("Notes: ");
        String notes = sc.nextLine();
        System.out.print("Amount: ");
        double amt = sc.nextDouble();
        sc.nextLine();

        m.addTransaction(date, type, category, notes, BigDecimal.valueOf(amt));
        System.out.println("✔ Transaction added.");
    }

    /* ─── Edit ─────────────────────────────────────────────────────────── */
    private static void edit(TransactionManager m, Scanner sc) {
        System.out.print("Date to edit: ");
        String date = sc.nextLine();
        System.out.print("New Type (blank skip): ");
        String type = sc.nextLine();
        System.out.print("New Category (blank skip): ");
        String cat = sc.nextLine();
        System.out.print("New Notes (blank skip): ");
        String notes = sc.nextLine();
        System.out.print("New Amount (-1 skip): ");
        double val = sc.nextDouble();
        sc.nextLine();

        boolean ok = m.editTransaction(
                date, type, cat, notes,
                val < 0 ? null : BigDecimal.valueOf(val));
        System.out.println(ok ? "✔ Edited." : "✘ Not found.");
    }

    /* ─── Delete ───────────────────────────────────────────────────────── */
    private static void delete(TransactionManager m, Scanner sc) {
        System.out.print("Date to delete: ");
        String date = sc.nextLine();
        System.out.println(m.deleteTransaction(date) ? "✔ Deleted." : "✘ Not found.");
    }

    /* ─── Utility ──────────────────────────────────────────────────────── */
    private static void printList(List<Transaction> list) {
        if (list.isEmpty()) {
            System.out.println("(no transactions)");
            return;
        }
        list.forEach(System.out::println);
    }
}
