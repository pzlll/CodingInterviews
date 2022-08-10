package string;

import java.util.*;

public class Solution {
    /**
     * 动态规划
     * 利用小的计算结果推演出大的
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }
        String str = "";
        boolean[][] isPR = new boolean[s.length()][s.length()];
        for (int l = 0; l < s.length(); l++) {
            for (int i = 0; i + l < s.length(); i++) {
                int j = i + l;
                boolean temp = s.charAt(i) == s.charAt(j);
                if (j == i) {
                    isPR[i][j] = true;
                } else if (j - i == 1 && temp) {
                    isPR[i][j] = true;
                } else if (isPR[i + 1][j - 1] == true && temp) {
                    isPR[i][j] = true;
                } else {
                    isPR[i][j] = false;
                }
                if (isPR[i][j] == true && str.length() < (j - i + 1)) {
                    str = s.substring(i, j + 1);
                }

            }
        }

        return str;
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> list = new ArrayList<>();

        if (s == null) {
            return list;
        }

        int i = 0;
        int j = i + 1;
        int count = 0;
        while (i < s.length() && j < s.length()) {
            if (s.charAt(j) == s.charAt(i)) {
                count++;
                j++;
            } else {
                if (count >= 2) {
                    List<Integer> l = new ArrayList<>();
                    l.add(i);
                    l.add(j - 1);
                    list.add(l);
                }
                i = j;
                j++;
                count = 0;
            }
        }

        if (count >= 2) {
            List<Integer> l = new ArrayList<>();
            l.add(i);
            l.add(j - 1);
            list.add(l);
        }

        return list;
    }

    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (isMatch(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (isMatch(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }

        return f[n][m];
    }

    private boolean isMatch(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /**
     * 解题思路：对于罗马数字，若数值小的符号出现在数值大的符号前面，则表示减去该值；
     * 若数值小的符号出现在数值大的符号后面，则表示加上该值
     * 通过把数值存储进哈系表来优化代码
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int sum = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));
            if ((i + 1) < s.length() && (value < map.get(s.charAt(i + 1)))) {
                sum -= value;
            } else {
                sum += value;
            }
        }
        return sum;

    }

    public String longestCommonPrefix(String[] strs) {

        int min = Integer.MAX_VALUE;
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == null) {
                return "";
            }
            if (strs[i].length() < min) {
                min = strs[i].length();
            }
        }

        if (min == 0) {
            return "";
        }

        int i = 0;
        for (i = 0; i < min; i++) {
            char ch = strs[0].charAt(i);
            boolean flag = true;

            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != ch) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                break;
            }

        }

        if (i == 0) {
            return "";
        } else {
            return strs[0].substring(0, i);
        }
    }

    /**
     * 栈应用
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i < tokens.length) {
            String s = tokens[i];
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                if (!stack.isEmpty() && stack.size() >= 2) {
                    int b = stack.pop();
                    int a = stack.pop();
                    switch (s) {
                        case "+":
                            sum = a + b;
                            stack.push(sum);
                            break;
                        case "-":
                            sum = a - b;
                            stack.push(sum);
                            break;
                        case "*":
                            sum = a * b;
                            stack.push(sum);
                            break;
                        case "/":
                            sum = a / b;
                            stack.push(sum);
                    }
                } else {
                    return -1;
                }
            } else {
                Integer temp = Integer.valueOf(s);
                stack.push(temp);
            }
            i++;
        }
        if (!stack.isEmpty()) {
            sum = stack.pop();
        }
        return sum;
    }

    /**
     * 动态规划
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        //将字典元素存入哈希表
        Set<String> set = new HashSet<>();
        for (String str :
                wordDict) {
            set.add(str);
        }

        boolean[] record = new boolean[s.length() + 1];
        record[0] = true;

        //用recode数组记录之前的状态
        //建立转移方程：record[i] = recode[j] && s[j+1, i](是否在字典中)
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (record[j] && set.contains(s.substring(j, i))) {
                    record[i] = true;
                    break;
                }
            }
        }

        return record[s.length()];

    }

    /**
     * 使用双指针对字符串进行判断
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            while (!isNumOrAlpha(s.charAt(i)) && i < j) {
                i++;
            }
            while (!isNumOrAlpha(s.charAt(j)) && i < j) {
                j--;
            }

            if (i < j) {
                Character s1 = Character.toLowerCase(s.charAt(i));
                Character s2 = Character.toLowerCase(s.charAt(j));
                if (!s1.equals(s2)) {
                    return false;
                }

            }
            i++;
            j--;
        }

        return true;
    }

    //可使用Character.isLetterOrDigit() 并且不需要函数调用
    private boolean isNumOrAlpha(char j) {

        if (Character.isLetter(j) || Character.isDigit(j)) {
            return true;
        }

        return false;
    }

    /**
     * 解题思路：1.使用字符拼接   2.使用散列表存放映射
     *
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        Map<Integer, String> map = new TreeMap<>();
        map.put(3, "Fizz");
        map.put(5, "Buzz");

        for (int i = 1; i <= n; i++) {
            StringBuilder str = new StringBuilder();
            for (Integer key :
                    map.keySet()) {
                if (i % key == 0) {
                    str.append(map.get(key));
                }
            }

            if (str.length() == 0) {
                str.append(i);
            }

            result.add(str.toString());
        }
        return result;
    }

    public String countAndSay(int n) {
        int i = 1;
        String s = "1";
        StringBuffer str = new StringBuffer();
        while (i < n) {
            int j = 0;
            int k = (j+1);
            int m = s.length();
            while(j < m) {
                while(k < m && (s.charAt(k) == s.charAt(j))) {
                    k++;
                }
                str.append((k - j));
                str.append(s.charAt(j));
                j = k;
                k = (j + 1);
            }
            s = str.toString();
            str.delete(0, str.length());
            i++;
        }



        return s;
    }


    public String maximumTime(String time) {
        Map<Integer, Character> map = new HashMap<Integer, Character>(){{
            put(0, '2');
            put(1, '9');
            put(2, ':');
            put(3, '5');
            put(4, '9');
        }};

        StringBuffer str = new StringBuffer();

        for (int i = 0; i < time.length(); i++) {
            if(time.charAt(i) != '?') {
                str.append(time.charAt(i));
            }else {
                if(i == 1 && str.charAt(0) == '2') {
                    str.append('3');
                }else if(i == 0 && (time.charAt(1) >= '4' && time.charAt(1) <= '9')) {
                    str.append('1');
                }
                else{
                    str.append(map.get(i));

                }
            }

        }

        return str.toString();

    }

    public int titleToNumber(String columnTitle) {
        int n = columnTitle.length();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            char c = columnTitle.charAt(i);

            ans = ans * 26 + c - 'A' + 1;

        }

        return ans;
    }


    public boolean checkRecord(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            count = count + (chars[i] == 'A' ? 1 : 0);
            if(count >= 2) {
                return false;
            }
            if(chars[i] == 'L') {
                num++;
            }else {
                num = 0;
            }

            if(num >= 3) {
                return false;
            }
        }




        return true;
    }

    public int lengthOfLastWord(String s) {
        int i = s.length() -1;
        int len = 0;
        while (i >= 0) {
            if(s.charAt(i) != ' ') {
                break;
            }
            i--;
        }
        while (i >= 0) {
            if(s.charAt(i) == ' ') {
                break;
            }
            len++;
            i--;
        }

        return len;
    }

    public String convert(String s, int numRows) {
        StringBuffer[] strs = new StringBuffer[numRows];

        for (int i = 0; i < numRows; i++) {
            strs[i] = new StringBuffer();
        }

        int i = 0;
        int n = numRows;
        int m = s.length();
        while(i < m) {
            int j = 0;
            while (i < m && j < n) {
                strs[j].append(s.charAt(i));
                j++;
                i++;
            }
            j -= 2;
            while (i < m && j > 0) {
                strs[j].append(s.charAt(i));
                j--;
                i++;
            }
        }

        StringBuffer res = new StringBuffer();
        for (int j = 0; j < n; j++) {
            res.append(strs[j]);
        }

        return new String(res);
    }

    public String nearestPalindromic(String n) {
        int m = n.length();

        List<Long> candidates = getCandidates(n);

        long selfNumber = Long.parseLong(n);

        long ans = -1;
        for (Long candidate :
                candidates) {
            if(ans == -1 || Math.abs(candidate - selfNumber) < Math.abs(ans - selfNumber)
                    || (Math.abs(candidate - selfNumber) == Math.abs(ans - selfNumber) && candidate < ans)) {
                ans = candidate;
            }

        }

        return Long.toString(ans);
    }

    private List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> res = new ArrayList<>();
        res.add((long)(Math.pow(10, len - 1) - 1));
        res.add((long)(Math.pow(10, len)) + 1);

        Long selfPrefix = Long.parseLong(n.substring(0, (len + 1)/2));

        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuffer sb = new StringBuffer();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            StringBuffer suffix = new StringBuffer(prefix).reverse();
            sb.append(suffix.substring(len & 1));
            res.add(Long.parseLong(new String(sb)));
        }
        return res;
    }

    public int findLUSlength(String a, String b) {
        if(a.length() != b.length()) {
            return a.length() > b.length() ? a.length() : b.length();
        }else {
            return a.equals(b) ? -1 : a.length();
        }

    }

    public List<String> stringMatching(String[] words) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if(i == j || words[i].length() > words[j].length()) {
                    continue;
                }
                int i1 = 0;
                int j1 = 0;
                while (i1 < words[i].length() && j1 < words[j].length()) {
                    if(words[i].charAt(i1) == words[j].charAt(j1)) {
                        i1++;
                        j1++;
                    }else {
                        j1 = j1 - i1 + 1;
                        i1 = 0;

                    }
                }
                if(i1 == words[i].length()) {
                    res.add(words[i]);
                    break;
                }
            }
        }

        return res;
    }

    public String solveEquation(String equation) {
        String[] split = equation.split("=");
        int sum1 = 0;
        int count1 = 0;
        int sum2 = 0;
        int count2 = 0;

        int i = 0;
        boolean flag = false;
        int sum = 0;
        boolean valid = false;
        while (i < split[0].length()) {
            char c = split[0].charAt(i);
            if(c == '+') {
                if(sum != 0) {
                    sum1 = sum1 + (flag ? -sum : sum);
                    sum = 0;
                }
                flag = false;
                i++;
                valid = false;
                continue;
            }
            if(c == '-') {
                if(sum != 0) {
                    sum1 = sum1 + (flag ? -sum : sum);
                    sum = 0;
                }
                flag = true;
                i++;
                valid = false;
                continue;
            }
            if(c == 'x') {
                if(!valid) {
                    count1 = count1 + (flag ? -1 : 1);
                }else {
                    count1 = count1 + (flag ? -sum : sum);
                }
                sum = 0;
                i++;
                valid = false;
                continue;
            }
            sum = sum * 10 + c - '0';
            i++;
            valid = true;


        }
        if(sum != 0) {
            sum1 = sum1 + (flag ? -sum : sum);
            sum = 0;
        }

        i = 0;
        flag = false;
        valid = false;
        while (i < split[1].length()) {
            char c = split[1].charAt(i);
            if(c == '+') {
                if(sum != 0) {
                    sum2 = sum2 + (flag ? -sum : sum);
                    sum = 0;
                }
                flag = false;
                i++;
                valid = false;
                continue;
            }
            if(c == '-') {
                if(sum != 0) {
                    sum2 = sum2 + (flag ? -sum : sum);
                    sum = 0;
                }
                flag = true;
                i++;
                valid = false;
                continue;
            }
            if(c == 'x') {
                if(!valid) {
                    count2 = count2 + (flag ? -1 : 1);
                }else {
                    count2 = count2 + (flag ? -sum : sum);
                }
                sum = 0;
                i++;
                valid = false;
                continue;
            }
            sum = sum * 10 + c - '0';
            i++;
            valid = true;

        }

        if(sum != 0) {
            sum2 = sum2 + (flag ? -sum : sum);
            sum = 0;
        }

        System.out.println("sum1 : " + sum1);
        System.out.println("sum2 : " + sum2);
        System.out.println("count1 : " + count1);
        System.out.println("count2 : " + count2);


        count1 = count1 - count2;
        sum2 = sum2 - sum1;
        if(sum2 == 0 && count1 == 0){
            return "Infinite solutions";
        }

        if(count1 == 0 && sum2 != 0) {
            return "No solution";
        }

        return "x=" + sum2/count1;
    }
}
