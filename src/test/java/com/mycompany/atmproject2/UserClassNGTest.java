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
public class UserClassNGTest {
    
    public UserClassNGTest() {
    }

    /**
     * Test of increaseUserBalance method, of class UserClass.
     */
    @Test
    public void testIncreaseUserBalance() {
    }

    /**
     * Test of decreaseUserBalance method, of class UserClass.
     */
    @Test
    public void testDecreaseUserBalance() {
    }

    /**
     * Test of checkForPotentialDebt method, of class UserClass.
     */
    @Test
    public void testCheckForPotentialDebt() {
        
        UserClass user = new UserClass();
        Boolean test = user.checkForPotentialDebt(30);
        System.out.println(test);
        assertNotEquals(test, true);
        
        
    }
    
}
