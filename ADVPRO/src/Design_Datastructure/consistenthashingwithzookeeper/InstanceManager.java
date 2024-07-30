package Design_Datastructure.consistenthashingwithzookeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class InstanceManager {
    private final List<String> instances = new ArrayList<>();
    private final ConsistentHashing consistentHashing;
    private final ZookeeperSimulator zookeeper;
    private final Map<String, AtomicInteger> instanceLoad = new ConcurrentHashMap<>();
    private final Map<String, Boolean> instanceStatus = new ConcurrentHashMap<>();

    public InstanceManager(ConsistentHashing consistentHashing, ZookeeperSimulator zookeeper) {
        this.consistentHashing = consistentHashing;
        this.zookeeper = zookeeper;
    }

    public void registerInstance(String instance) {
        instances.add(instance);
        consistentHashing.addInstance(instance);
        zookeeper.registerNode("/instances/" + instance, instance);
        instanceLoad.put(instance, new AtomicInteger(0));
        instanceStatus.put(instance, true);
    }

    public void deregisterInstance(String instance) {
        instances.remove(instance);
        consistentHashing.removeInstance(instance);
        zookeeper.deregisterNode("/instances/" + instance);
        instanceLoad.remove(instance);
        instanceStatus.remove(instance);
    }

    public String getInstance(String documentId) {
        return consistentHashing.getInstance(documentId);
    }

    public void incrementLoad(String instance) {
        instanceLoad.get(instance).incrementAndGet();
    }

    public void decrementLoad(String instance) {
        instanceLoad.get(instance).decrementAndGet();
    }

    public List<String> listInstances() {
        return zookeeper.getChildren("/instances");
    }

    public int getLoad(String instance) {
        return instanceLoad.get(instance).get();
    }

    public void failInstance(String instance) {
        instanceStatus.put(instance, false);
        System.out.println("Instance " + instance + " failed.");
        redistributeDocuments(instance);
    }

    private void redistributeDocuments(String failedInstance) {
        List<String> documentsToRedistribute = new ArrayList<>(DocumentManager.getInstance().getDocumentsByInstance(failedInstance));
        for (String documentId : documentsToRedistribute) {
            DocumentManager.getInstance().releaseDocument(documentId);
            DocumentManager.getInstance().handleDocumentRequest(documentId);
        }
    }

    public boolean isInstanceActive(String instance) {
        return instanceStatus.getOrDefault(instance, false);
    }
}

