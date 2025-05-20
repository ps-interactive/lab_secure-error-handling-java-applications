import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class UserRegistration {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    
    private Set<String> registeredEmails = new HashSet<>();

    public void registerUser(String email, String password, String creditCard) {
        try {
            // Set up logging context
            LoggingUtil.setRequestId();
            LoggingUtil.setUserEmail(email);

            // Log registration attempt (without sensitive data)
            LoggingUtil.logInfo("Registration attempt initiated");

            if (!isValidEmail(email)) {
                LoggingUtil.logWarning("Invalid email format");
                throw new IllegalArgumentException("Invalid email format");
            }

            if (!isValidPassword(password)) {
                LoggingUtil.logWarning("Password validation failed");
                throw new IllegalArgumentException("Password does not meet requirements");
            }

            if (registeredEmails.contains(email)) {
                LoggingUtil.logWarning("Duplicate registration attempt");
                throw new IllegalStateException("Email already registered");
            }

            // Process registration
            registeredEmails.add(email);
            LoggingUtil.logInfo("User registration successful");
            LoggingUtil.logInfo("Credit card processed successfully: " + LoggingUtil.maskCreditCard(creditCard));

        } catch (Exception e) {
            LoggingUtil.logError("Registration failed", e);
            throw e;
        } finally {
            LoggingUtil.clearContext();
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