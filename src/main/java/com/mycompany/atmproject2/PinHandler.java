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
public class PinHandler {

    String pin;

    public PinHandler() {
        Scanner scanner = new Scanner(System.in);

        Boolean i = true;

        while (i) {

            System.out.println("Please set your pin:");
            String userPin = scanner.nextLine();
            if (userPin.contains("+") || (userPin.contains("-"))) {
                System.out.println(pinSetupChecker());
            } else if (checkForInteger(userPin)) {
                pin = userPin;
                i = false;
            } else {
                System.out.println(pinSetupChecker());

            }

        }
    }

    public String pinSetupChecker() {
        return "Pin must be made of numbers.";
    }

    public Boolean checkForInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean checkPin() {

        System.out.println("Please enter your pin: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equals(pin)) {
            return true;
        } else {
            System.out.println("Pin Incorrect. Please try again.");

            return false;
        }
    }
}
