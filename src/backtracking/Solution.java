package backtracking;

import java.util.*;

public class Solution {
    /**
     * 判断当前字符与单词对应位置是否匹配
     * 匹配：1.最后一个字符则返回true，2.还有其他字符则遍历四个方向（未被访问的）
     * 不匹配，返回false
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        if (word == null || word.length() == 0) {
            return true;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                boolean flag = check(i, j, 0, board, word, visited);
                if (flag) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean check(int i, int j, int k, char[][] board, String word, boolean[][] visited) {
        if (k == word.length() - 1 && board[i][j] == word.charAt(k)) {
            return true;
        }
        if (board[i][j] != word.charAt(k)) {
            return false;
        } else {
            boolean flag = false;
            visited[i][j] = true;
            if (i > 0 && !visited[i - 1][j]) {
                flag = check(i - 1, j, k + 1, board, word, visited);
                if (flag) {
                    return true;
                }
            }
            if (i < board.length - 1 && !visited[i + 1][j]) {
                flag = check(i + 1, j, k + 1, board, word, visited);
                if (flag) {
                    return true;
                }
            }
            if (j > 0 && !visited[i][j - 1]) {
                flag = check(i, j - 1, k + 1, board, word, visited);
                if (flag) {
                    return true;
                }
            }
            if (j < board[0].length - 1 && !visited[i][j + 1]) {
                flag = check(i, j + 1, k + 1, board, word, visited);
                if (flag) {
                    return true;
                }
            }
        }

        visited[i][j] = false;
        return false;

    }

    public List<String> findWords(char[][] board, String[] words) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        List<String> list = new ArrayList<>();
        for (String word :
                words) {
            if (word == null || word.length() == 0) {
                continue;
            }
            for (int i = 0; i < visited.length; i++) {
                Arrays.fill(visited[i], false);
            }

            boolean flag = false;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    flag = check(i, j, 0, board, word, visited);
                    if (flag) {
                        list.add(word);
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
        }

        return list;

    }

    private List<Integer> t = new ArrayList<>();
    private List<List<Integer>> array = new ArrayList<>();

    /**
     * 解题思路：对数组进行排序，n个元素，则有2的n次方种组合
     * 若前一元素与当前元素相同，且前一元素不选择，则放弃当前组合
     * 目的是为了避免重复，使得元素{1，1，1}的组合为{{}， {1}， {1，1}， {1，1，1}}
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
////        dfs(false, 0, nums);
        int n = nums.length;
        boolean flag = true;
        for (int mask = 0; mask < (1 << n); mask++) {
            t.clear();
            flag = true;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    if (i > 0 && ((mask & (1 << (i - 1))) == 0) && nums[i - 1] == nums[i]) {
                        flag = false;
                        break;
                    }
                    t.add(nums[i]);
                }
            }
            if (flag) {
                array.add(new ArrayList<>(t));

            }

        }


        return array;
    }

    private void dfs(boolean flag, int cur, int[] nums) {
        if (cur == nums.length) {
            array.add(new ArrayList<>(t));
            return;
        }

        dfs(false, cur + 1, nums);
        if (!flag && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        t.add(nums[cur]);
        dfs(true, cur + 1, nums);
        t.remove(t.size() - 1);
    }

    public int minimumTimeRequired(int[] jobs, int k) {
        int min = 0;
        int max = 0;
        int n = jobs.length;
        for (int i = 0; i < n; i++) {
            min = Math.max(min, jobs[i]);
            max += jobs[i];
        }


        while (min < max) {
            int[] workVol = new int[k];
            int mid = (max + min) / 2;
            if (backTracking(workVol, mid, 0, jobs)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;


    }

    private boolean backTracking(int[] workVol, int limit, int i, int[] jobs) {
        if (i == jobs.length) {
            return true;
        }
        for (int j = 0; j < workVol.length; j++) {
            if ((workVol[j] + jobs[i]) <= limit) {
                workVol[j] += jobs[i];
                if (backTracking(workVol, limit, i + 1, jobs)) {
                    return true;
                } else if (workVol[j] == limit) {
                    workVol[j] -= jobs[i];
                    return false;
                }
                workVol[j] -= jobs[i];
            }
            if (workVol[j] == 0) {
                return false;
            }
        }

        return false;
    }

    /**
     * 解题思路：类似求解最长公共子序列问题
     * 使用dp数组存储对应下标i，j的最长子序列个数
     * dp[i][j]:数组nums1下标i之前的元素和数组nums2下标j之前的元素的最长公共序列
     * 若nums1[i] == nums2[j]，dp[i][j] = dp[i-1][j-1] + 1
     * 否则，从dp[i-1][j]和dp[i][j-1]中选出最大值
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }

        }
        return dp[n][m];
    }

    /**
     * 解题思路：回溯法
     */
    private int max = 0;

