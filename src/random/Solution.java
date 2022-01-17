package random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Solution {

    private int index;
    private int[][] val;
    private Map<Integer, Integer> map;
    private int m;
    private int n;
    public Solution(int m, int n) {
        index = m * n;
        val = new int[m][n];
        map = new HashMap<>();
        this.m = m;
        this.n = n;
    }

    public int[] flip() {
        Random random = new Random();
        int i = random.nextInt(index);

        int a = index - 1;
        val[a/n][a%n] = 1;


        int[] ret;
        index--;
        if(!map.containsKey(i)) {
            map.put(i, map.getOrDefault(index, index));
            ret = new int[]{i/n, i%n};

        }else {
            int val = map.get(i);
            ret = new int[]{val/n, val%n};
            map.put(i, map.getOrDefault(index, index));
        }

        return ret;

    }

    public void reset() {
        index = m * n;
        val = new int[m][n];
        map = new HashMap<>();

    }
}
