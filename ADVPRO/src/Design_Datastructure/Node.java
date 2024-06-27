package Design_Datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*  Now imagine, each node in the N-ry tree is a node in a distributed system.
Each node will have to implement two functions, send(id, message) and receive(id, message).
 Once all nodes run these functions, every node will be able to know how many nodes are present in the system.
 We need to implement those two functions by making some assumptions.*/
class Node {
    int id;
    Node parent;
    List<Node> children;
    int receivedCount;
    Map<Integer, Boolean> receivedFromChildren;

    public Node(int id) {
        this.id = id;
        this.children = new ArrayList<>();
        this.receivedFromChildren = new HashMap<>();
    }

    public void addChild(Node child) {
        child.parent = this;
        this.children.add(child);
        receivedFromChildren.put(child.id, false);
    }

    public void send(int count) {
        // Send the count to the parent
        System.out.println("id :"+this.id);
        if (this.parent != null) {
            this.parent.receive(this.id, count);
        }
    }

    public void receive(int senderId, int count) {
        System.out.println("sender :"+senderId+" "+count);
        receivedFromChildren.put(senderId, true);
        receivedCount += count;

        // Check if we have received messages from all children
        if (receivedFromChildren.values().stream().allMatch(Boolean::booleanValue)) {
            // Add own count
            receivedCount += 1;

            // If this node is the root, print the total count
            if (parent == null) {
                System.out.println("Total number of nodes: " + receivedCount);
            } else {
                // Otherwise, send the total count to the parent
                send(receivedCount);
            }
        }
    }

    public void startCounting() {
        System.out.println(this.id);
        // If the node is a leaf, it sends count directly to the parent
        if (children.isEmpty()) {
            send(1);
        } else {
            // Otherwise, start the counting process from all children
            for (Node child : children) {
                child.startCounting();
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        Node grandChild1 = new Node(5);
        Node grandChild2 = new Node(6);

        root.addChild(child1);
        root.addChild(child2);
        child1.addChild(child3);
        child3.addChild(grandChild1);
        child3.addChild(grandChild2);

        root.startCounting();
    }
}

/*class Node {
    int id;
    Node parent;
    List<Node> children;
    int receivedCount;
    boolean receivedFromParent;
    Map<Integer, Integer> receivedFromChildren;

    public Node(int id) {
        this.id = id;
        this.children = new ArrayList<>();
        this.receivedFromChildren = new HashMap<>();
    }

    public void addChild(Node child) {
        child.parent = this;
        this.children.add(child);
    }

    public void send(int senderId, int count) {
        // Send the count to the parent
        if (this.parent != null && this.parent.id != senderId) {
            this.parent.receive(this.id, count);
        }
        // Send the count to the children
        for (Node child : children) {
            if (child.id != senderId) {
                child.receive(this.id, count);
            }
        }
    }

    public void receive(int senderId, int count) {
        if (this.parent != null && this.parent.id == senderId) {
            receivedFromParent = true;
        } else {
            receivedFromChildren.put(senderId, count);
        }

        receivedCount += count;

        // Check if we have received messages from all children
        if (receivedFromChildren.size() == children.size()) {
            // If this node is the root and has received from all children, print the total count
            if (parent == null) {
                System.out.println("Total number of nodes: " + (receivedCount + 1)); // +1 for the current node
            } else if (receivedFromParent) {
                // If the current node is not the root and has received from all children and its parent, propagate the count to the parent
                parent.receive(this.id, receivedCount + 1); // +1 for the current node
            }
        }
    }

    public void startCounting() {
        // Start the counting process from the root
        if (children.isEmpty()) {
            if (parent != null) {
                parent.receive(this.id, 1); // Leaf node sends count to parent
            } else {
                System.out.println("Total number of nodes: 1"); // Only one node in the tree
            }
        } else {
            send(this.id, 1); // Send initial message to all children and parent
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        Node grandChild1 = new Node(5);
        Node grandChild2 = new Node(6);

        root.addChild(child1);
        root.addChild(child2);
        child1.addChild(child3);
        child3.addChild(grandChild1);
        child3.addChild(grandChild2);

        root.startCounting();
    }
}*/
