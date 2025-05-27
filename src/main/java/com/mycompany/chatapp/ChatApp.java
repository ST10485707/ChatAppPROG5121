/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.chatapp;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
/**
 *
 * @author mila
 * OpenAI. (2024). ChatGPT (GPT-4) response to code generation prompt [Computer 
software]. Available from https://chat.openai.com/cha
 */
//Updated code based on A1 Feedback

public class ChatApp {

     public static void main(String[] args) {
        // Display a welcome message using a dialog box
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        // Start the chat system (user registration and login process)
        boolean loggedIn = ChatSystem.startChat();

        if (loggedIn) {
            int totalMessages = 0;
            boolean validInput = false;

            // Get valid number of messages
            while (!validInput) {
                String input = JOptionPane.showInputDialog("How many messages would you like to send?");
                try {
                    totalMessages = Integer.parseInt(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number. Please enter a valid integer.");
                }
            }

            int count = 0; // Total messages sent

            OUTER:
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
                       // Send Message
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
                        Message message = new Message(recipient.trim(), messageText.trim());
                           message.composeMessage();
                            message.displayMessage();
                             message.saveToJson();

                            JOptionPane.showMessageDialog(null, "Message sent and saved successfully.");
                            count++; // Increment the actual message count
                            } catch (HeadlessException e) {
                               JOptionPane.showMessageDialog(null, "Error sending message: " + e.getMessage(),
                                      "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                    }

                    case 1 -> {
                        // Show Recently Sent Messages
                        Message.sendStoredMessages();
                    }

                    case 2, JOptionPane.CLOSED_OPTION -> {
                        // Quit
                        JOptionPane.showMessageDialog(null, "You sent a total of " + count + " messages.\nThank you for using QuickChat.");
                        break OUTER;
                    }

                    default -> {
                        JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                    }
                }
            }

            // Final confirmation (already shown in "Quit", this is just redundancy-safe)
            JOptionPane.showMessageDialog(null, "You sent a total of " + count + " messages.");
        } else {
            // Login failed
            JOptionPane.showMessageDialog(null, "Login failed. Exiting QuickChat.");
        }
     }
}