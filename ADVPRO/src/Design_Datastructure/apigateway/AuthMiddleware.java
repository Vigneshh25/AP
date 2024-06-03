package Design_Datastructure.apigateway;

public class AuthMiddleware {
    public boolean authenticate(String token) {
        // Simulate token authentication
        return token.equals("valid-token");
    }

    public boolean authorize(String path, String role) {
        // Simulate role-based authorization
        return role.equals("admin") || path.startsWith("/public");
    }
}
