package Design_Datastructure.consitenthashing;

import distributedcache.HashFunction;
import distributedcache.JavaHashFunction;
import distributedcache.MD5HashFunction;

public class Main {
    public static void main(String[] args) {
        HashFunction hashFunction = new JavaHashFunction();
        ConsistentHashing<String> consistentHash = new ConsistentHashing<>(hashFunction, 3);

        consistentHash.addNode("NodeA");
        consistentHash.addNode("NodeB");
        consistentHash.addNode("NodeC");

        System.out.println("Circle before adding new node:");
        consistentHash.printCircle();

        String[] keys = {"Key1", "Key2", "Key3", "Key4", "Key5"};

        for (String key : keys) {
            System.out.println(key + " is mapped to " + consistentHash.getNode(key));
        }

        // Adding a new node
        consistentHash.addNode("NodeD");

        System.out.println("\nCircle after adding new node:");
        consistentHash.printCircle();

        for (String key : keys) {
            System.out.println(key + " is mapped to " + consistentHash.getNode(key));
        }

        // Removing a node
        consistentHash.removeNode("NodeB");

        System.out.println("\nCircle after removing NodeB:");
        consistentHash.printCircle();

        for (String key : keys) {
            System.out.println(key + " is mapped to " + consistentHash.getNode(key));
        }
    }
}
