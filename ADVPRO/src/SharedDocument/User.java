package SharedDocument;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final String userId;
    private final String username;
    private final Set<String> permissions;

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.permissions = new HashSet<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getPermissions() {
        return Collections.unmodifiableSet(permissions);
    }

    public void addPermission(String permission) {
        permissions.add(permission);
    }

    public void removePermission(String permission) {
        permissions.remove(permission);
    }
}
