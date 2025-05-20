public class TestRegistration {
    public static void main(String[] args) {
        UserRegistration registration = new UserRegistration();
        
        // Test 1: Valid registration
        System.out.println("\nTest 1: Valid Registration");
        try {
            registration.registerUser(
                "user@example.com",
                "SecurePass123!",
                "4111-1111-1111-1111"
            );
            System.out.println("✓ Registration successful");
        } catch (Exception e) {
            System.out.println("✗ Registration failed: " + e.getMessage());
        }

        // Test 2: Invalid email format
        System.out.println("\nTest 2: Invalid Email Format");
        try {
            registration.registerUser(
                "invalid-email",
                "SecurePass123!",
                "4111-1111-1111-1111"
            );
            System.out.println("✗ Should have failed with invalid email");
        } catch (Exception e) {
            System.out.println("✓ Registration failed as expected: " + e.getMessage());
        }

        // Test 3: Invalid password format
        System.out.println("\nTest 3: Invalid Password Format");
        try {
            registration.registerUser(
                "another@example.com",
                "weak",
                "4111-1111-1111-1111"
            );
            System.out.println("✗ Should have failed with invalid password");
        } catch (Exception e) {
            System.out.println("✓ Registration failed as expected: " + e.getMessage());
        }

        // Test 4: Duplicate registration
        System.out.println("\nTest 4: Duplicate Registration");
        try {
            registration.registerUser(
                "user@example.com",
                "AnotherPass123!",
                "4111-1111-1111-1111"
            );
            System.out.println("✗ Should have failed with duplicate email");
        } catch (Exception e) {
            System.out.println("✓ Registration failed as expected: " + e.getMessage());
        }
    }
} 