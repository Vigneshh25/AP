package Design_Datastructure.consistenthashingwithzookeeper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class DocumentManager {
    private static DocumentManager instance;
    private final InstanceManager instanceManager;
    private final Map<String, String> documentLocks = new ConcurrentHashMap<>();
    private final Map<String, String> documentContents = new ConcurrentHashMap<>();
    private final Map<String, Long> documentTimestamps = new ConcurrentHashMap<>();
    private final Map<String, List<String>> instanceDocuments = new ConcurrentHashMap<>();
    private final Map<String, List<String>> documentVersions = new ConcurrentHashMap<>();

    private DocumentManager(InstanceManager instanceManager) {
        this.instanceManager = instanceManager;
    }

    public static synchronized DocumentManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("DocumentManager not initialized");
        }
        return instance;
    }

    public static synchronized void initialize(InstanceManager instanceManager) {
        if (instance == null) {
            instance = new DocumentManager(instanceManager);
        }
    }

    public synchronized void handleDocumentRequest(String documentId) {
        String instance = instanceManager.getInstance(documentId);
        if (instanceManager.isInstanceActive(instance) && documentLocks.putIfAbsent(documentId, instance) == null) {
            instanceManager.incrementLoad(instance);
            instanceDocuments.computeIfAbsent(instance, k -> new ArrayList<>()).add(documentId);
            System.out.println("Document " + documentId + " is being handled by " + instance);
        } else {
            System.out.println("Document " + documentId + " is already locked or the instance is inactive");
        }
    }

    public synchronized void releaseDocument(String documentId) {
        String instance = documentLocks.remove(documentId);
        if (instance != null) {
            instanceManager.decrementLoad(instance);
            instanceDocuments.get(instance).remove(documentId);
            System.out.println("Document " + documentId + " released by " + instance);
        }
    }

    public synchronized void editDocument(String documentId, String content) {
        if (documentLocks.containsKey(documentId)) {
            documentContents.put(documentId, content);
            documentTimestamps.put(documentId, System.currentTimeMillis());
            documentVersions.computeIfAbsent(documentId, k -> new ArrayList<>()).add(content);
            broadcastDocumentUpdate(documentId, content);
            System.out.println("Document " + documentId + " edited with new content: " + content);
        } else {
            System.out.println("Document " + documentId + " is not locked and cannot be edited");
        }
    }

    private void broadcastDocumentUpdate(String documentId, String content) {
        List<String> instances = instanceManager.listInstances();
        for (String instance : instances) {
            if (!documentLocks.get(documentId).equals(instance) && instanceManager.isInstanceActive(instance)) {
                System.out.println("Notifying instance " + instance + " about document " + documentId + " update");
            }
        }
    }

    public String getDocumentContent(String documentId) {
        return documentContents.get(documentId);
    }

    public long getDocumentTimestamp(String documentId) {
        return documentTimestamps.get(documentId);
    }

    public List<String> getDocumentsByInstance(String instance) {
        return instanceDocuments.getOrDefault(instance, Collections.emptyList());
    }

    public List<String> getDocumentVersions(String documentId) {
        return documentVersions.getOrDefault(documentId, Collections.emptyList());
    }
}