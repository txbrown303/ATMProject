/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atmproject2;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author txbrown
 */
public class PinHandler {
    public String pin;
    public String pin2;
    
    public PinHandler(String newPin){
        pin = newPin; 
        pin2 = newPin;
    }
    
    public Boolean CheckPin (){
        
        System.out.println("Please enter your pin: ");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            
            if(userInput.equals(pin)){
                return true;
            }
            else {
                System.out.println("Pin Incorrect. Please try again.");
                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                return false;
            }
    }
}




