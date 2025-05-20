public class CustomException extends RuntimeException {
    private final String errorCode;
    private final int httpStatus;

    public CustomException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}

class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException(String resource, String identifier) {
        super(
            String.format("%s not found with identifier: %s", resource, identifier),
            "RESOURCE_NOT_FOUND",
            404
        );
    }
}

class ValidationException extends CustomException {
    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR", 400);
    }
}

class DuplicateResourceException extends CustomException {
    public DuplicateResourceException(String resource, String identifier) {
        super(
            String.format("%s already exists with identifier: %s", resource, identifier),
            "DUPLICATE_RESOURCE",
            409
        );
    }
}

class InternalServerException extends CustomException {
    public InternalServerException(String message) {
        super(message, "INTERNAL_SERVER_ERROR", 500);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, "INTERNAL_SERVER_ERROR", 500);
        initCause(cause);
    }
} 