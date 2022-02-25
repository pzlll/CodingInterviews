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

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length;
        int time = 0;
        for (int i = 0; i < n - 1; i++) {
            time += Math.min(duration, timeSeries[i+1] - timeSeries[i]);
        }
        time += duration;

        return time;
    }

    public String complexNumberMultiply(String num1, String num2) {
        String[] s1 = num1.split("\\+");

        String[] s2 = num2.split("\\+");

        int a = stringToInteger(s1[0]);

        int b = stringToInteger(s1[1].substring(0, s1[1].length() - 1));

        int c = stringToInteger(s2[0]);
        int d = stringToInteger(s2[1].substring(0, s1[1].length() - 1));

        int r1 = a * c - b * d;
        int r2 = b * c + a * d;

        StringBuffer str = new StringBuffer();
        str.append(r1);
        str.append('+');
        str.append(r2);
        str.append('i');

        return new String(str);


    }

    private int stringToInteger(String s) {
        boolean flag = false;
        int i = 0;
        int n = s.length();
        if(s.charAt(i) == '-') {
            flag = true;
            i++;
        }

        int sum = 0;

        while (i < n) {
            sum = sum * 10 + s.charAt(i) - '0';
            i++;
        }

        return flag ? -sum : sum;

    }
}
