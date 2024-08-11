package Design_Datastructure.graph;
import java.util.ArrayList;
import java.util.PriorityQueue;

class PrimsAlgorithmMST {
    static int spanningTree(int V, int E, int[][] edges) {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        for (int i = 0; i < E; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] visited = new int[V];
        pq.add(new int[]{0, 0});
        int weight = 0;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int dist = arr[0];
            int node = arr[1];
            if (visited[node] == 1) continue;
            weight += dist;
            visited[node] = 1;
            for (int[] edge : adj.get(node)) {
                int adjNode = edge[0];
                int wt = edge[1];
                if (visited[adjNode] == 0) {
                    pq.add(new int[]{wt, adjNode});
                }
            }
        }
        return weight;
    }
}