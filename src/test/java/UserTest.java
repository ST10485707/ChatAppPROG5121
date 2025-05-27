/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.chatapp.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mila
 */
public class UserTest {

    /**
     * Test case to verify login succeeds with correct username and password.
     */
    @Test
    public void testLoginSuccess() {
        // Create a new User object with valid credentials
        User user = new User("user_", "Pass@123", "+27123456789", "John", "Doe");
        
        // Attempt login with correct username and password
        boolean result = user.login("user_", "Pass@123");
        
        // Assert that login should succeed (return true)
        assertTrue(result, "Login should succeed with correct credentials");
    }

    /**
     * Test case to verify login fails with correct username but incorrect password.
     */
    @Test
    public void testLoginFailure() {
        // Create a new User object with valid credentials
        User user = new User("user_", "Pass@123", "+27123456789", "John", "Doe");
        
        // Attempt login with correct username but wrong password
        boolean result = user.login("user_", "WrongPass");
        
        // Assert that login should fail (return false)
        assertFalse(result, "Login should fail with incorrect password");
    }

    /**
     * Test case to verify the login status message returned after login attempt.
     */
    @Test
    public void testReturnLoginStatus() {
        // Create a new User object with valid credentials
        User user = new User("john_", "Pass@123", "1234567890", "John", "Doe");
        
        // Perform login with correct credentials
        boolean isLoggedIn = user.login("john_", "Pass@123");
        
        // Retrieve the login status message based on login success
        String status = user.returnLoginStatus(isLoggedIn);
        
        // Assert that the status message matches expected output
        assertEquals("Welcome Back!, John Doe", status, "Login status message should be correct");
    }
}
