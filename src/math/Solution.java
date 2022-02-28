package math;

import java.util.*;

public class Solution {

    public Solution() {}
    /**
     * !关键是判断溢出
     * 解题思路：若得到的结果除以10不等于原先的数，则溢出
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        boolean isPositive = x > 0 ? true : false;
        x = Math.abs(x);
        int res = 0;
        while (x > 0) {
            int i = x % 10;
            int temp = res;

            res = res * 10 + i;

            if (isPositive && temp != (res / 10)) {
                return 0;
            }
            if (!isPositive && (-temp) < -(res / 10)) {
                return 0;
            }
            x = x / 10;
        }

        return isPositive ? res : -res;
    }

    /**
     * 自动机实现
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int n = s.length();
        Automation automation = new Automation();
        for (int i = 0; i < n; i++) {
            automation.changeState(s.charAt(i));
        }

        return (int) automation.getAns();

    }

    class Automation {
        private long ans;
        private String state;
        private int sign;
        private Map<String, String[]> table;

        public Automation() {
            state = "start";
            ans = 0;
            sign = 1;
            table = new HashMap<>();
            table.put("start", new String[]{"start", "signed", "in_number", "end"});
            table.put("signed", new String[]{"end", "end", "in_number", "end"});
            table.put("in_number", new String[]{"end", "end", "in_number", "end"});
            table.put("end", new String[]{"end", "end", "end", "end"});
        }

        public long getAns() {
            return sign == 1 ? ans : -ans;
        }

        public void changeState(char c) {
            this.state = table.get(state)[get_col(c)];
            if (state.equals("in_number")) {
                ans = ans * 10 + c - '0';
                ans = (sign == 1 ? Math.min(ans, Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE));
            }
            if (state.equals("signed")) {
                sign = (c == '+' ? 1 : 0);
            }
        }

        private int get_col(char c) {
            if (c == ' ') {
                return 0;
            }
            if (c == '-' || c == '+') {
                return 1;
            }

            if (Character.isDigit(c)) {
                return 2;
            }

            return 3;
        }
    }

    /**
     * 直线方程问题
     *
     * @param coordinates
     * @return
     */
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates == null || coordinates.length <= 1) {
            return true;
        }

        boolean flag = false;
        if (coordinates[0][0] == coordinates[1][0]) {
            flag = true;
        }
        double k = (double) (coordinates[1][1] - coordinates[0][1]) / (coordinates[1][0] - coordinates[0][0]);

        for (int i = 2; i < coordinates.length; i++) {
            if (flag) {
                if (coordinates[i][0] != coordinates[0][0]) {
                    return false;
                }
            } else {
                if (coordinates[i][0] == coordinates[0][0]) {
                    return false;
                }
                double k1 = (double) (coordinates[i][1] - coordinates[0][1]) / (coordinates[i][0] - coordinates[0][0]);
                if (k != k1) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * 旋转数组的公式
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[matrix.length - j - 1][i];
                matrix[matrix.length - j - 1][i] = temp;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }



    /**
     * 解题思路：判断数是否只包含质数（2，3，5），则把数中的质数（2，3，5）剔除，看剩下的数是否为1
     *
     * @param n
     * @return
     */
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor :
                factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }

    /**
     * 解题思路：对于给定的数字，选择尽可大的符号值，总共有13个符号
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
//        String[] thousand = {"M", "MM", "MMM"};
//        String[] hundred = {"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
//        String[] ten = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
//        String[] one = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
//        StringBuffer buffer = new StringBuffer();
//        int a = num % 10;
//        num /= 10;
//        int b = num % 10;
//        num /= 10;
//        int c = num % 10;
//        num /= 10;
//        int d = num % 10;
//        if(d > 0) {
//            buffer.append(thousand[d - 1]);
//        }
//        if(c > 0) {
//            buffer.append(hundred[c - 1]);
//        }
//        if(b > 0) {
//            buffer.append(ten[b - 1]);
//        }
//        if(a > 0) {
//            buffer.append(one[a - 1]);
//        }
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuffer buffer = new StringBuffer();
        int i = 0;
        while (num > 0) {
            while (num >= values[i]) {
                buffer.append(symbols[i]);
                num -= values[i];
            }
            if (num == 0) {
                break;
            }
            i++;
        }

        return buffer.toString();
    }

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;
        int m = queries.length;

        long[] front = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            front[i] = front[i - 1] + candiesCount[i - 1];
        }

        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; i++) {
            int type = queries[i][0];

            long num = front[type + 1];


            long day = queries[i][1];
            long cap = queries[i][2];

            if (num <= day) {
                ans[i] = false;
                continue;
            }

            num = front[type];


            if (num >= ((day + 1) * cap)) {
                ans[i] = false;
                continue;
            }

            ans[i] = true;
        }

        return ans;
    }

    /**
     * 解题思路：
     * 自动机：建立状态和它们之间的转移关系
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        Map<String, String[]> map = new HashMap<>();

        map.put("a", new String[]{"error", "b", "c", "d", "error"});
        map.put("b", new String[]{"error", "error", "c", "d", "error"});
        map.put("c", new String[]{"error", "error", "error", "e", "error"});
        map.put("d", new String[]{"f", "error", "e", "d", "error"});
        map.put("e", new String[]{"f", "error", "error", "e", "error"});
        map.put("f", new String[]{"error", "g", "error", "h", "error"});
        map.put("g", new String[]{"error", "error", "error", "h", "error"});
        map.put("h", new String[]{"error", "error", "error", "h", "error"});

        String state = "a";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = 0;
            switch (c) {
                case 'e':
                case 'E':
                    index = 0;
                    break;
                case '+':
                case '-':
                    index = 1;
                    break;
                case '.':
                    index = 2;
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    index = 3;
                    break;
                default:
                    index = 4;
                    break;
            }

            state = map.get(state)[index];
            if(state.equals("error")) {
                return false;
            }


        }

        if(state.equals("d") || state.equals("e") || state.equals("h")) {
            return true;
        }

        return false;

    }

    /**
     * 解题思路：公式推导
     * @param columnNumber
     * @return
     */
    public String convertToTitle(int columnNumber) {
        StringBuffer str = new StringBuffer();
        columnNumber--;

        Stack<Character> stack = new Stack<>();

        while (columnNumber > 0) {
            int offset = (columnNumber-1) % 26;

            int v = 'A' + offset;
            stack.push((char)v);


            columnNumber = (columnNumber-offset - 1)/26;
        }

        int n = stack.size();

        for (int j = 0; j < n; j++) {
            str.append(stack.pop());
        }

        return str.toString();
    }

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int dist = Math.abs(target[0]) + Math.abs(target[1]);

        for (int i = 0; i < ghosts.length; i++) {
            int d = Math.abs(target[0] - ghosts[i][0]) + Math.abs(target[1] - ghosts[i][1]);
            if(d <= dist) {
                return false;
            }
        }

        return true;
    }

    /**
     * 解题思路：拒绝采样
     * @return
     */
    public int rand10() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2, 1);
        map.put(3, 2);
        map.put(5, 3);
        map.put(7, 4);
        map.put(8, 5);
        map.put(10, 6);
        map.put(14, 7);
        map.put(15, 8);
        map.put(21, 9);
        map.put(20, 10);

        while (true) {
            int ret = rand7() * rand7();
            if(map.containsKey(ret)) {
                return map.get(ret);
            }

        }
    }

    private int rand7() {
        return 0;
    }

    public int bulbSwitch(int n) {
        int i = 1;
        int count = 0;
        while (i * i <= n) {
            count++;
            i++;
        }

        return count;
    }

    private int[] origin;
    private int n;

    public Solution(int[] nums) {
        this.n = nums.length;
        this.origin = new int[this.n];
        for (int i = 0; i < this.n; i++) {
            this.origin[i] = nums[i];
        }
    }

    public int[] reset() {
        return this.origin;
    }

    public int[] shuffle() {
        int[] arrange = new int[this.n];

        for (int i = 0; i < n; i++) {
            arrange[i] = i;
        }

        int[] ret = new int[this.n];
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            int index = random.nextInt(n-i);
            ret[i] = this.origin[arrange[index]];
            int temp = arrange[index];
            arrange[index] = arrange[n-i-1];
            arrange[n-i-1] = temp;
        }

        return ret;
    }

    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry :
                freq.entrySet()) {
            int[] key = new int[]{entry.getKey(), entry.getValue()};
            queue.offer(key);
            if(queue.size() > k) {
                queue.poll();
            }
        }

        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = queue.poll()[0];
        }

        return ret;
    }

    public int trailingZeroes(int n) {

        int count = 0;
        int divider = 5;
        while(divider <= n) {
            count += (n / divider);
            divider *= 5;
        }

        return count;
    }

    public double myPow(double x, int n) {


        long N = n;
        if(N == 0) {
            return 1.0;
        }

        boolean flag = false;
        if(N < 0) {
            flag = true;
            N = -N;
        }


        double res = 1;
        while (N > 0) {
            res  = res * ((N & 1) == 1 ? x: 1);
            N >>= 1;
            x *= x;
        }




        return flag ? 1.0/res : res;
    }

    public int mySqrt(int x) {
        long X = x;
        if(x == 0 || x == 1){
            return x;
        }
        int start = 1;
        int end = x;
        while(start < end) {
            int mid = (end - start) / 2 + start;
            if(mid * mid == X) {
                return mid;
            }else if(mid * mid > X) {
                end = mid;
            }else {
                start = mid + 1;
            }
        }

        return start == 1 ? 1 : start - 1;
    }

    public String optimalDivision(int[] nums) {
        StringBuffer str = new StringBuffer();
        int n = nums.length;
        if(nums.length == 1) {
            str.append(nums[0]);
            return new String(str);
        }

        if(nums.length == 2) {
            str.append(nums[0]);
            str.append('/');
            str.append(nums[1]);
            return new String(str);
        }


        str.append(nums[0]);
        str.append('/');
        str.append('(');
        for (int i = 1; i < n - 1; i++) {
            str.append(nums[i]);
            str.append('/');
        }

        str.append(nums[n-1]);
        str.append(')');

        return new String(str);
    }

    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE) {
            if(divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if(divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }

        if(divisor == Integer.MIN_VALUE) {
            if(dividend == 1) {
                return Integer.MIN_VALUE;
            }else {
                return 0;
            }
        }

        if(divisor == 0) {
            return 0;
        }

        boolean flag = false;
        if(dividend > 0) {
            dividend = -dividend;
            flag = !flag;
        }

        if(divisor > 0) {
            divisor = -divisor;
            flag = !flag;
        }

        int left = 1;
        int right = Integer.MAX_VALUE;

        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) >> 1;

            boolean check = quickAdd(divisor, mid, dividend);

            if(check) {
                ans = mid;
                if(mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            }else {
                right = mid - 1;
            }

        }

        return flag ? -ans : ans;

    }

    private boolean quickAdd(int divisor, int z, int dividend) {
        int res = 0;
        int add = divisor;
        while (z > 0) {
            if((z & 1) != 0) {
                if(res < dividend - add) {
                    return false;
                }
                res += add;
            }

            if(z != 1) {
                if(add < dividend - add) {
                    return false;
                }
                add += add;
            }
            z >>= 1;
        }

        return true;
    }


}
