/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//Name: Mila Mpengesi
 //* Student Number:  st10485707
 //* POE: Part 2
 //* Date: 26 May 2025
 //* Description: This application handles messages by saving, sending, and showing them through dialog boxes.
//We appreciate your support and look forward to collaborating with you in the future.


package com.mycompany.chatapp;

import javax.swing.JOptionPane;

/**
 * ChatSystem handles registration and login logic.
 * This version uses JOptionPane for GUI input/output (Part 2).
 */
public class ChatSystem {

    public static User user;

    // Registration and login using dialogs
    
    public static boolean startChat() {
        // --- Registration Process ---
        // Ask user for username and validate format requirements (underscore and length)
        String username = JOptionPane.showInputDialog(null, "Register - Enter Username (must include an underscore and be <= 5 characters):");
        if (username == null || !Validator.checkUserName(username.trim())) {
            JOptionPane.showMessageDialog(null, "Invalid Username.\nMust include an underscore and be no more than 5 characters.");
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Username successfully captured.");
        }

        // Ask for password with complexity rules and validate
        String password = JOptionPane.showInputDialog(null, "Register - Enter Password (must be complex: 8+ chars, upper case, digit, special char):");
        if (password == null || !Validator.checkPasswordComplexity(password.trim())) {
            JOptionPane.showMessageDialog(null, "Invalid Password.\nMust include uppercase, digit, special character and be at least 8 characters long.");
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Password successfully captured.");
        }

        // Ask for South African phone number, validate format +27XXXXXXXXX
        String phone = JOptionPane.showInputDialog(null, "Register - Enter Cellphone Number (Format: +27XXXXXXXXX):");
        if (phone == null || !Validator.checkCellPhoneNumber(phone.trim())) {
            JOptionPane.showMessageDialog(null, "Invalid Phone Number.\nMust start with +27 and be exactly 12 characters.");
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Cellphone number successfully added.");
        }

        // Collect first name ensuring it is not empty
        String firstName = JOptionPane.showInputDialog(null, "Register - Enter First Name:");
        if (firstName == null || firstName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "First name cannot be empty.");
            return false;
        }

        // Collect last name ensuring it is not empty
        String lastName = JOptionPane.showInputDialog(null, "Register - Enter Last Name:");
        if (lastName == null || lastName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Last name cannot be empty.");
            return false;
        }

        // Store all valid registration data into a new User object
        user = new User(username.trim(), password.trim(), phone.trim(), firstName.trim(), lastName.trim());

        // Notify user that registration is complete
        JOptionPane.showMessageDialog(null, "Registration Complete!");

        // --- Login Process ---
        // Prompt user for login username
        String loginUser = JOptionPane.showInputDialog(null, "Login - Enter Username:");
        if (loginUser == null) return false;

        // Prompt user for login password
        String loginPass = JOptionPane.showInputDialog(null, "Login - Enter Password:");
        if (loginPass == null) return false;

        // Check login credentials against registered user details
        boolean success = user.login(loginUser.trim(), loginPass.trim());

        // Display result message based on login success or failure
        JOptionPane.showMessageDialog(null, user.returnLoginStatus(success));

        return success;
    }

}
