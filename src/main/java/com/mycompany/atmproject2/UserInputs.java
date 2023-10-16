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

        while (!shouldExit) {
            readUserInput(pin);
        }
    }

    public void readUserInput(PinHandler pin) {
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
                    System.out.println(errorMessage());
                }

                
            }

            return;
        }

        System.out.println(menuOutput());
        // calling functions

        listenForCommands();
    }

    public void listenForCommands() {
        String input = inputScanner.nextLine();
        input = input.toLowerCase();

        if (input.startsWith("deposit")) {
            depositCommand();

        } else if (input.startsWith("withdraw")) {
            minusCommand();
        } else if (input.startsWith("balance")) {

            balanceCommand();

        } else if (input.startsWith("exit")) {
            exitCommand();
        } else {

            System.out.println("\nYou must type 'balance', deposit', 'withdraw' or 'exit'.\n");
        }
    }

    // defining command functions
    // defing minus command function
    public void minusCommand() {
        System.out.println("How much would you like to withdraw?");
        String amountToMinus = inputScanner.nextLine();

        if (!inputScanner.hasNextInt()) {
            System.out.println(errorMessage());
        } else {
            try {
                int amount = Integer.parseInt(amountToMinus);

                if (currentUser.checkForPotentialDebt(amount)) {
                    currentUser.decreaseUserBalance(amount);
                    System.out.println("Please take your money from the dispenser. Your new balance is: £" + currentUser.userBalance + ". What would you like to do next?");
                } else if (!currentUser.checkForPotentialDebt(amount)) {
                    System.out.println("User cannot go into debt.");

                }

            } catch (NumberFormatException e) {
                System.out.println(errorMessage());
            }
        }
    }

    // defining balance command function
    public String balanceCommand() {
        System.out.println(userCurrentBalance(currentUser.userBalance));

        System.out.println(continueQuestion());

        Scanner scanner = new Scanner(System.in);

        String exitQuestion = scanner.nextLine();

        if (exitQuestion.startsWith("y")) {

        } else if (exitQuestion.startsWith("n")) {
            System.out.println("LOGGING OUT. SHUTTING DOWN...........");
            System.exit(0);
        }
        return exitQuestion;
    }

    public String userCurrentBalance(int userBalance) {

        return "Your current balance is: £" + userBalance + "\n";
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
        return "-----------------------------------------------\nType 'balance' to see your current balance.\n\nType 'deposit' to add to your balance.\n\nType 'withdraw' to withdraw from your balance.\n\nType 'exit' to log out once you are finished.\n-----------------------------------------------\n";

    }
    
    public String errorMessage(){
        return "Invalid Input";
    }

    // defining deposit function
    public void depositCommand() {
        System.out.println("How much money would you like to deposit?");

        if (!inputScanner.hasNextInt()) {
            System.out.println(errorMessage());
        } else {
            Integer amountToAdd = inputScanner.nextInt();
            currentUser.increaseUserBalance(amountToAdd);
            System.out.println("You have successfully deposited £" + amountToAdd + " to your balance. Your new balance is: £" + currentUser.userBalance + ". What would you like to do next?\n\n");
        }
    }
}
