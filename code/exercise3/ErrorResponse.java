import java.time.LocalDateTime;

public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final String errorCode;
    private final String message;
    private final String path;
    private final int status;

    public ErrorResponse(String errorCode, String message, String path, int status) {
        this.timestamp = LocalDateTime.now();
        this.errorCode = errorCode;
        this.message = message;
        this.path = path;
        this.status = status;
    }

    // Getters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return status;
    }

    // Builder pattern for easy construction
    public static class Builder {
        private String errorCode;
        private String message;
        private String path;
        private int status;

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(errorCode, message, path, status);
        }
    }
} 