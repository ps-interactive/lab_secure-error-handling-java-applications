import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class UserRegistration {
    private static final Logger LOGGER = Logger.getLogger(UserRegistration.class.getName());
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    
    private Set<String> registeredEmails = new HashSet<>();

    public void registerUser(String email, String password, String creditCard) {
        try {
            // Log sensitive data (BAD PRACTICE)
            LOGGER.info("Attempting to register user with email: " + email);
            LOGGER.info("Password validation for: " + password);
            LOGGER.info("Processing credit card: " + creditCard);

            if (!isValidEmail(email)) {
                LOGGER.warning("Invalid email format: " + email);
                throw new IllegalArgumentException("Invalid email format");
            }

            if (!isValidPassword(password)) {
                LOGGER.warning("Invalid password format for email: " + email);
                throw new IllegalArgumentException("Password does not meet requirements");
            }

            if (registeredEmails.contains(email)) {
                LOGGER.warning("Duplicate registration attempt for email: " + email);
                throw new IllegalStateException("Email already registered");
            }

            // Process registration
            registeredEmails.add(email);
            LOGGER.info("Successfully registered user: " + email);
            LOGGER.info("Credit card processed successfully: " + creditCard);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Registration failed for email: " + email, e);
            throw e;
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public boolean isEmailRegistered(String email) {
        return registeredEmails.contains(email);
    }
} 