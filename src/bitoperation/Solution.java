package bitoperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int NumberOf1(int n) {
        int num = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                num++;
            }
            n = n >>> 1;

            System.out.println(n);
        }

        return num;
    }

    public static void main(String[] args) {
        int n = 16;
        int i = new Solution().NumberOf1(-1);
        System.out.println(i);

    }

    public int countTriplets(int[] arr) {
        int Xor = 0;
        int count = 0;
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] ^ arr[i];
        }

        Map<Integer, Integer> cnt = new HashMap<>();
        Map<Integer, Integer> sum = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (cnt.containsKey(s[i + 1])) {
                count = count + cnt.get(s[i + 1]) * i - sum.get(s[i + 1]);

            }
            cnt.put(s[i], cnt.getOrDefault(s[i], 0) + 1);
            sum.put(s[i], sum.getOrDefault(s[i], 0) + i);
        }

        return count;

    }
}
