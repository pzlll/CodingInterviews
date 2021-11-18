package bitoperation;

import java.util.*;

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


    /**
     * 解题思路：使用前缀和存储对应下标的前缀和
     * 将题目转化为与下标k前缀和相同以及下标值在它之前的所有下标
     * 使用两个哈系表减少查找时间复杂度
     * @param arr
     * @return
     */
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

    /**
     * 解题思路：使用前缀和存储对应下标的前缀异或和
     * 遍历数组，找出第K大的前缀和（使用快速排序或堆排序）
     * @param matrix
     * @param k
     * @return
     */
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] Xor = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                Xor[i][j] = Xor[i-1][j] ^ Xor[i][j-1] ^ Xor[i-1][j-1] ^ matrix[i-1][j-1];
            }
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = Xor[i+1][j+1];
                priorityQueue.offer(val);
                if(priorityQueue.size() > k) {
                    priorityQueue.poll();
                }
            }
        }

        return priorityQueue.peek();
    }

    public int maxProduct(String[] words) {
        int n = words.length;
        int[] bits = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                int k = words[i].charAt(j) - 'a';
                bits[i] |= (1 << k);
            }
        }

        int length = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if((bits[i] & bits[j]) == 0) {
                    length = Math.max(length, words[i].length() * words[j].length());
                }
            }
        }

        return length;
    }
}
