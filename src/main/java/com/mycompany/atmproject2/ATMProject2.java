/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.atmproject2;
/**
 *
 * @author txbrown
 */
public class ATMProject2 {

    public static void main(String[] args) {
        UserClass newUser = new UserClass();
        newUser.userBalance = 0;
        
        System.out.println("Hello and welcome to the BBC! The Big Banking Corporation!");
        
        UserInputs userInputs = new UserInputs(newUser);
        
        userInputs.ListenForInputs();
        
        
    }
}

//commit
//then push
