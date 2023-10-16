/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package com.mycompany.atmproject2;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author txbrown
 */
public class UserInputsNGTest {
    
    public UserInputsNGTest() {
    }


//    @Test
//    public void testUserCurrentBalance() {
//        
//        int[] inputData = {7, 39, 5, -1};
//        String[] expectedData = {"Your balance is £7", "Your balance is £39", "Your balance is £5", "Your balance is £-1"};
//        String[] actualData = new String [4];
//        String missMatch = "";
//        
//        UserClass user = new UserClass();
//        
//        UserInputs userInputs = new UserInputs(user);
//        
//        
//        
//        for (int i = 0; i < inputData.length; i++) {
//            actualData[i] = userInputs.userCurrentBalance(inputData[i]);
//
//        }
//        for (int i = 0; i < inputData.length; i++) {
//
//            if (expectedData[i].equals(actualData[i])) {
//                missMatch += "input" + inputData[i] + "expected data did not match actual data.\n";
//                
//            } 
//            else {
//                missMatch = "expected data matches actual data";
//            }
//        }
//            System.out.println(missMatch);
//    }
    
    @Test
    public void testUserCurrentBalanceFake(){
        String expectedValue = "Your current balance is: £7\n";
        
       UserClass user = new UserClass();
        
        UserInputs userInputs = new UserInputs(user);
        
        String actualValue = userInputs.userCurrentBalance(7);
        
        System.out.println("ACTUAL VALUE: " + actualValue);
        System.out.println("EXOECTEd VALUE: " + expectedValue);
        
        assertEquals(actualValue, expectedValue);
    }
    
    @Test
    public void testUserCurrentBalanceIsFalse(){
        String expectedValue = "Invalid Input";
        
       UserClass user = new UserClass();
        
        UserInputs userInputs = new UserInputs(user);
        
        String actualValue = userInputs.userCurrentBalance(-1);
        
        System.out.println("ACTUAL VALUE: " + actualValue);
        System.out.println("EXOECTEd VALUE: " + expectedValue);
        
        assertEquals(actualValue, expectedValue);
    }
    
}
