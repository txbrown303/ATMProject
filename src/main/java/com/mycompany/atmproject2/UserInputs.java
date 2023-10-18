/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atmproject2;

import java.util.Scanner;

/**
 *
 * @author txbrown
 */
public class UserInputs {

    Scanner inputScanner;
    UserClass currentUser;
    int loginStrikes;
    Boolean isUserLoggedIn;
    Boolean shouldExit;

    public UserInputs(UserClass user) {
        currentUser = user;
        inputScanner = new Scanner(System.in);

        isUserLoggedIn = false;
        shouldExit = false;
        loginStrikes = 0;
    }

    public void ListenForInputs() {
        PinHandler pin = new PinHandler();
        readUserInput(pin);

        while (!shouldExit) {
            listenForCommands();
        }
    }

    public void readUserInput(PinHandler pin) {
        while (true) {
            if (Boolean.FALSE.equals(isUserLoggedIn)) {
                Boolean isPinCorrect = pin.checkPin();

                if (Boolean.FALSE.equals(isPinCorrect)) {

                    loginStrikes++;

                } else if (isPinCorrect) {
                    isUserLoggedIn = true;

                    return;
                }

                if (loginStrikes == 3) {

                    System.out.println("TERMINAL LOCKED FOR 30 SECONDS");

                    try {
                        Thread.sleep(30000);

                        System.out.println("TERMINAL IS UNLOCKED");
                        loginStrikes = 0;
                    } catch (InterruptedException e) {
                        System.out.println(errorMessage()); //not this
                    }

                }

            }
        }
    }

    public void listenForCommands() {

        Boolean keepRunning = true;

        while (keepRunning) {
            System.out.println(menuOutput()); //called from here first

            String input = "";

            while (input.equals("")) {
                input = inputScanner.nextLine();
                input = input.toLowerCase();
            }

            if (input.equals("deposit")) {
                depositCommand();
            } else if (input.equals("withdraw")) {
                withdrawCommand();
            } else if (input.equals("balance")) {
                balanceCommand();
            } else if (input.equals("exit")) {
                exitCommand();
            } else {
                System.out.println(menuOutputError());
                keepRunning = false;
            }

        }
    }

    // defining command functions
    // defing minus command function
    public void withdrawCommand() {

        Boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("If you have accidentally typed withdraw and want to exit, please type 'exit'.\nHow much would you like to withdraw?");
            Scanner scanner = new Scanner(System.in);
            String amountToMinus = scanner.nextLine();

            if ("exit".equals(amountToMinus)) {
                listenForCommands();
                keepRunning = false;
            } else if (!checkForParsableInteger(amountToMinus)) {
                System.out.println(errorMessage());
            }
                else if(amountToMinus.startsWith("+") || (amountToMinus.startsWith("-"))){
                        System.out.println("You must withdraw an integer amount.");
                        }
             else {
                int amount = Integer.parseInt(amountToMinus);
                if (amount > 0) {
                    if (currentUser.checkForPotentialDebt(amount)) {
                        currentUser.decreaseUserBalance(amount);
                        System.out.println("Please take your money from the dispenser. Your new balance is: £" + currentUser.userBalance + ". What would you like to do next?");
                        keepRunning = false;
                    } else if (!currentUser.checkForPotentialDebt(amount)) {
                        System.out.println("User cannot go into debt.");

                    }
                } else if (amount < 0) {
                    System.out.println("You cannot withdraw a negative amount of money.");
                } else if (amount == 0) {
                    System.out.println("You cannot withdraw £0");
                }

            }
        }
    }

    private Boolean checkForParsableInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

// defining balance command function
    public String balanceCommand() {
        System.out.println(userCurrentBalance(currentUser.userBalance));

        Scanner scanner = new Scanner(System.in);

        String exitQuestion = "";

        Boolean isYesOrNo = true;

        while (isYesOrNo) {

            System.out.println(continueQuestion());
            exitQuestion = scanner.nextLine();

            if (exitQuestion.equals("y")) {
                System.out.println("Continuing...\n");
                isYesOrNo = false;

            } else if (exitQuestion.equals("n")) {
                System.out.println("LOGGING OUT. SHUTTING DOWN...........");

                System.exit(0);

            } else {
                System.out.println(errorMessage());

            }

        }

        return exitQuestion;

    }

    public String userCurrentBalance(int userBalance) {

        return "Your current balance is: £" + userBalance + "\n";
    }

    public String menuOutputError() {

        return "\nYou must type 'balance', deposit', 'withdraw' or 'exit'.\n";

    }

    public String continueQuestion() {

        return "Would you like to continue (y) or log out (n)?";
    }

    // defining exit function
    public void exitCommand() {

        System.out.println("Thank you for using the BBC. Logging you out...");
        System.exit(0);
    }

    public String menuOutput() {
        return "\n\nMain Menu\n-----------------------------------------------\nType 'balance' to see your current balance.\n\nType 'deposit' to add to your balance.\n\nType 'withdraw' to withdraw from your balance.\n\nType 'exit' to log out once you are finished.\n-----------------------------------------------\n";

    }

    public String errorMessage() {
        return "Invalid Input";
    }

    // defining deposit function
    public void depositCommand() {

        Boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("How much money would you like to deposit?");

            Scanner scanner = new Scanner(System.in);
            String amountToAdd = scanner.nextLine();
            if (!checkForParsableInteger(amountToAdd)) {
                System.out.println(errorMessage());
            } else if (amountToAdd.startsWith("+") || (amountToAdd.startsWith("-"))){
                System.out.println("You must deposit an integer amount.");
            } else {

                int amount = Integer.parseInt(amountToAdd);
                if (amount > 0) {
                    currentUser.increaseUserBalance(amount);
                    System.out.println("You have successfully deposited £" + amount + " to your balance. Your new balance is: £" + currentUser.userBalance + ". What would you like to do next?\n\n");
                    keepRunning = false;
                } else {
                    System.out.println("You can't deposit a negative amount of money.\n");
                }
            }
        }
    }
}
