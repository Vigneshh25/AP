package SharedDocument;

import java.util.*;

public class AccessControlList {
    private final Map<String, Map<String, Set<String>>> acl;

    public AccessControlList() {
        acl = new HashMap<>();
    }

    public void grantPermission(String documentId, String userId, String permission) {
        acl.computeIfAbsent(documentId, k -> new HashMap<>())
           .computeIfAbsent(userId, k -> new HashSet<>())
           .add(permission);
    }

    public void revokePermission(String documentId, String userId, String permission) {
        Map<String, Set<String>> userPermissions = acl.get(documentId);
        if (userPermissions != null) {
            Set<String> permissions = userPermissions.get(userId);
            if (permissions != null) {
                permissions.remove(permission);
                if (permissions.isEmpty()) {
                    userPermissions.remove(userId);
                }
            }
        }
    }

    public boolean checkPermission(String documentId, String userId, String permission) {
        return acl.containsKey(documentId) &&
               acl.get(documentId).containsKey(userId) &&
               acl.get(documentId).get(userId).contains(permission);
    }
}
