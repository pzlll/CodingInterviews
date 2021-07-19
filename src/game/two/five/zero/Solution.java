package game.two.five.zero;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < brokenLetters.length(); i++) {
            set.add(brokenLetters.charAt(i));
        }

        String[] sArray = text.split(" ");

        int count = sArray.length;
        for (String s :
                sArray) {
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if(set.contains(c[i])) {
                    count--;
                    break;
                }
            }

        }

        return count;

    }

    public int addRungs(int[] rungs, int dist) {
        int count = 0;
        int n = rungs.length;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            count += Math.max(0, Math.ceil((rungs[i] - pre -dist)/(dist * 1.0)));
            pre = rungs[i];
        }

        return count;
    }

    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;

        long[] dp = new long[n];

        for (int i = 0; i < n; i++) {
            dp[i] = points[0][i];
        }

        for (int i = 1; i < m; i++) {
            long[] temp = new long[n];
            long[] large = new long[n];
            large[n-1] = -(n-1);

            long max = Long.MIN_VALUE;
            for (int j = (n-2); j >= 0; j--) {
                max = getMax(max, dp[j+1] - (j+1));
                large[j] = max;
            }
            max = Long.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                max = getMax(max, dp[j] + j);

                temp[j] = getMax(max + points[i][j] - j, large[j] + points[i][j] + j);



            }
            dp = temp;

        }

        long max = 0;
        for (int i = 0; i < n; i++) {
            max = getMax(max, dp[i]);
        }

        return max;
    }

    private long getMax(long a, long b) {
        return (a > b ? a : b);
    }
}
