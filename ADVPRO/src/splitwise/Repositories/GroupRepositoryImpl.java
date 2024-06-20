package splitwise.Repositories;

import splitwise.entities.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRepositoryImpl implements GroupRepository {
    private Map<Long, Group> groupDatabase = new HashMap<>();

    @Override
    public void save(Group group) {
        groupDatabase.put(group.getGroupId(), group);
    }

    @Override
    public Group findById(long groupId) {
        return groupDatabase.get(groupId);
    }

    @Override
    public void delete(Group group) {
        groupDatabase.remove(group.getGroupId());
    }

    @Override
    public List<Group> findAll() {
        return new ArrayList<>(groupDatabase.values());
    }
}
