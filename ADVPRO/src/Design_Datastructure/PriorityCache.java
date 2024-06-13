package Design_Datastructure;

import java.util.HashMap;
import java.util.TreeMap;

class PriorityCache {
    private class Node {
        String key;
        String value;
        int priority;
        Node prev;
        Node next;

        public Node(String key, String value, int priority) {
            this.key = key;
            this.value = value;
            this.priority = priority;
        }
    }

    private int capacity;
    private HashMap<String, Node> map;
    private TreeMap<Integer, Node> priorityMap;
    private Node head, tail;

    public PriorityCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.priorityMap = new TreeMap<>();
        this.head = new Node(null, null, 0);
        this.tail = new Node(null, null, 0);
        head.next = tail;
        tail.prev = head;
    }

    public String get(String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    public void put(String key, String value, int priority) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            node.priority = priority;
            remove(node);
            add(node);
        } else {
            if (map.size() >= capacity) {
                Node leastPriorityNode = priorityMap.firstEntry().getValue();
                map.remove(leastPriorityNode.key);
                priorityMap.remove(leastPriorityNode.priority);
                remove(leastPriorityNode);
            }
            Node newNode = new Node(key, value, priority);
            map.put(key, newNode);
            priorityMap.put(priority, newNode);
            add(newNode);
        }
    }

    private void add(Node node) {
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;
        priorityMap.put(node.priority, node);
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        priorityMap.remove(node.priority);
    }
}
