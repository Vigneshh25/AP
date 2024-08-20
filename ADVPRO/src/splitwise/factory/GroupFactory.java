package splitwise.factory;

import splitwise.entities.Group;
import splitwise.entities.User;

import java.util.List;

public class GroupFactory {

    public static Group createGroup(long id, String name, List<User> members) {
        Group group = new Group(id, name);
        members.forEach(group::addMember);
        return group;
    }
}
