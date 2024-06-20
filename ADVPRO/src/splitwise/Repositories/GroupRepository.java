package splitwise.Repositories;

import splitwise.entities.Group;

import java.util.List;

// GroupRepository.java
public interface GroupRepository {
    void save(Group group);
    Group findById(long groupId);
    void delete(Group group);
    List<Group> findAll();
}
