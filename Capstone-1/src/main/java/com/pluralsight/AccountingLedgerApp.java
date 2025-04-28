package com.pluralsight;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class AccountingLedgerApp {
    public static Scanner input = new Scanner(System.in);
    public static boolean running = true;
    public static void main(String[] args) {

        while(running){
            showMainMenu();
            String userSelectedOption = input.nextLine();

            switch (userSelectedOption) {
                case "D":
                    addingDeposit();
                    break;
                case "P":
                    makingPayment();
                    break;
                case "L":
                    ledgerPrompter();
                    break;
                case "X":
                    System.out.println("goodbye");
                    running =false;
                    break;
                default:
        }
        }
    }
    public static void addingDeposit(){
        Transactions newDeposit = new Transactions("","","",0.00);
        savingDepositInfo();

    }

    private static void savingDepositInfo() {
        try {
            FileWriter fileWriter= new FileWriter("Capstone-1\\DataFiles\\walter_white_purchase_history.csv");
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            System.out.println("please provide debit information");
            String debitForDeposit = input.nextLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makingPayment() {

        Transactions newPayment = new Transactions("","","",0.00);
        savingPaymentInfo();
    }

    private static void savingPaymentInfo() {
        try {
            FileWriter fileWriter= new FileWriter("Capstone-1\\DataFiles\\walter_white_purchase_history.csv");
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            System.out.println("please provide debit information");
            String debitForPayment = input.nextLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void searchForVendor() {

        ArrayList<Transactions> transactions = readingTransaction();

        try {
            FileReader fileReader= new FileReader("Capstone-1\\DataFiles\\walter_white_purchase_history.csv");
            BufferedReader bufreader = new BufferedReader(fileReader);

            bufreader.readLine();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void  viewLedgerOptions(){
        System.out.println("Ledger Menu");
        System.out.println("___________");
        System.out.println("A- All");
        System.out.println("D- Deposits");
        System.out.println("P- Payment");
        System.out.println("R- Reports");
        System.out.println("H- Home");

        promptReturnToMainMenu();
    }

    public static void ledgerPrompter (){
        while (running){
            viewLedgerOptions();
            String selectedLedgerOption = input.nextLine();

            switch (selectedLedgerOption){
                case"A":
                    showAllTransactions();
                    break;
                case"D":
                    addingDeposit();
                    break;
                case"P":
                    makingPayment();
                    break;
                case"R":reportPrompter();
                    break;
                case"H":
                    System.out.println("Thank you");
                    running = false;
                default:
                    System.out.println("invalid option");
            }
        }
    }

    public static void showAllTransactions() {
        System.out.println("all transactions");
        System.out.println("_________________");

        ArrayList<Transactions> transactions = readingTransaction();
        printingDisplayAndTransactions(transactions);

        promptReturnToMainMenu();
    }

    private static void printingDisplayAndTransactions(ArrayList<Transactions>transactions) {
        for (Transactions transaction: transactions)
            System.out.println(transaction.display());
    }

    private static ArrayList<Transactions> readingTransaction() {
        ArrayList<Transactions> transactions = new ArrayList<>();

        try {
            FileReader fileReader= new FileReader("Capstone-1\\DataFiles\\walter_white_purchase_history.csv");
            BufferedReader bufreader = new BufferedReader(fileReader);

            bufreader.readLine();

            String line;
            while ((line = bufreader.readLine()) != null) {
                String[] tokens = line.split("\\|");

                LocalDateTime localDateTime = LocalDateTime.parse(tokens[0]);
                String description = tokens[1];
                String vendor = tokens[2];
                double price = Double.parseDouble(tokens[3]);
                Transactions transaction = new Transactions(localDateTime, description , vendor, price);
                transactions.add(transaction);
            }
            bufreader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    public static void reportsMenuOptions() {
        System.out.println("1- Month to date");
        System.out.println("2- Previous month");
        System.out.println("3- Year to date");
        System.out.println("4- Previous year");
        System.out.println("5- Search for vendor");
        System.out.println("0- Back to last menu");

        promptReturnToMainMenu();
    }

    public static void reportPrompter (){
        while (running){
            reportsMenuOptions();
            int selectedReportsOptions = input.nextInt();
            input.nextLine();

            switch(selectedReportsOptions){
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
                    previousYear();
                    break;
                case 5:
                    searchForVendor();
                    break;
                case 0:
                    running =false;
                    break;
                default:
                    System.out.println("invalid option.");
            }
        }
    }

    public static void reportsMonthToDate() {
        System.out.println("month to today");

        ArrayList<Transactions> transactions = readingTransaction();
        printingDisplayAndTransactions(transactions);

        ArrayList<Transactions> transactionsOverThisMonth = new ArrayList<>();

        LocalDateTime startOfMonth = LocalDateTime.now().minusMonths(1);
        LocalDateTime today = LocalDateTime.now();
        for (Transactions transaction : transactions) {
            if (transaction.getLocalDateTime().isAfter(startOfMonth) && transaction.getLocalDateTime().isBefore(today)) {
                transactionsOverThisMonth.add(transaction);
            }

        }

    }

    public static void reportsPreviousMonth() {
        System.out.println("last month");

        ArrayList<Transactions> transactions = readingTransaction();
        printingDisplayAndTransactions(transactions);

        ArrayList<Transactions> transactionsOverLastMonth = new ArrayList<>();

        LocalDateTime startOfPreviousMonth = LocalDateTime.now().minusMonths(2);
        LocalDateTime endOfPreviousMonth = LocalDateTime.now().plusMonths(2);
        for (Transactions transaction : transactions) {
            if (transaction.getLocalDateTime().isAfter(startOfPreviousMonth)
                    && transaction.getLocalDateTime().isBefore(endOfPreviousMonth)){
                transactionsOverLastMonth.add(transaction);
            }
        }
    }

    public static void reportsYearToDate() {
        ArrayList<Transactions> transactions = readingTransaction();
        printingDisplayAndTransactions(transactions);

        ArrayList<Transactions> transactionsOverThisYear = new ArrayList<>();

        LocalDateTime startOfYear = LocalDateTime.now().minusYears(1);
        LocalDateTime today = LocalDateTime.now();
        for (Transactions transaction : transactions) {
            if (transaction.getLocalDateTime().isAfter(startOfYear) && transaction.getLocalDateTime().isBefore(today)) {
                transactionsOverThisYear.add(transaction);
        }
    }
    }

    public static void previousYear() {
        System.out.println("last year");

        ArrayList<Transactions> transactions = readingTransaction();
        printingDisplayAndTransactions(transactions);

        try {
            FileReader fileReader= new FileReader("Capstone-1\\DataFiles\\walter_white_purchase_history.csv");
            BufferedReader bufreader = new BufferedReader(fileReader);

            bufreader.readLine();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void showMainMenu() {
        System.out.println("Main Menu");
        System.out.println("___________");
        System.out.println("D- Add Deposit");
        System.out.println("P- Make payment");
        System.out.println("L- View ledger");
        System.out.println("X- Exit");

    }
    private static void promptReturnToMainMenu() {
        System.out.println("\nPress Enter to return to the main menu...");
        input.nextLine();
    }
    }


    //use arraylist, make objects, then filter array list for specific object needed.
//for (Product p : products){
//        System.out.printf("ID: %s, Item: %s, Price: $%.2f%n", p.getItemId(), p.getItem(), p.getPrice());
