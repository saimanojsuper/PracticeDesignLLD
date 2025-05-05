package org.practice.dsatuf.UnionFind;

public class UnionFind {
  class Solution {
    int[] parent;
    int[] rank;

    public int[] findRedundantConnection(int[][] edges) {

      int n = edges.length;
      parent = new int[n + 1];
      rank = new int[n + 1];

      for (int i = 1; i <= n; i++) {
        parent[i] = i;
        rank[i] = 0;
      }

      int[] res = new int[2];

      for (int i = 0; i < n; i++) {
        int j = edges[i][0];
        int k = edges[i][1];

        int rootj = findParent(j);
        int rootk = findParent(k);

        if (rootj != rootk) {

          if (rank[rootj] > rank[rootk]) {
            parent[rootk] = rootj;
          } else if (rank[j] < rank[k]) {
            parent[rootj] = rootk;
          } else {
            parent[rootk] = rootj;
            rank[rootj]++;
          }

        } else {
          res[0] = j;
          res[1] = k;
        }
      }

      return res;
    }

    public int findParent(int i) {
      return parent[i] == i ? i : findParent(parent[i]);
    }
  }
}
