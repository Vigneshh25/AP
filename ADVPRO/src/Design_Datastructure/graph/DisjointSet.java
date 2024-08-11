package Design_Datastructure.graph;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();

    DisjointSet(int n) {
        for (int i = 0; i < n; i++) {
            rank.add(0);
            size.add(1);
            parent.add(i);
        }
    }

    public int findUPar(int node) {
        if (parent.get(node) == node) return node;
        int ulpar = findUPar(parent.get(node));
        parent.set(node, ulpar);
        return parent.get(node);
    }

    public void unionByRank(int x, int y) {
        int ulx = findUPar(x);
        int uly = findUPar(y);
        if (ulx == uly) return;
        if (rank.get(ulx) < rank.get(uly)) parent.set(ulx, uly);
        else if (rank.get(uly) < rank.get(ulx)) parent.set(uly, ulx);
        else {
            parent.set(uly, ulx);
            int rankU = rank.get(uly);
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

class Solution {
    public int makeConnected(int n, int[][] connections) {
        DisjointSet ds = new DisjointSet(n);
        int extraEdj = 0;
        for (int[] edj : connections) {
            int u = edj[0];
            int v = edj[1];
            if (ds.findUPar(u) == ds.findUPar(v)) extraEdj++;
            else ds.unionByRank(u, v);
        }
        int cntC = 0;
        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) cntC++;
        }
        int ans = cntC - 1;
        if (extraEdj >= ans) return ans;
        return -1;
    }
}
