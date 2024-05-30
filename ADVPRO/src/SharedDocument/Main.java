package SharedDocument;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DocumentRepositoryService service = new DocumentRepositoryService();
        
        // Create users
        User user1 = new User("1", "Alice");
        User user2 = new User("2", "Bob");

        // Create a document
        Document doc1 = service.createDocument(user1.getUserId(), "DesignDoc", "This is the design document.");

        // Grant write permission to another user
        service.grantPermission(doc1.getId(), user2.getUserId(), "write");

        // Update document content
        service.updateDocument(user2.getUserId(), doc1.getId(), "This is the updated design document.");

        // Search documents by filename
        List<Document> searchResults = service.searchDocumentsByFilename("Design");
        for (Document doc : searchResults) {
            System.out.println("Found document: " + doc.getFilename());
        }

        // Search documents by content
        searchResults = service.searchDocumentsByContent("updated");
        for (Document doc : searchResults) {
            System.out.println("Found document with content: " + doc.getContent());
        }

        // Print document version history
        List<String> versionHistory = doc1.getVersionHistory();
        for (String version : versionHistory) {
            System.out.println("Version: " + version);
        }
    }
}
