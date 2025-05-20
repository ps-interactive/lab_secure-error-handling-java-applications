import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ApiController {
    private Map<String, User> users = new HashMap<>();

    public User createUser(UserRequest request) {
        // Basic validation without proper error handling
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new RuntimeException("Name cannot be empty");
        }

        if (request.getEmail() == null || !request.getEmail().contains("@")) {
            throw new RuntimeException("Invalid email format");
        }

        if (users.values().stream().anyMatch(u -> u.getEmail().equals(request.getEmail()))) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User(
            UUID.randomUUID().toString(),
            request.getName(),
            request.getEmail()
        );

        users.put(user.getId(), user);
        return user;
    }

    public User getUser(String id) {
        User user = users.get(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return user;
    }

    public void updateUser(String id, UserRequest request) {
        User user = users.get(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            if (!request.getEmail().contains("@")) {
                throw new RuntimeException("Invalid email format");
            }
            if (users.values().stream()
                    .anyMatch(u -> !u.getId().equals(id) && u.getEmail().equals(request.getEmail()))) {
                throw new RuntimeException("Email already exists");
            }
            user.setEmail(request.getEmail());
        }
    }

    public void deleteUser(String id) {
        if (!users.containsKey(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        users.remove(id);
    }
}

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