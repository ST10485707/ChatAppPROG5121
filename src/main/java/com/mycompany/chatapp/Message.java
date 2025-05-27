/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

  package com.mycompany.chatapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

  
  

/**
 * Class for composing and saving messages.
 * Includes both console and JOptionPane versions of message input/output.
 */
public final class Message {

    // Message data fields
    private String recipient;
    private String messageText;
    private String messageID;
    private String messageHash;
    private boolean sent;
    private boolean received;
    private boolean read;

    // Shared message tracking fields and lists
    private static int totalMessages = 0;
    private static final ArrayList<Message> sentMessages = new ArrayList<>();
    private static final ArrayList<Message> storedMessages = new ArrayList<>();
    private final List<String> messages = new ArrayList<>();

    /**
     * Prompts the user to enter a message, then offers options
     * to send the message immediately, disregard it, or store it
     * for sending later. Uses JOptionPane dialogs for interaction.
     */
    public void handleMessageOption() {
        String message = JOptionPane.showInputDialog(null, "Enter your message:");

        if (message == null || message.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Message cannot be empty.");
            return;
        }

        String[] options = {"Send Message", "Disregard Message", "Store Message to Send Later"};

        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an option for your message:\n" + message,
                "Message Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0 -> {
                sendMessage(message);
                displayMessageInDialog(message);
            }
            case 1 -> JOptionPane.showMessageDialog(null, "Message disregarded.");
            case 2 -> {
                this.recipient = JOptionPane.showInputDialog(null, "Enter Recipient (+27...):");
                this.messageText = message;
                this.messageID = generateID();
                this.messageHash = createMessageHash();
                storedMessages.add(this);
                JOptionPane.showMessageDialog(null, "Message stored to send later.");
            }
            default -> JOptionPane.showMessageDialog(null, "No option selected.");
        }
    }

    /**
     * Displays the details of a sent message in a dialog box,
     * including message ID, hash, recipient, and the message text.
     *
     * @param message The message text to display.
     */
    public void displayMessageInDialog(String message) {
        String details = "Message ID: " + messageID + "\n"
                       + "Message Hash: " + messageHash + "\n"
                       + "Recipient: " + recipient + "\n"
                       + "Message: " + messageText;

        JOptionPane.showMessageDialog(null, "Message sent:\n" + details);
    }

    /**
     * Adds the current message instance to the storedMessages list
     * so it can be sent later.
     */
    private void storeMessage() {
        storedMessages.add(this);
    }

    /**
     * Sends all messages currently stored in the storedMessages list.
     * For each stored message, sends it and displays its details,
     * then clears the storedMessages list afterwards.
     */
    public static void sendStoredMessages() {
        if (storedMessages == null || storedMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No stored messages to send.");
            return;
        }

        for (Message msg : storedMessages) {
            msg.sendMessage(msg.getMessageText());
            msg.displayMessageInDialog(msg.getMessageText());
        }

        storedMessages.clear();
        JOptionPane.showMessageDialog(null, "All stored messages have been sent.");
    }

    /**
     * Constructs a new Message instance with a recipient and message content.
     * Automatically generates a unique message ID and creates a message hash.
     *
     * @param recipient The recipient's phone number.
     * @param messageText The content of the message.
     */
    public Message(String recipient, String messageText) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    /**
     * Default constructor allows the setting of fields later,
     * e.g., via setter methods or interactive input.
     */
    public Message() {}

    /**
     * Interactively prompts the user to enter and validate
     * a recipient phone number and message text.
     * Validates the recipient against South African format,
     * and limits message length to 250 characters.
     *
     * @return true if message composed successfully, false otherwise.
     */
    public boolean composeMessage() {
        this.recipient = JOptionPane.showInputDialog(null, "Enter Recipient (+27...):");

        if (recipient == null || recipient.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Recipient cannot be empty.");
            return false;
        }

        if (!checkRecipientCell()) {
            JOptionPane.showMessageDialog(null, "Invalid recipient number.");
            return false;
        }

        this.messageText = JOptionPane.showInputDialog(null, "Enter Your Message:");

        if (messageText == null || messageText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Message cannot be empty.");
            return false;
        }

        if (messageText.length() > 250) {
            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
            return false;
        }

        this.messageID = generateID();
        this.messageHash = createMessageHash();

        JOptionPane.showMessageDialog(null, "Message created successfully.");
        return true;
    }

    /**
     * Prints the message details to the console for debugging
     * or verification purposes.
     */
    public void displayMessage() {
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
    }

    /**
     * Generates a unique random 10-digit message ID.
     *
     * @return A string representing the message ID.
     */
    private String generateID() {
        Random rand = new Random();
        long num = 1000000000L + (long) (rand.nextDouble() * 8999999999L);
        return Long.toString(num);
    }

    /**
     * Creates a simple hash string for the message using:
     * - First two characters of the message ID
     * - Total number of messages created so far
     * - Combination of the first and last words of the message
     *
     * @return The generated message hash string in uppercase.
     */
    public String createMessageHash() {
        if (messageText == null || messageText.isEmpty()) {
            return (messageID.substring(0, 2) + ":" + totalMessages + ":").toUpperCase();
        }

        String[] words = messageText.split(" ");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : first;

        return (messageID.substring(0, 2) + ":" + totalMessages + ":" + first + last).toUpperCase();
    }

    /**
     * Simulates sending a message by marking it as sent and received,
     * increments the total message count, adds the message to the sentMessages list,
     * and shows a dialog with message details.
     *
     * @param message The message text to send (used for display purposes).
     */
    public void sendMessage(String message) {
        this.sent = true;
        this.received = true;
        totalMessages++;
        sentMessages.add(this);

        String details = "Message ID: " + messageID
                       + "\nMessage Hash: " + messageHash
                       + "\nRecipient: " + recipient
                       + "\nMessage: " + messageText;

        JOptionPane.showMessageDialog(null, details, "Message Sent", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Saves the current message's data as a JSON object by appending
     * it to a file named "messages.json". Handles possible IO exceptions.
     */
    public void saveToJson() {
        JSONObject obj = new JSONObject();
        obj.put("messageID", messageID);
        obj.put("messageHash", messageHash);
        obj.put("recipient", recipient);
        obj.put("message", messageText);

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(obj.toJSONString() + "\n");
            System.out.println("Message saved to messages.json.");
        } catch (IOException e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }

    /**
     * Marks the message as read and prints confirmation to console.
     */
    public void readMessage() {
        this.read = true;
        System.out.println("Message has been read.");
    }

    // ========== Getter Methods ==========

    /**
     * Returns whether this message has been sent.
     *
     * @return true if sent, false otherwise.
     */
    public boolean isSent() { return sent; }

    /**
     * Returns whether this message has been read.
     *
     * @return true if read, false otherwise.
     */
    public boolean isRead() { return read; }

    /**
     * Returns the total number of messages created and sent so far.
     *
     * @return total message count.
     */
    public static int getTotalMessages() { return totalMessages; }

    /**
     * Returns a list of all messages that have been sent.
     *
     * @return list of sent messages.
     */
    public static ArrayList<Message> getSentMessages() { return sentMessages; }

    /**
     * Returns the unique ID of this message.
     *
     * @return message ID string.
     */
    public String getMessageID() { return messageID; }

    /**
     * Returns the generated hash string of this message.
     *
     * @return message hash string.
     */
    public String getMessageHash() { return messageHash; }

    /**
     * Returns the recipient phone number for this message.
     *
     * @return recipient phone number string.
     */
    public String getRecipient() { return recipient; }

    /**
     * Returns the text content of this message.
     *
     * @return message text string.
     */
    public String getMessageText() { return messageText; }

    /**
     * Validates if the recipient phone number matches
     * the South African format: +27 followed by 9 digits.
     *
     * @return true if valid format, false otherwise.
     */
    public boolean checkRecipientCell() {
           return recipient != null && recipient.matches("\\+27\\d{9}");

    }

    // ========== Setter Methods ==========

    /**
     * Sets the recipient phone number.
     *
     * @param recipient Phone number string.
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Sets the message text content.
     *
     * @param messageText Text of the message.
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * Sets the message ID (not typically used externally).
     *
     * @param messageID Unique ID string.
     */
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    /**
     * Sets the message hash (not typically used externally).
     *
     * @param messageHash Message hash string.
     */
    public void setMessageHash(String messageHash) {
        this.messageHash = messageHash;
    }

    public String validateRecipientCellWithMessage() {
        if (checkRecipientCell()) {
        return "Cell phone number successfully captured.";
        } else {
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
}