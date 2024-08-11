package Design_Datastructure.graph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DisJointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();

    DisJointSet(int n) {
        for (int i = 0; i < n; i++) {
            rank.add(0);
            size.add(1);
            parent.add(i);
        }
    }

    int findUPar(int node) {
        if (parent.get(node) == node) return node;
        int ulpar = findUPar(parent.get(node));
        parent.set(node, ulpar);
        return parent.get(node);
    }

    void unionByRank(int x, int y) {
        int ulx = findUPar(x);
        int uly = findUPar(y);
        if (ulx == uly) return;
        if (rank.get(ulx) < rank.get(uly))
            parent.set(ulx, uly);
        else if (rank.get(uly) < rank.get(ulx))
            parent.set(uly, ulx);
        else {
            parent.set(uly, ulx);
            int rankU = rank.get(x);
            rank.set(ulx, rankU + 1);
        }
    }

    void unionBySize(int x, int y) {
        int ulx = findUPar(x);
        int uly = findUPar(y);
        if (ulx == uly) return;
        if (size.get(ulx) < size.get(uly)) {
            parent.set(ulx, uly);
            size.set(uly, size.get(ulx) + size.get(uly));
        } else {
            parent.set(uly, ulx);
            size.set(ulx, size.get(ulx) + size.get(uly));
        }
    }
}

class KruskalAlgorithmMST {
    static int spanningTree(int V, int E, int[][] edges) {
        List<int[]> adj = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];
            adj.add(new int[]{u, v, wt});
        }
        Collections.sort(adj, (a, b) -> a[2] - b[2]);
        DisJointSet ds = new DisJointSet(V);
        int sum = 0;
        for (int[] e : adj) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];
            if (ds.findUPar(u) != ds.findUPar(v)) {
                sum += wt;
                ds.unionBySize(u, v);
            }
        }
        return sum;

    }
}
