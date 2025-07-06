package com.chirag.financetracker;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Console UI for the Personal Finance Tracker.
 * Relies entirely on TransactionManager for business logic.
 */
public class FinanceTrackerApp {

    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    Main Menu
                    1. Add Transaction
                    2. View (Filter/Sort/Search)
                    3. Edit Transaction
                    4. Delete Transaction
                    5. Summary
                    6. Exit""");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {
                case 1 -> add(manager, scanner);
                case 2 -> viewMenu(manager, scanner);
                case 3 -> edit(manager, scanner);
                case 4 -> delete(manager, scanner);
                case 5 -> summarize(manager, scanner);
                case 6 -> {
                    System.out.println("Good‑bye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }


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

    private static void viewMenu(TransactionManager m, Scanner sc) {
        print(m.getAllTransactions());
        while (true) {
            System.out.println("""
                    View Menu
                    1. Filter
                    2. Sort
                    3. Search Notes
                    4. Back""");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> filterMenu(m, sc);
                case 2 -> sortMenu(m, sc);
                case 3 -> {
                    System.out.print("Keyword: ");
                    print(m.searchByNotes(sc.nextLine()));
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void filterMenu(TransactionManager m, Scanner sc) {
        System.out.println("Filter 1.Type 2.Category 3.Date Range 4.Back");
        System.out.print("Choose: ");
        int ch = sc.nextInt();
        sc.nextLine();

        switch (ch) {
            case 1 -> {
                System.out.print("Type: ");
                print(m.filterByType(sc.nextLine()));
            }
            case 2 -> {
                System.out.print("Category: ");
                print(m.filterByCategory(sc.nextLine()));
            }
            case 3 -> {
                System.out.print("Start date: ");
                String s = sc.nextLine();
                System.out.print("End date: ");
                String e = sc.nextLine();
                print(m.filterByDateRange(s, e));
            }
            case 4 -> {
                return;
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private static void sortMenu(TransactionManager m, Scanner sc) {
        System.out.println("Sort 1.Date 2.Amount 3.Back");
        System.out.print("Choose: ");
        int ch = sc.nextInt();
        sc.nextLine();

        switch (ch) {
            case 1 -> print(m.sortByDateDesc());
            case 2 -> print(m.sortByAmountDesc());
            case 3 -> {
                return;
            }
            default -> System.out.println("Invalid option.");
        }
    }


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
        double v = sc.nextDouble();
        sc.nextLine();

        boolean ok = m.editTransaction(date, type, cat, notes,
                v < 0 ? null : BigDecimal.valueOf(v));
        System.out.println(ok ? "✔ Edited." : "✘ Not found.");
    }


    private static void delete(TransactionManager m, Scanner sc) {
        System.out.print("Date to delete: ");
        String date = sc.nextLine();
        System.out.println(m.deleteTransaction(date) ? "✔ Deleted." : "✘ Not found.");
    }


    private static void summarize(TransactionManager m, Scanner sc) {
        System.out.print("Summary: 1=7days 2=30days : ");
        int ch = sc.nextInt();
        sc.nextLine();
        m.printSummary(ch == 1 ? 7 : 30);
    }


    private static void print(List<Transaction> list) {
        if (list.isEmpty()) System.out.println("(no transactions)");
        else list.forEach(System.out::println);
    }
}
