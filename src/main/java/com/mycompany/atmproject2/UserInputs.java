/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atmproject2;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    
    public UserInputs(UserClass user){
        currentUser = user;
        inputScanner = new Scanner(System.in);
        
        isUserLoggedIn = false;
        shouldExit = false;
        loginStrikes = 0;
    }
    
    public void readUserInputs(){
        PinHandler pin = new PinHandler("1234");
        
        if (isUserLoggedIn == false){
            Boolean isPinCorrect = pin.CheckPin();
                    
            if (isPinCorrect == false){
                loginStrikes++;
                
            }
            else if (isPinCorrect == true)
            {
                isUserLoggedIn = true;
                return;
            }
            
            if (loginStrikes == 3){
                
                System.out.println("TERMINAL LOCKED FOR 30 SECONDS");
                
                try {
                    Thread.sleep(30000);
                    
                    System.out.println("TERMINAL IS UNLOCKED");
                    loginStrikes = 0;
                } catch (InterruptedException ex) {
                    System.out.println("ERROR OCCCCCCCVCUHREOUEHRAWHB");
                }
                
                return;
            }
            
            return;
        }
        
                       
        System.out.println("-----------------------------------------------\nType '/balance' to see your current balance.\n\nType '/add' to add to your balance.\n\nType '/minus' to withdraw from your balance.\n\nType '/exit' to log out once you are finished.\n-----------------------------------------------\n");
        
        String input = inputScanner.nextLine();
        
        if(input.startsWith("/add") || (input.startsWith("/ADD"))){
            System.out.println("How much money would you like to deposit?");
            String amountToAdd = inputScanner.nextLine();
            
                try {
                    int amount = Integer.parseInt(amountToAdd);
                    currentUser.increaseUserBalance(amount);
                    System.out.println("You have successfully added £" + amountToAdd +" to your balance. Your new balance is: £" + currentUser.userBalance+". What would you like to do next?\n\n");
                }
                catch(Exception e) {
                    System.out.println("Invalid input");
                }
                
            }
        
        else if (input.startsWith("/minus") || (input.startsWith("/MINUS"))) {
            MinusCommand();
        }
        else if (input.startsWith("/balance") || (input.startsWith("/Balance") || (input.startsWith("/BALANCE")))){
            System.out.println("Your current balance is: £" +currentUser.userBalance);
            
            System.out.println("Would you like to continue (y) or log out (n)?");
            String exitQuestion = inputScanner.nextLine();
            
            if(exitQuestion.startsWith("y")){
                
            }
            else if (exitQuestion.startsWith("n")){
                System.out.println("LOGGING OUT. SHUTTING DOWN...........");
                System.exit(0);
            }
            
           
        }
        else if (input.startsWith("/exit") || (input.startsWith("/EXIT"))){
            System.out.println("Thank you for using the BBC. Logging you out...");
            System.exit(0);
        }
        else{
            System.out.println("You must type '/balance', /add', '/minus' or '/exit'.");
        }
    } 
        
    
    public void MinusCommand(){
        System.out.println("How much would you like to withdraw?");
            String amountToMinus = inputScanner.nextLine();
            try{
            int amount = Integer.parseInt(amountToMinus);
            
            if(currentUser.checkForPotentialDebt(amount)){
                currentUser.decreaseUserBalance(amount);
                System.out.println("Please take your money from the dispenser. Your new balance is: £" + currentUser.userBalance + ". What would you like to do next?");
            }
            else if (!currentUser.checkForPotentialDebt(amount)){
                System.out.println("User cannot go into debt.");
            }
            }
            catch (Exception e) {
                System.out.println("Invalid Input");
            }
    }
    
    public void ListenForInputs(){
        while (!shouldExit) {
            readUserInputs();
        }
    }
    
}
