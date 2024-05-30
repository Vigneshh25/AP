package SharedDocument;

import java.util.*;

public class DocumentRepositoryService {
    private final Map<String, Document> documents = new HashMap<>();
    private final AccessControlList acl = new AccessControlList();

    public Document createDocument(String userId, String filename, String content) {
        String documentId = UUID.randomUUID().toString();
        Document document = new Document(documentId, filename, content);
        documents.put(documentId, document);
        acl.grantPermission(documentId, userId, "owner");
        return document;
    }

    public void updateDocument(String userId, String documentId, String newContent) {
        if (acl.checkPermission(documentId, userId, "write")) {
            Document document = documents.get(documentId);
            document.setContent(newContent);
        } else {
            throw new SecurityException("User does not have write permission");
        }
    }

    public Document getDocument(String documentId) {
        return documents.get(documentId);
    }

    public List<Document> searchDocumentsByFilename(String query) {
        List<Document> results = new ArrayList<>();
        for (Document document : documents.values()) {
            if (document.getFilename().contains(query)) {
                results.add(document);
            }
        }
        return results;
    }

    public List<Document> searchDocumentsByContent(String query) {
        List<Document> results = new ArrayList<>();
        for (Document document : documents.values()) {
            if (document.getContent().contains(query)) {
                results.add(document);
            }
        }
        return results;
    }

    public void grantPermission(String documentId, String userId, String permission) {
        acl.grantPermission(documentId, userId, permission);
    }

    public void revokePermission(String documentId, String userId, String permission) {
        acl.revokePermission(documentId, userId, permission);
    }
}
