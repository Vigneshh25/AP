package googlecalander.model;

import java.util.ArrayList;
import java.util.List;

// Observer Pattern for Invitations
public interface InvitationObserver {
    void update(User user, Event event);
}