    public int maxLength(List<String> arr) {
        int n = arr.size();

        List<Integer> pos = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String s = arr.get(i);
            int mask = 0;
            for (int j = 0; j < s.length(); j++) {
                if (((mask >> (s.charAt(j) - 'a')) & 1) != 0) {
                    mask = 0;
                    break;
                }
                mask |= (1 << (s.charAt(j) - 'a'));
            }
            if (mask > 0) {
                pos.add(mask);
            }
        }

        int mask = 0;
        trackArray(pos, 0, mask);

        return max;
    }

    private void trackArray(List<Integer> pos, int i, int mask) {
        if (i == pos.size()) {
            int count = 0;
            while (mask > 0) {
                if (mask % 2 == 1) {
                    count++;
                }
                mask /= 2;
            }
            max = Math.max(max, count);
            return;
        }

        if ((mask & pos.get(i)) == 0) {
            trackArray(pos, i + 1, mask | pos.get(i));
        }

        trackArray(pos, i + 1, mask);

    }

    /**
     * 解题思路：
     * 1.枚举时分
     * 枚举小时数 0-11和分钟数0-59，分别计算他们包含二进制1的个数，若两数之和等于turnedOn,则找到其中一个答案
     * 2.二进制枚举
     * 总共有2^10种结果，对于每一个结果，取其前四位为小时数，后六位为分钟数
     * 若小时和分钟取值合法且对应结果的二进制1个数等于turnedOn,则找到其中一个答案
     *
     * @param turnedOn
     * @return
     */
    public List<String> readBinaryWatch(int turnedOn) {
        int[] time = {8, 4, 2, 1, 32, 16, 8, 4, 2, 1};

        List<String> ans = new ArrayList<>();

        for (int i = 0; i < 1024; i++) {
            int h = i >> 6;
            int m = i & 63;
            if (h < 12 && m < 60 && Integer.bitCount(i) == turnedOn) {
                ans.add(h + (m < 10 ? "0" : "") + m);
            }
        }

        int hour = 0;
        int mimute = 0;

        backTrackBinaryWatch(turnedOn, time, 0, ans, 0, 0);

        return ans;
    }

    private void backTrackBinaryWatch(int num, int[] time, int i, List<String> ans, int hour, int minute) {
        if (num == 0) {
            if (hour <= 12 && minute <= 59) {
                StringBuffer str = new StringBuffer();
                str.append(hour);

                str.append(":");

                if (minute < 10) {
                    str.append(0);
                }

                str.append(minute);
                ans.add(str.toString());
            }
            return;
        }


        if (i == time.length) {
            return;
        }

        if (i <= 3) {
            backTrackBinaryWatch((num - 1), time, (i + 1), ans, (hour + time[i]), minute);
        } else {
            backTrackBinaryWatch((num - 1), time, (i + 1), ans, hour, minute + time[i]);
        }

        backTrackBinaryWatch(num, time, i + 1, ans, hour, minute);
    }

    /**
     * 解题思路：
     * 回溯法查找全排列
     * 在查找过程中，先对字符串排序，并且保证每次选择字符，是重复字符集合中从左到右第一个为未被插入的字符（有序性）
     * 即可保证重复字符集合只被填入一次
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {


        int n = s.length();

        boolean[] visited = new boolean[n];

        List<String> ans = new ArrayList<>();
        StringBuffer str = new StringBuffer();

        char[] chars = s.toCharArray();


        Arrays.sort(chars);

        backTrackString(chars, visited, str, ans);

        String[] ss = new String[ans.size()];
        int i = 0;
        for (String sb :
                ans) {
            ss[i] = sb;
            i++;
        }


        return ss;
    }

    private void backTrackString(char[] s, boolean[] v, StringBuffer str, List<String> ans) {
        if (str.length() == s.length) {
            ans.add(str.toString());
            return;
        }


        for (int i = 0; i < s.length; i++) {
            if (!v[i]) {
                if (i > 0 && !v[i - 1] && (s[i] == s[i - 1])) {
                    continue;
                }
                str.append(s[i]);
                v[i] = true;
                backTrackString(s, v, str, ans);
                str.deleteCharAt(str.length() - 1);
                v[i] = false;
            }
        }
    }

    /**
     * 解题思路：广度优先搜索，到指定位置需遍历的层数（存在返回层数，不存在返回-1）
     * 对应位置若其board值不为-1,则可跳转到另一指定位置
     * 两个转换函数：toVal、toIndex
     *
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        boolean[] visited = new boolean[n * n];

        int index = (n - 1) * n;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(index);
        visited[n * (n - 1)] = true;
        int count = 1;

        while (!queue.isEmpty()) {

            int m = queue.size();
            for (int i = 0; i < m; i++) {
                Integer p = queue.poll();
                Integer t = toVal(p, n);

                for (int j = 1; j <= 6; j++) {
                    int s = t + j;

                    index = toIndex(s, n);

                    int i1 = index / n;
                    int j1 = index % n;
                    if (board[i1][j1] != -1) {
                        s = board[i1][j1];
                        index = toIndex(s, n);
                    }
                    if (s == n * n) {
                        return count;
                    }
                    if (visited[index]) {
                        continue;
                    }
                    queue.offer(index);
                    visited[index] = true;
                }

            }

            count++;
        }

        return -1;

    }

    private int toVal(int index, int n) {
        int i = index / n;
        int j = index % n;
        int val = 0;
        val += (n - 1 - i) * n;
        if ((n - 1 - i) % 2 == 0) {
            val += j;
        } else {
            val = val + (n - 1 - j);
        }
        val++;
        return val;
    }

    private int toIndex(int val, int n) {
        val--;
        int i = (n - 1) - val / n;
        int j = 0;
        if ((n - 1 - i) % 2 == 0) {
            j = val % n;
        } else {
            j = n - 1 - (val % n);
        }
        return (i * n + j);
    }

    public int openLock(String[] deadends, String target) {
        Set<String> deadendSet = new HashSet<>();
        if (target.equals("0000")) {
            return 0;
        }
        int n = deadends.length;
        for (int i = 0; i < n; i++) {
            deadendSet.add(deadends[i]);
        }
        if (deadendSet.contains(target)) {
            return -1;
        }

        Set<String> visited = new HashSet<>();
        Queue<Union> queue = new LinkedList<>();
        queue.offer(new Union("0000", 0));
        visited.add("0000");

        while (!queue.isEmpty()) {
            int m = queue.size();
            for (int i = 0; i < m; i++) {
                Union p = queue.poll();
                for (int j = 0; j < 4; j++) {
                    StringBuffer str = new StringBuffer(p.getStatus());
                    int val = str.charAt(j) - '0';
                    val = (val + 1) % 10;
                    str.setCharAt(j, (char) (val + '0'));
                    String s = str.toString();
                    if (!visited.contains(s) && !deadendSet.contains(s)) {
                        if (s.equals(target)) {
                            return p.getCount() + 1;
                        }
                        queue.offer(new Union(s, p.getCount() + 1));
                        visited.add(s);
                    }

                    val = (val - 2 + 10) % 10;
                    str.setCharAt(j, (char) (val + '0'));
                    s = str.toString();
                    if (!visited.contains(s) && !deadendSet.contains(s)) {
                        if (s.equals(target)) {
                            return p.getCount() + 1;
                        }
                        queue.offer(new Union(s, p.getCount() + 1));
                        visited.add(s);
                    }

                }
            }
        }

        return -1;
    }

    class Union {
        private String status;
        private int count;

        public Union(String status, int count) {
            this.status = status;
            this.count = count;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    private Map<Character, List<Character>> map;
    private List<String> ret;

    public List<String> letterCombinations(String digits) {
        map = new HashMap<>();
        char num = '2';
        char c = 'a';
        for (int i = 2; i < 10; i++) {
            if(i == 7) {
                List<Character> list = new ArrayList<>();
                list.add((char)(c++));
                list.add((char)(c++));
                list.add((char)(c++));
                list.add((char)(c++));
                map.put((char)(num++), list);
            }else {
                List<Character> list = new ArrayList<>();
                list.add((char)(c++));
                list.add((char)(c++));
                list.add((char)(c++));
                map.put((char)(num++), list);
            }

        }

        ret = new ArrayList<>();

        if(digits.length() == 0) {
            ret.add("");
            return ret;
        }

        StringBuffer str = new StringBuffer();
        char[] ch = digits.toCharArray();
        getLetter(str, ch, 0);



        return ret;

    }

    private void getLetter(StringBuffer str, char[] ch, int i) {
        int n = ch.length;

        if(str.length() == n) {
            ret.add(str.toString());
            return;
        }

        for (Character c :
                map.get(ch[i])) {
            str.append(c);
            getLetter(str, ch, i+1);
            str.deleteCharAt(str.length()-1);
        }



    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, StringBuilder str, int left, int right, int n) {
        if(str.length() == 2 * n) {
            ans.add(str.toString());
            return;
        }

        if(left < n) {
            str.append('(');
            backtrack(ans,str, left + 1, right, n);
            str.deleteCharAt(str.length()-1);
        }

        if(right < left) {
            str.append(')');
            backtrack(ans, str, left, right+1, n);

        }
    }

    private int res = 0;

    /**
     * 解题思路：
     * 1.回溯
     * 2.状态压缩+动态规划
     * @param n
     * @return
     */
    public int countArrangement(int n) {
        int[] f = new int[1<<n];


        f[0] = 1;
        for (int i = 0; i < (1 << n); i++) {
            int index = Integer.bitCount(i);

            for (int j = 0; j < n; j++) {
                if((i & (1 << j)) != 0 && ((index % (j+1) == 0) || (j+1) % index == 0)) {
                    f[index] += f[index ^ (1 <<j)];
                }
            }
        }

        return f[1<<n -1];
    }

    private void backtrackArray(int i, int n, boolean[] v) {
        if(i == n) {
            res++;
            return;
        }

        for (int j = 0; j < n; j++) {
            if(!v[j] && ((i+1) % (j+1) == 0 || (j+1) % (i+1) == 0)) {
                v[j] = true;
                backtrackArray(i+1, n, v);
                v[j] = false;
            }
        }
    }

    private List<Integer> list = new ArrayList<>();
    List<List<Integer>> res2 = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        list.add(0);
        DFS(0, graph);

        return res2;
    }

    private void DFS(int i, int[][] graph) {
        if(i == graph.length-1) {
            res2.add(new ArrayList<>(list));
            return;
        }

        for (int j = 0; j < graph[i].length; j++) {
            list.add(graph[i][j]);
            DFS(graph[i][j], graph);
            list.remove(list.size()-1);
        }


    }



}
