package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class AccountingLedgerApp {
    public static Scanner input = new Scanner(System.in);
    public static boolean running = true;
    public static FileReader fileReader= new FileReader(csv);
    public static FileWriter fileWriter= new FileWriter(csv);
    public static void main(String[] args) {

        while(running){
            showMainMenu();
            String userSelectedOption = input.nextLine();
//            input.nextLine();

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
        fileWriter(csv);
        //        BufferedWriter bufWriter = new BufferedWriter(fileWriter);

    }

    public static void makingPayment() {
        fileWriter(csv);
//        BufferedWriter bufWriter = new BufferedWriter(fileWriter);

    }


    public static void searchForVendor() {
        fileReader(csv);
//        BufferedReader bufReader = new BufferedReader(vendorReader);

    }

    public static void  viewLedgerOptions(){
        System.out.println("Ledger Menu");
        System.out.println("___________");
        System.out.println("A- All");
        System.out.println("D- Deposits");
        System.out.println("P- Payment");
        System.out.println("R- Reports");
        System.out.println("H- Home");
    }

    public static void ledgerPrompter (){
        while (running){
            viewLedgerOptions();
            String selectedLedgerOption = input.nextLine();
//            input.nextLine();

            switch (selectedLedgerOption){
                case"A":
                    showAllInfo();
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

    public static void showAllInfo() {
        System.out.println("all info");
        fileReader(csv);
    }

    public static void reportsMenuOptions() {
        System.out.println("1- Month to date");
        System.out.println("2- Previous month");
        System.out.println("3- Year to date");
        System.out.println("4- Previous year");
        System.out.println("5- Search for vendor");
        System.out.println("0- Back to last menu");
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
        fileReader(csv);

    }

    public static void reportsPreviousMonth() {
        System.out.println("last month");
        fileReader(csv);
    }

    public static void reportsYearToDate() {
        System.out.println("start of year to now");
        fileReader(csv);
    }

    public static void previousYear() {
        System.out.println("last year");
        fileReader(csv);
    }
    public static void showMainMenu() {
        System.out.println("Main Menu");
        System.out.println("___________");
        System.out.println("D- Add Deposit");
        System.out.println("P- Make payment");
        System.out.println("L- View ledger");
        System.out.println("X- Exit");
    }
    private static void promptReturnToMenu() {
        System.out.println("\nPress Enter to return to the main menu...");
        input.nextLine();
    }
    }