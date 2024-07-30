package Design_Datastructure.consistenthashingwithzookeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ZookeeperSimulator {
    private final Map<String, String> nodes = new ConcurrentHashMap<>();

    public void registerNode(String path, String data) {
        nodes.put(path, data);
        System.out.println("Node registered: " + path);
    }

    public void deregisterNode(String path) {
        nodes.remove(path);
        System.out.println("Node deregistered: " + path);
    }

    public List<String> getChildren(String path) {
        List<String> children = new ArrayList<>();
        for (String node : nodes.keySet()) {
            if (node.startsWith(path)) {
                children.add(node);
            }
        }
        return children;
    }

    public boolean exists(String path) {
        return nodes.containsKey(path);
    }
}

