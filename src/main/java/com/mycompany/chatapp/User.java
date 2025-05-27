/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author mila
 */
public class User {
    // User's personal details and credentials stored as private final fields
    private final String username;
    private final String password;
    private final String phone;
    private final String firstName;
    private final String lastName;

    /**
     * Constructor to initialize a new User object with all necessary details.
     * @param username
     * @param password
     * @param phone
     * @param firstName
     * @param lastName
     */
    public User(String username, String password, String phone, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Validates the login credentials entered by the user.
     * @param username
     * @return 
     */
    public boolean login(String username, String password) {
        if (username == null || password == null) return false;
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Returns a welcome or error message based on login result.
     * @param isLoggedIn
     * @return 
     */
    public String returnLoginStatus(boolean isLoggedIn) {
            return isLoggedIn
            ? "Welcome Back!, " + firstName + " " + lastName
            : "Username or password incorrect, please try again";
}

    // Optional: Getters for accessing user info if needed
    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}


