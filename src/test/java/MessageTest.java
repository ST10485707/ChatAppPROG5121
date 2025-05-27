/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.chatapp.Message;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mila
 */
public class MessageTest {

    // Test if a correctly sized message passes the length check
    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hello world");
        assertTrue(msg.getMessageText().length() <= 250, "Message should be 250 characters or less");
    }

    // Test if an overly long message fails the length check
    @Test
    public void testMessageLengthFailure() {
        String longMessage = "a".repeat(260); // Message longer than 250 characters
        Message msg = new Message("+27718693002", longMessage);
        assertFalse(msg.getMessageText().length() <= 250, "Message should be rejected if longer than 250 characters");
    }

    // Test if a valid cellphone number format passes
    @Test
    public void testRecipientFormatSuccess() {
        Message msg = new Message("+27718693002", "Hello Eugene");
        assertTrue(msg.checkRecipientCell(), "Valid cellphone number should pass");
    }

    // Test if invalid cellphone number format fails
    @Test
    public void testRecipientFormatFailure() {
        Message msg = new Message("08575975889", "Hello Keegan");
        assertFalse(msg.checkRecipientCell(), "Invalid cellphone number should fail");
    }

    // Test if message hash contains ':' separator
    @Test
    public void testMessageHash() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us tonight?");
        assertTrue(msg.createMessageHash().contains(":"), "Message hash should contain ':' separator");
    }

    // Test if a message can be marked as read
    @Test
    public void testMarkMessageAsRead() {
        Message msg = new Message("+27718693002", "Hello, this is a test message.");
        msg.readMessage(); // Mark as read
        assertTrue(msg.isRead(), "Message should be marked as read.");
    }

    // Test if a read message displays the correct status
    @Test
    public void testMessageReadStatusDisplay() {
        Message msg = new Message("+27718693002", "Another test message.");

        // Before reading
        assertFalse(msg.isRead(), "Message should initially not be marked as read.");

        // After marking as read
        msg.readMessage();
        assertTrue(msg.isRead(), "Message should now be marked as read after calling readMessage().");
    }

    // Test if correct formatted recipient returns the success message
    @Test
    public void testValidRecipientFormatSuccess() {
        Message msg = new Message("+27738693002", "Test message");
        String result = msg.validateRecipientCellWithMessage();
        assertTrue(result.contains("successfully captured"), "Correct number should confirm successful capture.");
    }

    // Test if incorrectly formatted recipient returns the failure message
    @Test
    public void testValidateRecipientFormatFailure() {
        Message msg = new Message("+27738693002", "Test message");
    String result = msg.validateRecipientCellWithMessage();
    assertTrue(result.contains("successfully captured"), "Correct number should confirm successful capture.");
    }
}
       