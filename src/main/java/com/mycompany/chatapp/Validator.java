/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import javax.swing.JOptionPane;
/**
 *
 * @author mila
 */
/**
 * Validator class for user input checks such as username, password, and phone number.
 */
public class Validator {
 /**
     * Checks if the username is valid.
     * Valid username must contain an underscore (_) and be 5 characters or less.
     *
     * @param username The username string to validate
     * @return true if username meets criteria, false otherwise
     */
    public static boolean checkUserName(String username) {
        if (username == null || username.trim().isEmpty()) {
                 JOptionPane.showMessageDialog(null,
                "Username cannot be empty.",
                "Username Error",
                JOptionPane.ERROR_MESSAGE);
                 return false;
        }

             username = username.trim();

            if (username.contains("_") && username.length() <= 5) {
            return true;
             } else {
            JOptionPane.showMessageDialog(null,
                "Invalid Username:\n- Must contain an underscore (_)\n- Must be 5 characters or less.",
                "Username Error",
                JOptionPane.ERROR_MESSAGE);
                 return false;
        }
    }

    /**
     * Checks the complexity of a password.
     * Password must be at least 8 characters long,
     * contain at least one uppercase letter,
     * one digit, and one special character (!@#$%^&*).
     *
     * @param password The password string to validate
     * @return true if password meets complexity, false otherwise
     */
    public static boolean checkPasswordComplexity(String password) {
             if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Password cannot be empty.",
                "Password Error",
                JOptionPane.ERROR_MESSAGE);
                return false;
        }

        password = password.trim();

        String pattern = "(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}";

        if (password.matches(pattern)) {
            return true;
             } else {
            JOptionPane.showMessageDialog(null,
                "Invalid Password:\n- Must be at least 8 characters\n- Must include one uppercase letter\n- Must include one digit\n- Must include one special character (!@#$%^&*)",
                "Password Error",
                JOptionPane.ERROR_MESSAGE);
                 return false;
        }
    }

    /**
     * Validates if the cellphone number is a valid South African number.
     * The number must start with "+27" and be exactly 12 characters long.
     *
     * @param phone The phone number string to validate
     * @return true if phone number matches criteria, false otherwise
     */
    public static boolean checkCellPhoneNumber(String phone) {
                if (phone == null || phone.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                "Phone number cannot be empty.",
                "Phone Number Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        phone = phone.trim();

        if (phone.startsWith("+27") && phone.length() == 12) {
            return true;
             } else {
            JOptionPane.showMessageDialog(null,
                "Invalid Cellphone Number:\n- Must start with +27\n- Must be exactly 12 characters (including +27).",
                "Phone Number Error",
                JOptionPane.ERROR_MESSAGE);
                 return false;
        }
    }

}