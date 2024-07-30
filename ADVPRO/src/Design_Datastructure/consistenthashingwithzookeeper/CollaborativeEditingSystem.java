package Design_Datastructure.consistenthashingwithzookeeper;

public class CollaborativeEditingSystem {
    public static void main(String[] args) {
        ConsistentHashing consistentHashing = new ConsistentHashing(3);
        ZookeeperSimulator zookeeper = new ZookeeperSimulator();
        InstanceManager instanceManager = new InstanceManager(consistentHashing, zookeeper);
        DocumentManager.initialize(instanceManager);

        DocumentManager documentManager = DocumentManager.getInstance();

        instanceManager.registerInstance("instance1");
        instanceManager.registerInstance("instance2");
        instanceManager.registerInstance("instance3");

        documentManager.handleDocumentRequest("123");
        documentManager.editDocument("123", "First edit to document 123");
        documentManager.handleDocumentRequest("456");
        documentManager.editDocument("456", "First edit to document 456");
        documentManager.handleDocumentRequest("789");

        System.out.println("Content of document 123: " + documentManager.getDocumentContent("123"));
        System.out.println("Timestamp of document 123: " + documentManager.getDocumentTimestamp("123"));
        System.out.println("Versions of document 123: " + documentManager.getDocumentVersions("123"));

        System.out.println("Current instances: " + instanceManager.listInstances());

        documentManager.releaseDocument("123");
        documentManager.releaseDocument("456");

        instanceManager.failInstance("instance2");

        documentManager.handleDocumentRequest("123");
        documentManager.editDocument("123", "Second edit to document 123");
        documentManager.handleDocumentRequest("456");
        documentManager.editDocument("456", "Second edit to document 456");

        System.out.println("Content of document 123: " + documentManager.getDocumentContent("123"));
        System.out.println("Timestamp of document 123: " + documentManager.getDocumentTimestamp("123"));
        System.out.println("Versions of document 123: " + documentManager.getDocumentVersions("123"));

        System.out.println("Current instances: " + instanceManager.listInstances());
    }
}
