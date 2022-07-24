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
     * 2.以0101...字符串为基准，则每次比较可转化为对应下标的值与字符串“01”中i%2位置的值比较
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

        int n = words.length;
        for (int i = 0; i < n; i++) {
            int m = words[i].length();
            for (int j = 0; j < m; j++) {
                count[words[i].charAt(j) - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if ((count[i] % n) != 0) {
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
                if (!set.contains(j)) {
                    str.append(s.charAt(j));
                }
            }
            if (!isSum(str.toString(), p)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;


    }

    private boolean isSum(String toString, String p) {
        int j = 0;
        for (int i = 0; i < toString.length() && j < p.length(); i++) {
            if (toString.charAt(i) == p.charAt(j)) {
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
            if (triplets[i][0] > target[0] || triplets[i][1] > target[1] || triplets[i][2] > target[2]) {
                continue;
            }
            a = Math.max(a, triplets[i][0]);
            b = Math.max(b, triplets[i][1]);
            c = Math.max(c, triplets[i][2]);
        }

        return a == target[0] && b == target[1] && c == target[2];
    }

    public String largestOddNumber(String num) {
        int n = num.length();
        boolean flag = false;
        int start = 0;
        int end = 0;

        int i = 0, j = 0, k;
        while (i < n) {
            int a;
            a = num.charAt(i) - '0';
            if (a % 2 == 1) {
                j = i + 1;
                flag = true;
            }
            i++;
        }

        end = j;


        return flag ? num.substring(0, end) : "";

    }

    public int numberOfRounds(String startTime, String finishTime) {
        String h1 = startTime.substring(0, 2);
        String m1 = startTime.substring(3, 5);
        String h2 = finishTime.substring(0, 2);
        String m2 = finishTime.substring(3, 5);

        int count = 0;

        int ih1 = getNum(h1);
        int ih2 = getNum(h2);
        int im1 = getNum(m1);
        int im2 = getNum(m2);

        int count1, count2;

        if (ih1 < ih2 || (ih1 == ih2 && im1 < im2)) {
            count1 = ih1 * 4 + getMaxCount(im1);
            count2 = ih2 * 4 + getMinCount(im2);
            count = count2 - count1;
        } else {
            count1 = 24 * 4 - (ih1 * 4 + getMaxCount(im1));
            count2 = ih2 * 4 + getMinCount(im2);
            count = count1 + count2;
        }


        return count;
    }


    private int getMaxCount(int time) {
        if (time > 0 && time <= 15) {
            return 1;
        } else if (time > 15 && time <= 30) {
            return 2;
        } else if (time > 30 && time <= 45) {
            return 3;
        } else if (time > 45 && time < 60) {
            return 4;
        } else if (time == 0) {
            return 0;
        }

        return -1;
    }

    private int getMinCount(int time) {
        if (time >= 0 && time < 15) {
            return 0;
        } else if (time >= 15 && time < 30) {
            return 1;
        } else if (time >= 30 && time < 45) {
            return 2;
        } else if (time >= 45 && time < 60) {
            return 3;
        }

        return -1;
    }

    private int getNum(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum = sum * 10 + (s.charAt(i) - '0');
        }

        return sum;
    }

    /**
     * 解题思路：遍历grid2所有岛屿，对于其中一个岛屿，只要该岛屿其中一格在grid1为空，则排除改岛屿（但仍然遍历完该岛屿）
     * 具体实现：
     * 使用二维数组visited存储grid2节点是否被标记
     * 遍历二维数组，若该节点在grid2不为零且未被标记，则对其进行深度优先搜索
     * 深度优先搜索：
     * 标记该节点为已被访问，设置flag标志，若该节点在grid1中为零，则改变flag
     * 访问其四个方向，先判断对应坐标是否越界，在grid2中是否为零以及是否被访问过，若都通过，则对其进行深度优先搜索，如果该搜索改变flag的值，将其传回
     *
     * @param grid1
     * @param grid2
     * @return
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid1.length;
        int m = grid1[0].length;


        boolean[][] visited = new boolean[n][m];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 0) {
                    continue;
                }
                if (!visited[i][j]) {
                    if (dfsForIsland(grid1, grid2, i, j, visited)) {
                        ans++;
                    }
                }

            }
        }

        return ans;

    }

    private boolean dfsForIsland(int[][] grid1, int[][] grid2, int i, int j, boolean[][] v) {
        int n = grid1.length;
        int m = grid1[0].length;
        int[][] direct = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        boolean flag = true;
        v[i][j] = true;
        if (grid1[i][j] == 0) {
            flag = false;
        }

        for (int k = 0; k < 4; k++) {
            int ni = i + direct[k][0];
            int nj = j + direct[k][1];
            if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                continue;
            }
            if (grid2[ni][nj] == 0) {
                continue;
            }
            if (!v[ni][nj]) {
                if (!dfsForIsland(grid1, grid2, ni, nj, v)) {
                    flag = false;
                }
            }
        }

        return flag;

    }


    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        int a = nums[0] * nums[1];
        int n = nums.length;
        int b = nums[n - 2] * nums[n - 1];
        return (b - a);
    }

    /**
     * 解题思路：
     * 层数是两个维度m和n它们除以二取最小值
     * 使用三个list存储每一层数对应的行、列和数值
     * 对于该层的每一个位置：
     * 轮转k次后，（三个list中）位置i的数值变成（i - k + total）% total位置的值
     * 可通过数值list找到
     *
     * @param grid
     * @param k
     * @return
     */
    public int[][] rotateGrid(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int nlayer = Math.min(n / 2, m / 2);
        for (int i = 0; i < nlayer; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> col = new ArrayList<>();
            List<Integer> val = new ArrayList<>();
            for (int j = i; j < n - i - 1; j++) {
                row.add(j);
                col.add(i);
                val.add(grid[j][i]);
            }

            for (int j = i; j < m - i - 1; j++) {
                row.add(n - i - 1);
                col.add(j);
                val.add(grid[n - i][j]);
            }

            for (int j = n - i - 1; j > i; j--) {
                row.add(j);
                col.add(m - i - 1);
                val.add(grid[j][m - i - 1]);
            }

            for (int j = m - 1 - i; j > i; j--) {
                row.add(i);
                col.add(j);
                val.add(grid[i][j]);
            }

            int total = row.size();

            int kk = k % total;

            for (int j = 0; j < total; j++) {
                int index = (j - kk + total) % total;
                grid[row.get(j)][col.get(j)] = val.get(index);
            }

        }

        return grid;
    }

    /**
     * 解题思路：状态压缩
     * 若word(i, j)为最美字符串，则前缀（0, i-1）和前缀（0, j）里面字符奇偶性最多不超过1
     * 使用mask作为一个状态，总共10位，每一位代表特定字符的奇偶性
     * 使用哈系表存储对应状态以及它的频率
     * 遍历字符串每一位，更新mask，查找哈希表中与mask相差不多于1的状态，加入累加值
     *
     * @param word
     * @return
     */
    public long wonderfulSubstrings(String word) {
        int n = word.length();

        Map<Integer, Long> freq = new HashMap<>();

        freq.put(0, (long)1);

        int mask = 0;
        long ret = 0;
        for (int i = 0; i < n; i++) {
            int c = word.charAt(i) - 'a';
            mask = mask ^ (1 << c);
            for (int j = 0; j < 10; j++) {
                int match = mask ^ (1 << j);
                if (freq.get(match) != null) {
                    ret += freq.get(match);
                }
            }
            if (freq.get(mask) != null) {
                ret += freq.get(mask);
            }
            freq.put(mask, freq.getOrDefault(mask, (long) 0) + 1);
        }

        return ret;
    }

    public int[] buildArray(int[] nums) {
        int n = nums.length;

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = nums[nums[i]];
        }

        return ans;
    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] time = new int[n];

        for (int i = 0; i < n; i++) {
            time[i] = (int)Math.ceil(dist[i]/(double)speed[i]);
        }

        Arrays.sort(time);

        int t = 0;
        int j = 0;
        while (j <= n && time[j] <= t) {
            j++;
        }

        while (j <= t) {
            if(j == n) {
                return n;
            }

            t++;
            while (j <= n && time[j] <= t) {
                j++;
            }
        }

        return t;
    }

    public int countGoodNumbers(long n) {
        long ans = 1;
        long t = 1000000007;

        int m = 0;
        long temp = n;
        while (temp > 0) {
            temp = temp >> 1;
            m++;
        }

        m = Math.max(2, m);

        long[] dp = new long[m];

        dp[0] = 5;
        dp[1] = 5 * 4;
        for (int i = 2; i < m; i++) {
            dp[i] = (dp[i -1] * dp[i-1]) % t;
        }

//        for (int i = 1; i <= n; i++) {
//            if(i % 2 == 1) {
//                ans = (ans * 5) % t;
//            }else {
//                ans = (ans * 4) % t;
//            }
//
//        }

        int i = 0;
        while (n > 0) {
            if((n & 1) == 1) {
                ans *= dp[i];
                ans %= t;
            }
            i++;
            n >>= 1;
        }

        return (int)ans;
    }

    //第 303 场周赛
    public char repeatedCharacter(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(set.contains(c)) {
                return c;
            }
            set.add(c);
        }

        return 'a';
    }

    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean flag = true;
                for (int k = 0; k < n; k++) {
                    if(grid[i][k] != grid[k][j]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    count++;
                }
            }
        }

        return count;
    }



}
