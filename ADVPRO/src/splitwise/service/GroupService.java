package splitwise.service;

import splitwise.Repositories.GroupRepository;
import splitwise.Repositories.GroupRepositoryImpl;
import splitwise.entities.Group;

import java.util.List;

// GroupService.java
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService() {
        groupRepository = new GroupRepositoryImpl();
    }

    public void createGroup(Group group) {
        groupRepository.save(group);
    }

    public Group getGroupById(long groupId) {
        return groupRepository.findById(groupId);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }
}
