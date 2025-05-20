import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.UUID;

public class LoggingUtil {
    private static final Logger LOGGER = Logger.getLogger(LoggingUtil.class.getName());
    private static final ThreadLocal<String> requestId = new ThreadLocal<>();
    private static final ThreadLocal<String> userEmail = new ThreadLocal<>();

    public static void setRequestId() {
        requestId.set(UUID.randomUUID().toString());
    }

    public static void setUserEmail(String email) {
        userEmail.set(email);
    }

    public static void clearContext() {
        requestId.remove();
        userEmail.remove();
    }

    public static void logInfo(String message) {
        String context = String.format("[RequestId: %s, User: %s] ",
            requestId.get() != null ? requestId.get() : "N/A",
            userEmail.get() != null ? maskEmail(userEmail.get()) : "N/A");
        LOGGER.info(context + message);
    }

    public static void logWarning(String message) {
        String context = String.format("[RequestId: %s, User: %s] ",
            requestId.get() != null ? requestId.get() : "N/A",
            userEmail.get() != null ? maskEmail(userEmail.get()) : "N/A");
        LOGGER.warning(context + message);
    }

    public static void logError(String message, Throwable t) {
        String context = String.format("[RequestId: %s, User: %s] ",
            requestId.get() != null ? requestId.get() : "N/A",
            userEmail.get() != null ? maskEmail(userEmail.get()) : "N/A");
        LOGGER.log(Level.SEVERE, context + message, t);
    }

    private static String maskEmail(String email) {
        if (email == null || email.length() < 5) {
            return "***";
        }
        int atIndex = email.indexOf('@');
        if (atIndex <= 1) {
            return "***";
        }
        return email.charAt(0) + "***" + email.substring(atIndex);
    }

    public static String maskCreditCard(String creditCard) {
        if (creditCard == null || creditCard.length() < 4) {
            return "***";
        }
        return "****-****-****-" + creditCard.substring(creditCard.length() - 4);
    }
} 