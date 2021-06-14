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

    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;

        if (isEquals(mat, target)) {
            return true;
        }

        change(mat);


        if (isEquals(mat, target)) {
            return true;
        }

        change(mat);

        if (isEquals(mat, target)) {
            return true;
        }

        change(mat);

        if (isEquals(mat, target)) {
            return true;
        }

        return false;

    }

    private void change(int[][] mat) {
        int n = mat.length;
        for (int i = 0; i < (n / 2); i++) {
            for (int j = 0; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[n - 1 - i][j];
                mat[n - 1 - i][j] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = (i + 1); j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
    }

    private boolean isEquals(int[][] mat, int[][] target) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public int reductionOperations(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, nums[i]);
        }

        Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int count = 0;
        int sum = 0;


        for (Integer i :
                set) {
            if (i == min) {
                break;
            }

            sum += map.get(i);

            count += sum;
        }


        return count;
    }


    /**
     * 解题思路：双倍字符串+滑动窗口
     * 方法：维护长度为n的窗口，计算该窗口内转变为两种类型字符串所需次数，取最小值
     * 不断移动窗口，取转变操作的最小值
     * 提示：1.若转变为0101...字符串需要k次，则转变为1010...字符串需要n-k次
     *      2.以0101...字符串为基准，则每次比较可转化为对应下标的值与字符串“01”中i%2位置的值比较
     *
     * @param s
     * @return
     */
    public int minFlips(String s) {
        int n = s.length();
        int min;


        int count = 0;
        String target = "01";
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != target.charAt(i % 2)) {
                count++;
            }
        }

        min = Math.min(count, n - count);

        s = s + s;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != target.charAt(i % 2)) {
                count--;
            }
            if (s.charAt(i + n) != target.charAt((i + n) % 2)) {
                count++;
            }
            min = Math.min(min, count);
            min = Math.min(min, n - count);
        }


        return min;

    }

    public boolean makeEqual(String[] words) {
        int[] count = new int[26];

        int n= words.length;
        for (int i = 0; i < n; i++) {
            int m = words[i].length();
            for (int j = 0; j < m; j++) {
                count[words[i].charAt(j) - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if((count[i] % n) != 0) {
                return false;
            }
        }

        return true;
    }

    public int maximumRemovals(String s, String p, int[] removable) {
        int n = removable.length;



        int left = 0;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;
            Set<Integer> set = new HashSet<>();
            StringBuffer str = new StringBuffer();
            for (int i = 0; i <= mid; i++) {
                set.add(removable[i]);
            }

            for (int j = 0; j < s.length(); j++) {
                if(!set.contains(j)) {
                    str.append(s.charAt(j));
                }
            }
            if(!isSum(str.toString(), p)) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;


    }

    private boolean isSum(String toString, String p) {
        int j = 0;
        for (int i = 0; i < toString.length() && j < p.length(); i++) {
            if(toString.charAt(i) == p.charAt(j)) {
                j++;
            }
        }

        return j == p.length();
    }

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int n = triplets.length;


        int a = 0;
        int b = 0;
        int c = 0;

        for (int i = 0; i < n; i++) {
            if(triplets[i][0] > target[0] || triplets[i][1] > target[1] || triplets[i][2] > target[2]) {
                continue;
            }
            a = Math.max(a, triplets[i][0]);
            b = Math.max(b, triplets[i][1]);
            c = Math.max(c, triplets[i][2]);
        }

        return a == target[0] && b == target[1] && c == target[2];
    }


}
