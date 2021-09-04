package simulation;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int getMaximumGenerated(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        int max = 1;
        for (int i = 2; i <= n; i++) {
            if(i % 2 == 0) {
                dp[i] = dp[i/2];
            }else {
                dp[i] = dp[i/2] + dp[i/2 + 1];
            }
            max = Math.max(max, dp[i]);

        }



        return max;
    }

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] pair :
                pairs) {
            map.put(pair[0], pair[1]);
            map.put(pair[1], pair[0]);
        }
        
        int[][] rank = new int[n][n];

        for (int i = 0; i < preferences.length; i++) {
            for (int j = 0; j < preferences[i].length; j++) {
                rank[i][preferences[i][j]] = n - j;
            }
        }

        boolean[] visited = new boolean[n];
        int unhappy = 0;

        for (int i = 0; i < n; i++) {
            if(!visited[i]) {
                int y = map.get(i);
                for (int j = 0; (y != preferences[i][j]) ; j++) {
                    int u = preferences[i][j];
                    int v = map.get(u);
                    if(rank[u][i] > rank[u][v]) {
                        unhappy++;
                        break;
                    }
                }

            }

        }
        
        return unhappy;
    }
}
