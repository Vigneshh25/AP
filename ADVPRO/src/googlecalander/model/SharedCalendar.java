package googlecalander.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedCalendar implements ShareableCalendar {
    private CalendarComponent calendar;
    private Map<User, Permission> userPermissions = new HashMap<>();

    public SharedCalendar(CalendarComponent calendar) {
        this.calendar = calendar;
    }

    @Override
    public void share(User user, Permission permission) {
        userPermissions.put(user, permission);
    }

    @Override
    public List<User> getSharedUsers() {
        return new ArrayList<>(userPermissions.keySet());
    }

}