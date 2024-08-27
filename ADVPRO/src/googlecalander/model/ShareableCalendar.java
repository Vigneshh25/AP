package googlecalander.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Decorator Pattern for Sharing and Permissions
public interface ShareableCalendar {
    void share(User user, Permission permission);
    List<User> getSharedUsers();
}


