/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.chatapp;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

 /* Name: Mila Mpengesi
 * Student Number:  st10485707
 * POE: Part 2
 * Date: 26 May 2025
 * Description: This application handles messages by saving, sending, and showing them through dialog boxes.
We appreciate your support and look forward to collaborating with you in the future.
*/

 

public class ChatApp {
    // Main entry point: shows welcome message and starts chat system
    public static void main(String[] args) {
       
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        // Begin registration and login process, returns true if login successful
        boolean loggedIn = ChatSystem.startChat();

        if (loggedIn) {
            int totalMessages = 0;
            boolean validInput = false;

            // Loop until user enters a valid integer for number of messages to send
            while (!validInput) {
                String input = JOptionPane.showInputDialog("How many messages would you like to send?");
                try {
                    totalMessages = Integer.parseInt(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number. Please enter a valid integer.");
                }
            }

            int count = 0; // Tracks how many messages have been sent

            OUTER:
            // Main menu loop, runs until user chooses to quit or sends all messages
            while (count < totalMessages) {
                String[] options = {"Send Message", "Show Recently Sent Messages", "Quit"};
                int option = JOptionPane.showOptionDialog(
                        null,
                        "Choose an option:",
                        "QuickChat Menu",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                switch (option) {
                    case 0 -> {
                       // Send message option: collect recipient and message, validate inputs
                        String recipient = JOptionPane.showInputDialog("Enter recipient phone number:");
                        if (recipient == null || recipient.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Recipient number is required.");
                            break;
                        }

                        String messageText = JOptionPane.showInputDialog("Enter your message:");
                        if (messageText == null || messageText.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Message content is required.");
                            break;
                        }

                        try {
                            // Create Message object and process sending
                            Message message = new Message(recipient.trim(), messageText.trim());
                            message.composeMessage();
                            message.displayMessage();
                            message.saveToJson();

                            JOptionPane.showMessageDialog(null, "Message sent and saved successfully.");
                            count++; // Increase message count after successful send
                        } catch (HeadlessException e) {
                            JOptionPane.showMessageDialog(null, "Error sending message: " + e.getMessage(),
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    case 1 -> {
                        // Show all messages saved during the session
                        Message.sendStoredMessages();
                    }

                    case 2, JOptionPane.CLOSED_OPTION -> {
                        // Quit option: thank user and exit menu loop
                        JOptionPane.showMessageDialog(null, "You sent a total of " + count + " messages.\nThank you for using QuickChat.");
                        break OUTER;
                    }

                    default -> {
                        // Handle unexpected option inputs gracefully
                        JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                    }
                }
            }

            // Final message count confirmation (safe fallback in case quit message missed)
            JOptionPane.showMessageDialog(null, "You sent a total of " + count + " messages.");
        } else {
            // Inform user of login failure and end program
            JOptionPane.showMessageDialog(null, "Login failed. Exiting QuickChat.");
        }
    }
}
