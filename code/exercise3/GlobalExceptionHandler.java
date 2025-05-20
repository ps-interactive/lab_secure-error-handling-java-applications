import java.util.logging.Logger;
import java.util.logging.Level;

public class GlobalExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());

    public ErrorResponse handleCustomException(CustomException ex, String path) {
        LOGGER.log(Level.WARNING, "Custom exception occurred: " + ex.getMessage());
        return new ErrorResponse.Builder()
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(path)
            .status(ex.getHttpStatus())
            .build();
    }

    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex, String path) {
        LOGGER.log(Level.WARNING, "Resource not found: " + ex.getMessage());
        return new ErrorResponse.Builder()
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(path)
            .status(ex.getHttpStatus())
            .build();
    }

    public ErrorResponse handleValidationException(ValidationException ex, String path) {
        LOGGER.log(Level.WARNING, "Validation error: " + ex.getMessage());
        return new ErrorResponse.Builder()
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(path)
            .status(ex.getHttpStatus())
            .build();
    }

    public ErrorResponse handleDuplicateResourceException(DuplicateResourceException ex, String path) {
        LOGGER.log(Level.WARNING, "Duplicate resource: " + ex.getMessage());
        return new ErrorResponse.Builder()
            .errorCode(ex.getErrorCode())
            .message(ex.getMessage())
            .path(path)
            .status(ex.getHttpStatus())
            .build();
    }

    public ErrorResponse handleInternalServerException(InternalServerException ex, String path) {
        LOGGER.log(Level.SEVERE, "Internal server error: " + ex.getMessage(), ex);
        return new ErrorResponse.Builder()
            .errorCode(ex.getErrorCode())
            .message("An internal server error occurred. Please try again later.")
            .path(path)
            .status(ex.getHttpStatus())
            .build();
    }

    public ErrorResponse handleUnexpectedException(Exception ex, String path) {
        LOGGER.log(Level.SEVERE, "Unexpected error: " + ex.getMessage(), ex);
        return new ErrorResponse.Builder()
            .errorCode("INTERNAL_SERVER_ERROR")
            .message("An unexpected error occurred. Please try again later.")
            .path(path)
            .status(500)
            .build();
    }
} 