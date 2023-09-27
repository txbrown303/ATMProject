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
        EnterPin pin = new EnterPin("1234");
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
                shouldExit = true;
                System.out.println("ACCOUNT LOCKED.");
                
                return;
            }
            
            return;
        }
        
                       
        System.out.println("-----------------------------------------------\nType '/add' to add to your balance.\n\nType '/minus' to withdraw from your balance.\n\nType '/exit' to log out once you are finished.\n-----------------------------------------------\n");
        
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
        else if (input.startsWith("/exit")){
            System.out.println("Thank you for using the BBC. Logging you out...");
            System.exit(0);
        }
        else{
            System.out.println("You must type '/add', '/minus' or '/exit'.");
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
