import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class ApiController {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private Map<String, User> users = new HashMap<>();

    public User createUser(UserRequest request) {
        validateUserRequest(request);

        if (users.values().stream().anyMatch(u -> u.getEmail().equals(request.getEmail()))) {
            throw new DuplicateResourceException(
                "A user with the email address '" + request.getEmail() + "' already exists. Please use a different email address."
            );
        }

        try {
            User user = new User(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getEmail()
            );
            users.put(user.getId(), user);
            return user;
        } catch (Exception e) {
            throw new InternalServerException(
                "Unable to create user account at this time. Please try again later.",
                e
            );
        }
    }

    public User getUser(String id) {
        User user = users.get(id);
        if (user == null) {
            throw new ResourceNotFoundException(
                "No user account found with the provided ID. Please verify the ID and try again."
            );
        }
        return user;
    }

    public void updateUser(String id, UserRequest request) {
        User user = users.get(id);
        if (user == null) {
            throw new ResourceNotFoundException(
                "No user account found with the provided ID. Please verify the ID and try again."
            );
        }

        try {
            if (request.getName() != null) {
                if (request.getName().trim().isEmpty()) {
                    throw new ValidationException(
                        "The name field cannot be empty. Please provide a valid name."
                    );
                }
                user.setName(request.getName());
            }

            if (request.getEmail() != null) {
                if (!EMAIL_PATTERN.matcher(request.getEmail()).matches()) {
                    throw new ValidationException(
                        "The email address '" + request.getEmail() + "' is not in a valid format. Please use a valid email address."
                    );
                }
                if (users.values().stream()
                        .anyMatch(u -> !u.getId().equals(id) && u.getEmail().equals(request.getEmail()))) {
                    throw new DuplicateResourceException(
                        "The email address '" + request.getEmail() + "' is already in use. Please use a different email address."
                    );
                }
                user.setEmail(request.getEmail());
            }
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(
                "Unable to update user account at this time. Please try again later.",
                e
            );
        }
    }

    public void deleteUser(String id) {
        if (!users.containsKey(id)) {
            throw new ResourceNotFoundException(
                "No user account found with the provided ID. Please verify the ID and try again."
            );
        }
        try {
            users.remove(id);
        } catch (Exception e) {
            throw new InternalServerException(
                "Unable to delete user account at this time. Please try again later.",
                e
            );
        }
    }

    private void validateUserRequest(UserRequest request) {
        if (request == null) {
            throw new ValidationException(
                "The request cannot be empty. Please provide valid user information."
            );
        }

        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new ValidationException(
                "The name field is required. Please provide a valid name."
            );
        }

        if (request.getEmail() == null || !EMAIL_PATTERN.matcher(request.getEmail()).matches()) {
            throw new ValidationException(
                "A valid email address is required. Please provide an email address in the correct format."
            );
        }
    }
}

// User and UserRequest classes remain the same as in the original implementation
class User {
    private String id;
    private String name;
    private String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

class UserRequest {
    private String name;
    private String email;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
} 