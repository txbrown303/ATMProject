/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atmproject2;

/**
 *
 * @author txbrown
 */
public class UserClass {
    public int userBalance;
    
    
    public UserClass(){
        userBalance = 0;
    }
    
    public void increaseUserBalance(int increase){
        userBalance = userBalance + increase;
    }
    
    public void decreaseUserBalance(int decrease){
        userBalance = userBalance - decrease;
        
    }
    
    public Boolean checkForPotentialDebt(int decrease){
        int tempBalance = userBalance;
        tempBalance = tempBalance - decrease;
        
        return tempBalance >=0;
    }
}

