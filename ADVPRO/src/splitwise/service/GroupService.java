package splitwise.service;

import splitwise.Repositories.GroupRepository;
import splitwise.entities.Group;

import java.util.List;

// GroupService.java
public class GroupService {
    private GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
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
