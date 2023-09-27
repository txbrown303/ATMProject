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
public class EnterPin {
    public String pin;
    
    public EnterPin(String newPin){
        pin = newPin; 
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
                return false;
            }
    }   
}




