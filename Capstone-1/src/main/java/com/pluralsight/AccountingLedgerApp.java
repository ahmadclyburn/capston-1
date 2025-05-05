package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountingLedgerApp {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMainMenu();
            String userSelectedOption = input.nextLine();

            switch (userSelectedOption.toUpperCase()) {
                case "D":
                    addingDeposit();
                    break;
                case "P":
                    savingPaymentInfo();
                    break;
                case "L":
                    ledgerPrompter();
                    break;
                case "X":
                    System.out.println("Goodbye");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid menu option.try again");
            }
        }
    }

    public static void addingDeposit() {
        System.out.println("\n---Adding deposit---");
        System.out.println("---------------------");

        System.out.print("\nEnter description: ");
        String description = input.nextLine();

        System.out.print("\nEnter vendor: ");
        String vendor = input.nextLine();

        System.out.print("\nEnter amount: ");
        double price = input.nextDouble();

        System.out.println("thank you. returning to menu" );
        Transaction transaction = new Transaction(LocalDateTime.now(), description, vendor, price);
        savingDepositInfo(transaction);
        promptReturnToMainMenu();
    }

    private static void savingDepositInfo(Transaction transaction) {
        try {
            FileWriter fileWriter = new FileWriter("C:\\pluralsight\\capston-1\\Capstone-1\\DataFiles\\walter_white_transactions_2024_2025_with_header.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            bufWriter.write("\n");
            bufWriter.write(transaction.toString());
            bufWriter.close();

        } catch (IOException e) {
            System.out.println("****invalid file****");
        }
    }

    public static Transaction.Payment gettingPaymentInfo() {
        System.out.println("---Making a Payment---");
        System.out.println("----------------------");
        System.out.println("please provide debit information");
        String debitForPayment = input.nextLine();

        System.out.println("what is  the payment amount");
            double paymentAmount = input.nextDouble();
            input.nextLine();

        Transaction.Payment payment = new Transaction.Payment();
        payment.setDebitInfo(debitForPayment);
        payment.setPaymentAmount(paymentAmount);

        return payment;
    }

    public static void savingPaymentInfo() {
        Transaction.Payment payment = gettingPaymentInfo();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|hh:mm:ss");

            FileWriter fileWriter = new FileWriter("C:\\pluralsight\\capston-1\\Capstone-1\\DataFiles\\walter_white_transactions_2024_2025_with_header.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            bufWriter.write("\n");
            bufWriter.write(String.format("%s|New Payment Using Debit Card Number|%s|-%.2f",
                    LocalDateTime.now().format(formatter), payment.getDebitInfo(), payment.getPaymentAmount()));
            bufWriter.close();

        } catch (IOException e) {
            System.out.println("*****file not found*****");
        }catch (NumberFormatException e) {
            System.out.println("*****Invalid number format in file: ****");
        }
        System.out.println("--Payment Has Been Processed. Thank You.--");
        System.out.println("Returning to menu");
    }

    public static void viewLedgerOptions() {
        System.out.println("\n\n\n\n");
        System.out.println("-----------");
        System.out.println("Ledger Menu");
        System.out.println("___________");
        System.out.println("A- Vew All Transactions\n");
        System.out.println("D- View All Deposits\n");
        System.out.println("P- View All Payment\n");
        System.out.println("R- View Reports\n");
        System.out.println("H- Home");
    }

    public static void ledgerPrompter() {
        boolean running = true;
        while (running) {
            viewLedgerOptions();
            String selectedLedgerOption = input.nextLine();

            switch (selectedLedgerOption.toUpperCase()) {
                case "A":
                    showAllTransactions();
                    break;
                case "D":
                    showingDeposits();
                    break;
                case "P":
                    showingPayments();
                    break;
                case "R":
                    reportPrompter();
                    break;
                case "H":
                    running = false;
                    break;
                default:
                    System.out.println("*****invalid option****");
            }
        }
    }

    public static void showAllTransactions() {
        System.out.println(" ");
        System.out.println("----------------");
        System.out.println("Viewing All transactions");
        System.out.println("_________________");
        System.out.println();

        ArrayList<Transaction> transactions = readTransactionsFromFile();
        displayTransactions(transactions);

        promptReturnToMainMenu();
    }
    public static void showingDeposits() {
        ArrayList<Transaction> transactions = readTransactionsFromFile();
        ArrayList<Transaction> deposits = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getPrice() > 0) {
                deposits.add(transaction);
            }
        }
        displayTransactions(deposits);promptReturnToMainMenu();
    }
    public static void showingPayments() {
        ArrayList<Transaction> transactions = readTransactionsFromFile();
        ArrayList<Transaction> payment = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getPrice() < 0) {
                payment.add(transaction);
            }
        }
        displayTransactions(payment);promptReturnToMainMenu();
    }

    public static void reportsMenuOptions() {
        System.out.println("\n\n\n\n");
        System.out.println("---------------");
        System.out.println("Reports Options");
        System.out.println("---------------");
        System.out.println("1- Month to date\n");
        System.out.println("2- Previous month\n");
        System.out.println("3- Year to date\n");
        System.out.println("4- Previous year\n");
        System.out.println("5- Search for vendor\n");
        System.out.println("0- Back to last menu");
    }

    public static void reportPrompter() {
        boolean running = true;

        while (running) {
            reportsMenuOptions();
            int selectedReportsOptions = input.nextInt();

            switch (selectedReportsOptions) {
                case 1:
                    reportsMonthToDate();
                    break;
                case 2:
                    reportsPreviousMonth();
                    break;
                case 3:
                    reportsYearToDate();
                    break;
                case 4:
                    reportsPreviousYear();
                    break;
                case 5:
                    searchForVendor();
                    break;
                case 0:
                    ledgerPrompter();
                    running =false;
                    break;

                default:
                    System.out.println("****invalid option****");
            }
        }
    }

    private static ArrayList<Transaction> readTransactionsFromFile() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("C:\\pluralsight\\capston-1\\Capstone-1\\DataFiles\\walter_white_transactions_2024_2025_with_header.csv");
            BufferedReader bufreader = new BufferedReader(fileReader);

            bufreader.readLine();

            String line;
            while ((line = bufreader.readLine()) != null) {
                String[] tokens = line.split("\\|");

                LocalDateTime localDateTime = LocalDateTime.parse(tokens[0] + "T" + tokens[1]);
                String description = tokens[2];
                String vendor = tokens[3];
                double price = Double.parseDouble(tokens[4]);
                Transaction transaction = new Transaction(localDateTime, description, vendor, price);
                transactions.add(transaction);
            }
            bufreader.close();

        } catch (FileNotFoundException e) {
            System.out.println("****error reading code****");
        } catch (IOException e) {
            System.out.println("****invalid number format in file****");
        }
        return transactions;
    }

    private static void displayTransactions(ArrayList<Transaction> transactions) {
        System.out.println("All Transactions Found");
        for (Transaction transaction : transactions) {
            System.out.println("-----------------------");
            System.out.println(transaction.toString());
        }
    }

    public static ArrayList<Transaction> filterTransactionsMonthToDate(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> transactionsOverThisMonth = new ArrayList<>();

        LocalDateTime startOfMonth = LocalDateTime.now().minusMonths(1);
        LocalDateTime today = LocalDateTime.now();

        for (Transaction transaction : transactions) {
            if (transaction.getLocalDateTime().isAfter(startOfMonth)
                    && transaction.getLocalDateTime().isBefore(today)) {
                transactionsOverThisMonth.add(transaction);
            }

        }
        return transactionsOverThisMonth;

    }

    public static void reportsMonthToDate() {
        System.out.println(" ");
        System.out.println("---------------");
        System.out.println("This Month to Today");

        ArrayList<Transaction> transactions = readTransactionsFromFile();
        ArrayList<Transaction> transactionsOverThisMonth = filterTransactionsMonthToDate(transactions);
        displayTransactions(transactionsOverThisMonth);

    }

    public static ArrayList<Transaction> filterTransactionsPreviousMonth(ArrayList<com.pluralsight.Transaction> transactions) {
        ArrayList<Transaction> transactionsOverLastMonth = new ArrayList<>();

        LocalDateTime startOfPreviousMonth = LocalDateTime.now().minusMonths(2);
        LocalDateTime endOfPreviousMonth = LocalDateTime.now().minusMonths(1);

        for (Transaction transaction : transactions) {
            if (transaction.getLocalDateTime().isAfter(startOfPreviousMonth)
                    && transaction.getLocalDateTime().isBefore(endOfPreviousMonth)) {
                transactionsOverLastMonth.add(transaction);
            }
        }
        return transactionsOverLastMonth;
    }

    public static void reportsPreviousMonth() {
        System.out.println(" ");
        System.out.println("---------------");
        System.out.println("Last Month");

        ArrayList<Transaction> transactions = readTransactionsFromFile();
        ArrayList<Transaction> transactionsOverPreviousMonth = filterTransactionsPreviousMonth(transactions);
        displayTransactions(transactionsOverPreviousMonth);
    }

    public static ArrayList<Transaction> filterTransactionsYearToDate(ArrayList<com.pluralsight.Transaction> transactions) {
        ArrayList<Transaction> transactionsOverThisYear = new ArrayList<>();

        LocalDateTime startOfYear = LocalDateTime.now().minusYears(1);
        LocalDateTime today = LocalDateTime.now();

        for (Transaction transaction : transactions) {
            if (transaction.getLocalDateTime().isAfter(startOfYear) && transaction.getLocalDateTime().isBefore(today)) {
                transactionsOverThisYear.add(transaction);
            }
        }
        return transactionsOverThisYear;
    }

    public static void reportsYearToDate() {
        System.out.println(" ");
        System.out.println("---------------");
        System.out.println("This Year to Today");

        ArrayList<Transaction> transactions = readTransactionsFromFile();
        ArrayList<Transaction> transactionsYearToDate = filterTransactionsYearToDate(transactions);
        displayTransactions(transactionsYearToDate);
    }

    public static ArrayList<Transaction> filterTransactionsPreviousYear(ArrayList<com.pluralsight.Transaction> transactions) {
        ArrayList<Transaction> transactionsOverPreviousYear = new ArrayList<>();

        LocalDateTime startOfMonth = LocalDateTime.now().minusYears(2);
        LocalDateTime today = LocalDateTime.now().minusYears(1);

        for (Transaction transaction : transactions) {
            if (transaction.getLocalDateTime().isAfter(startOfMonth)
                    && transaction.getLocalDateTime().isBefore(today)) {
                transactionsOverPreviousYear.add(transaction);
            }

        }
        return transactionsOverPreviousYear;
    }

    public static void reportsPreviousYear() {
        System.out.println(" ");
        System.out.println("---------------");
        System.out.println("Last Years Reports");

        ArrayList<Transaction> transactions = readTransactionsFromFile();
        ArrayList<Transaction> transactionsOverPreviousYear = filterTransactionsPreviousYear(transactions);
        displayTransactions(transactionsOverPreviousYear);
    }

    public static void searchForVendor() {
        System.out.println(" ");
        System.out.println("what vendor do you want to find");
        input.nextLine();
        String vendorUserWants = input.nextLine().trim().toLowerCase();


        try {BufferedReader bufreader = new BufferedReader(new FileReader("C:\\pluralsight\\capston-1\\Capstone-1\\DataFiles\\walter_white_transactions_2024_2025_with_header.csv"));
            bufreader.readLine();
            String line;

            while ((line = bufreader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length >=5) {
                    String description = parts[2].toLowerCase();
                    String vendor = parts[3].toLowerCase();

                    if (description.contains(vendorUserWants) || vendor.contains(vendorUserWants)) {
                        System.out.printf("\nAll vendor occurrences\n--------------\nDate: %s %s | Description: %s | Vendor: %s | Amount: %s%n",
                                parts[0], parts[1], parts[2], parts[3], parts[4]);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showMainMenu() {
        System.out.println(" ");
        System.out.println("Welcome to Accounting Leger");
        System.out.println("---Main Menu---");
        System.out.println("_______________");
        System.out.println("D- Add Deposit\n");
        System.out.println("P- Make payment\n");
        System.out.println("L- View ledger\n");
        System.out.println("X- Exit");

    }

    private static void promptReturnToMainMenu() {
        System.out.println("\nPress Enter to go back");
        input.nextLine();
    }
}