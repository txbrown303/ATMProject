/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atmproject2;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.atmproject2.Balance;
import java.io.IOException;

/**
 *
 * @author txbrown
 */
public class UserInputs {

    Scanner inputScanner;
    UserClass currentUser;
    ATMProject2 userBalance;
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

    public void readUserInputs() {
        PinHandler pin = new PinHandler("1234");

        if (isUserLoggedIn == false) {
            Boolean isPinCorrect = pin.CheckPin();

            if (isPinCorrect == false) {
                loginStrikes++;

            } else if (isPinCorrect == true) {
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
                    System.out.println("ERROR OCCURED");
                }

                return;
            }

            return;
        }

        System.out.println("-----------------------------------------------\nType '/balance' to see your current balance.\n\nType '/deposit' to add to your balance.\n\nType '/withdraw' to withdraw from your balance.\n\nType '/exit' to log out once you are finished.\n-----------------------------------------------\n");

        String input = inputScanner.nextLine();
        
        // calling functions
        if (input.startsWith("/deposit") || (input.startsWith("/DEPOSIT"))) {
            depositCommand();

        } else if (input.startsWith("/withdraw") || (input.startsWith("/WITHDRAW"))) {
            minusCommand();
        } else if (input.startsWith("/balance") || (input.startsWith("/Balance") || (input.startsWith("/BALANCE")))) {

            balanceCommand();

        } else if (input.startsWith("/exit") || (input.startsWith("/EXIT"))) {
            exitCommand();
        } else {
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            
            System.out.println("\nYou must type '/balance', /deposit', '/withdraw' or '/exit'.\n");
            
            
            
        }
    }
    // defining command functions
    // defing minus command function
    public void minusCommand() {
        System.out.println("How much would you like to withdraw?");
        String amountToMinus = inputScanner.nextLine();
        if (amountToMinus.startsWith("-")){
                        System.out.println("Invalid Input");
                        }
        else{
        try {
            int amount = Integer.parseInt(amountToMinus);

            if (currentUser.checkForPotentialDebt(amount)) {
                currentUser.decreaseUserBalance(amount);
                System.out.println("Please take your money from the dispenser. Your new balance is: £" + currentUser.userBalance + ". What would you like to do next?");
            }
            else if (!currentUser.checkForPotentialDebt(amount)) {
                System.out.println("User cannot go into debt.");
              
            }
             
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
        }
    }
    }
    // defining balance command function
    public void balanceCommand() {

        System.out.println("Your current balance is: £" + currentUser.userBalance);

        System.out.println("Would you like to continue (y) or log out (n)?");
        String exitQuestion = inputScanner.nextLine();

        if (exitQuestion.startsWith("y")) {}
        

         else if (exitQuestion.startsWith("n")) {
            System.out.println("LOGGING OUT. SHUTTING DOWN...........");
            System.exit(0);
        }
    }
    // defining exit function
    public void exitCommand() {
        System.out.println("Thank you for using the BBC. Logging you out...");
        System.exit(0);
    }
    // defining deposit function
    public void depositCommand(){
        System.out.println("How much money would you like to deposit?");
        
            String amountToAdd = inputScanner.nextLine();
            if (amountToAdd.startsWith("-")){
                        System.out.println("Invalid Input");
                        }
            else{
            
            try {
                int amount = Integer.parseInt(amountToAdd);
                currentUser.increaseUserBalance(amount);
                System.out.println("You have successfully deposited £" + amountToAdd + " to your balance. Your new balance is: £" + currentUser.userBalance + ". What would you like to do next?\n\n");
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
            }
    }

    public void ListenForInputs() {
        while (!shouldExit) {
            readUserInputs();
        }
    }

}

































