import java.util.*;

class BusRoute {
    String start;
    String end;
    double distance;

    public BusRoute(String start, String end, double distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

 class Main {
    public static void main(String[] args) {
        // List of bus routes
        List<BusRoute> busRoutes = new ArrayList<>();
        busRoutes.add(new BusRoute("StopA", "StopB", 5.0));
        busRoutes.add(new BusRoute("StopB", "StopC", 10.0));
        busRoutes.add(new BusRoute("StopC", "StopD", 6.0));
        busRoutes.add(new BusRoute("StopD", "StopA", 4.0));

        // Create a graph using adjacency lists
        Map<String, List<Pair<String, Double>>> graph = new HashMap<>();

        for (BusRoute busRoute : busRoutes) {
            String start = busRoute.start;
            String end = busRoute.end;
            double distance = busRoute.distance;
            if (!graph.containsKey(start)) {
                graph.put(start, new ArrayList<>());
            }
            graph.get(start).add(new Pair<>(end, distance));

            // Add the reverse edge from end to start for bus travel in reverse direction
            if (!graph.containsKey(end)) {
                graph.put(end, new ArrayList<>());
            }
            graph.get(end).add(new Pair<>(start, distance));
        }

        // Find the shortest route between two nodes (bus stops) using Priority Queue
        String startNode = "StopA";
        String endNode = "StopC";

        Map<String, Double> distanceMap = new HashMap<>();
        Map<String, String> previousNodeMap = new HashMap<>();
        PriorityQueue<Pair<String, Double>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Pair::getValue));

        // Initialize distances
        for (String node : graph.keySet()) {
            distanceMap.put(node, Double.MAX_VALUE);
        }
        distanceMap.put(startNode, 0.0);
        priorityQueue.add(new Pair<>(startNode, 0.0));

        while (!priorityQueue.isEmpty()) {
            Pair<String, Double> currentPair = priorityQueue.poll();
            String currentNode = currentPair.getKey();
            double currentDistance = currentPair.getValue();

            if (currentDistance > distanceMap.get(currentNode)) {
                continue; // Skip if we've found a shorter path to this node
            }

            for (Pair<String, Double> neighbor : graph.get(currentNode)) {
                String neighborNode = neighbor.getKey();
                double distance = neighbor.getValue();
                double newDistance = currentDistance + distance;

                if (newDistance < distanceMap.get(neighborNode)) {
                    distanceMap.put(neighborNode, newDistance);
                    previousNodeMap.put(neighborNode, currentNode);
                    priorityQueue.add(new Pair<>(neighborNode, newDistance));
                }
            }
        }

        // Reconstruct the shortest path
        List<String> shortestPath = new ArrayList<>();
        String currentNode = endNode;
        while (previousNodeMap.containsKey(currentNode)) {
            shortestPath.add(currentNode);
            currentNode = previousNodeMap.get(currentNode);
        }
        shortestPath.add(startNode);
        Collections.reverse(shortestPath);

        // Print the shortest path and its distance
        System.out.println("Shortest Path from " + startNode + " to " + endNode + ": " + shortestPath);
        System.out.println("Distance: " + distanceMap.get(endNode));
    }
}
