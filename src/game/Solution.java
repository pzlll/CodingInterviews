package game;

import java.util.*;

public class Solution {
    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int sum = 0;

        return sum;
    }

    public boolean checkZeroOnes(String s) {
        int max1 = 0;
        int max0 = 0;
        int i = 0;
        int j = 0;
        int n = s.length();
        while (j < n) {
            if (s.charAt(j) == '0') {
                j++;
                i = j;
            } else {
                max1 = Math.max(max1, (j - i + 1));
                j++;
            }
        }

        i = 0;
        j = 0;
        while (j < n) {
            if (s.charAt(j) == '1') {
                j++;
                i = j;
            } else {
                max0 = Math.max(max0, (j - i + 1));
                j++;
            }
        }
        if (max1 > max0) {
            return true;
        } else {
            return false;
        }
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;
        if (hour <= (n - 1)) {
            return -1;
        }
        int max = 0;
        for (int i = 0; i < (n - 1); i++) {
            max = Math.max(max, dist[i]);
        }
        max = Math.max(max, (int) Math.ceil(dist[n - 1] / (hour - n + 1)));

        int left = 1;
        int right = max;
        while (left < right) {
            int mid = (right + left) / 2;

            if (isOnTime(hour, mid, dist)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;


    }

    private boolean isOnTime(double hour, int mid, int[] dist) {
        double time = 0;
        for (int i = 0; i < (dist.length - 1); i++) {

            time += Math.ceil(((double) dist[i]) / mid);
        }

        time += ((double) dist[dist.length - 1] / mid);
        if (time > hour) {
            return false;
        } else {
            return true;
        }
    }

    public boolean canReach(String s, int minJump, int maxJump) {

        int n = s.length();
        int length = n;

        if (s.charAt(n - 1) == '1') {
            return false;
        }


        Stack<Integer> stack = new Stack<>();
        stack.push(n - 1);

        boolean[] res = new boolean[n];
        res[n - 1] = true;
        while (!stack.isEmpty() && length > 1) {
            Integer pop = stack.pop();

            if (length >= (pop - maxJump)) {
                for (int i = pop - minJump; i >= (pop - maxJump) && i >= 0; i--) {
                    if (s.charAt(i) == '0' && (!res[i])) {
                        stack.push(i);
                        res[i] = true;
                        length = Math.min(length, (i + 1));
                    }
                }
            }

        }
        return (length > 1) ? false : true;
    }

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int a = getValue(firstWord);
        int b = getValue(secondWord);
        int c = getValue(targetWord);
        return (a + b) == c;


    }

    private int getValue(String word) {
        int res = 0;
        for (int i = 0; i < word.length(); i++) {
            res = res * 10 + word.charAt(i) - 'a';
        }
        return res;
    }

    public String maxValue(String n, int x) {
        boolean isPositive = true;
        StringBuffer sb = new StringBuffer();
        if (n.charAt(0) == '-') {
            isPositive = false;
            sb.append('-');
        }

        if (isPositive) {
            int i = 0;
            while (i < n.length() && (n.charAt(i) > (x + '0'))) {
                sb.append(n.charAt(i));
                i++;
            }
            sb.append(x);
            while (i < n.length()) {
                sb.append(n.charAt(i));
                i++;
            }

        } else {
            int i = 1;
            while (i < n.length() && (n.charAt(i) < (x + '0'))) {
                sb.append(n.charAt(i));
                i++;
            }
            sb.append(x);
            while (i < n.length()) {
                sb.append(n.charAt(i));
                i++;
            }
        }

        return sb.toString();
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> freeQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int o = o1[1] - o2[1];
                return o == 0 ? o1[0] - o2[0] : o;
            }
        });
        PriorityQueue<int[]> servingQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int o = o1[0] - o2[0];
                return o == 0 ? o1[1] - o2[1] : o;
            }
        });

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];
        for (int i = 0; i < n; i++) {
            freeQueue.offer(new int[]{i, servers[i]});
        }

        int time = 0;

        for (int i = 0; i < m; i++) {
            // 保证：如果同一时刻存在多台空闲服务器，可以同时将多项任务分别分配给它们。
            time = Math.max(time, i);

            while (!servingQueue.isEmpty() && (servingQueue.peek()[0] <= time)) {
                int[] serve1 = servingQueue.poll();
                freeQueue.offer(new int[]{serve1[1], serve1[2]});
            }
            if (freeQueue.isEmpty()) {
                int[] serve = servingQueue.peek();
                time = serve[0];
                while (!servingQueue.isEmpty() && (servingQueue.peek()[0] <= time)) {
                    int[] serve1 = servingQueue.poll();
                    freeQueue.offer(new int[]{serve1[1], serve1[2]});
                }
            }
            int[] free = freeQueue.poll();
            ans[i] = free[0];
            servingQueue.offer(new int[]{time + tasks[i], free[0], free[1]});

        }


        return ans;
    }

}
