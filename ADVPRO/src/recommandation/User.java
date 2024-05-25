package recommandation;

import java.util.ArrayList;
import java.util.List;

// Represents a User entity
class User {
    private String email;
    private List<Action> actions;

    public User(String email) {
        this.email = email;
        this.actions = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void addAction(Action action) {
        actions.add(action);
    }
}
