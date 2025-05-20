public class TestApi {
    public static void main(String[] args) {
        ApiController api = new ApiController();
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        
        // Test 1: Create valid user
        System.out.println("\nTest 1: Create Valid User");
        try {
            UserRequest request = new UserRequest();
            request.setName("John Doe");
            request.setEmail("john@example.com");
            
            User user = api.createUser(request);
            System.out.println("✓ User created successfully: " + user.getName());
            System.out.println("  ID: " + user.getId());
            System.out.println("  Email: " + user.getEmail());
        } catch (Exception e) {
            ErrorResponse error = handler.handleUnexpectedException(e, "/api/users");
            System.out.println("✗ User creation failed: " + error.getMessage());
        }

        // Test 2: Create user with invalid email
        System.out.println("\nTest 2: Create User with Invalid Email");
        try {
            UserRequest request = new UserRequest();
            request.setName("Jane Doe");
            request.setEmail("invalid-email");
            
            api.createUser(request);
            System.out.println("✗ Should have failed with invalid email");
        } catch (Exception e) {
            ErrorResponse error = handler.handleUnexpectedException(e, "/api/users");
            System.out.println("✓ User creation failed as expected: " + error.getMessage());
        }

        // Test 3: Create duplicate user
        System.out.println("\nTest 3: Create Duplicate User");
        try {
            UserRequest request = new UserRequest();
            request.setName("John Doe");
            request.setEmail("john@example.com");
            
            api.createUser(request);
            System.out.println("✗ Should have failed with duplicate email");
        } catch (Exception e) {
            ErrorResponse error = handler.handleUnexpectedException(e, "/api/users");
            System.out.println("✓ User creation failed as expected: " + error.getMessage());
        }

        // Test 4: Get non-existent user
        System.out.println("\nTest 4: Get Non-existent User");
        try {
            api.getUser("non-existent-id");
            System.out.println("✗ Should have failed with user not found");
        } catch (Exception e) {
            ErrorResponse error = handler.handleUnexpectedException(e, "/api/users/non-existent-id");
            System.out.println("✓ User retrieval failed as expected: " + error.getMessage());
        }

        // Test 5: Update user with invalid data
        System.out.println("\nTest 5: Update User with Invalid Data");
        try {
            // First create a valid user
            UserRequest createRequest = new UserRequest();
            createRequest.setName("Alice Smith");
            createRequest.setEmail("alice@example.com");
            User user = api.createUser(createRequest);

            // Try to update with invalid email
            UserRequest updateRequest = new UserRequest();
            updateRequest.setEmail("invalid-email");
            api.updateUser(user.getId(), updateRequest);
            System.out.println("✗ Should have failed with invalid email");
        } catch (Exception e) {
            ErrorResponse error = handler.handleUnexpectedException(e, "/api/users/update");
            System.out.println("✓ User update failed as expected: " + error.getMessage());
        }
    }
} 