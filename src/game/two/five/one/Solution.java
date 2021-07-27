package game.two.five.one;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public int getLucky(String s, int k) {
        char[] ch = s.toCharArray();

        int sum = 0;
        boolean flag = false;
        for (int i = 0; i < ch.length; i++) {
            int count = (ch[i] - 'a' + 1);
            if(count >= 10) {
                sum += count % 10;
                sum += (count / 10) % 10;
            }else {
                sum += count;
            }

        }

        System.out.println(sum);
        for (int i = 0; i < k; i++) {
            if(sum < 10) {
                return (int)sum;
            }

            int temp = sum;
            sum = 0;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }

        }

        return sum;

    }

    public String maximumNumber(String num, int[] change) {
        char[] ch = num.toCharArray();

        StringBuffer str = new StringBuffer();

        int n = ch.length;

        int i = 0;
        while (i < n) {
            int k = (ch[i] - '0');
            if(k < change[k]) {
                str.append(change[k]);
                break;
            }else {
                str.append(k);
            }
            i++;
        }

        int j = i + 1;
        while (i < n && j < n) {
            int k = (ch[j] - '0');
            if(k < change[k]) {
                str.append(change[k]);
                j++;
            }else {
                str.append(k);
                j++;
                break;
            }

        }

        while (j < n) {
            str.append((ch[j] - '0'));
            j++;
        }



        return str.toString();
    }

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int n = students.length;
        int m = students[0].length;

        int[][] score = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    score[i][j] += (students[i][k] == mentors[j][k] ? 1 : 0);
                }
            }
        }

        boolean[] v = new boolean[n];
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> sort = new ArrayList<>();
        getList(v, n, list,sort);
    }

    private void getList(boolean[] v, int n, List<Integer> list, List<List<Integer>> sort) {
        if(list.size() == n) {
            sort.add(Collections.copy();)
        }
    }
}
