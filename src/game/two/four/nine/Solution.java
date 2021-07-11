package game.two.four.nine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int m = n * 2;
        int[] ret = new int[m];
        for (int i = 0; i < n; i++) {
            ret[i] = nums[i];
            ret[i + n] = nums[i];
        }

        return ret;
    }

    public int countPalindromicSubsequence(String s) {
        HashSet<Character>[] set = new HashSet[26];
        for (int i = 0; i < 26; i++) {
            set[i] = new HashSet<>();
        }

        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            Integer j = map.get(c);
            if (j == null) {
                map.put(c, i);
            } else {
                for (int k = j + 1; k < i; k++) {
                    set[c - 'a'].add(s.charAt(k));

                }
                map.put(c, i - 1);
            }
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            count += set[i].size();
        }

        return count;
    }

    private int count = 0;


    public int colorTheGrid(int m, int n) {
        int[] graph = new int[m * n];
        makeColor(graph, 0, m, n);

        return count;
    }

    private void makeColor(int[] graph, int i, int m, int n) {
        if (i == (m * n - 1)) {
            if(goodColor(graph, -1, i, m, n)) {
                count = (count + 1) % 1000000007;
            }
            if(goodColor(graph, 0, i, m, n)) {
                count = (count + 1) % 1000000007;
            }
            if(goodColor(graph, 1, i, m, n)) {
                count = (count + 1) % 1000000007;
            }
            return;
        }
        if (goodColor(graph, -1, i, m, n)) {
            graph[i] = -1;
            makeColor(graph, i + 1, m, n);
        }

        if (goodColor(graph, 0, i, m, n)) {
            graph[i] = -1;
            makeColor(graph, i + 1, m, n);
        }

        if (goodColor(graph, 1, i, m, n)) {
            graph[i] = -1;
            makeColor(graph, i + 1, m, n);
        }

    }

    private boolean goodColor(int[] graph, int color, int i, int m, int n) {
        if ((i % n != 0) && graph[i - 1] == color) {
            return false;
        }

        if ((i / n != 0) && graph[i - n] == color) {
            return false;
        }

        return true;
    }
}
