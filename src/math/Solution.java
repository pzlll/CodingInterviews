package math;

import java.util.*;

public class Solution {
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
        while(x > 0) {
            int i = x % 10;
            int temp = res;

            res = res * 10 + i;

            if(isPositive && temp != (res / 10)) {
                return 0;
            }
            if(!isPositive && (-temp) < -(res /10)) {
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
        if (s == null)
            return 0;
        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        boolean flag = true;

        if (i < s.length() && s.charAt(i) == '-') {
            flag = false;
            i++;
        } else if (i < s.length() && s.charAt(i) == '+') {
            flag = true;
            i++;
        }

        int sum = 0;

        int check = 0;

        while (i < s.length() && (s.charAt(i) <= '9' && s.charAt(i) >= '0')) {
            check = sum;
            sum = sum * 10 + s.charAt(i) - '0';
            if (sum < check) {
                if (flag) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
            i++;
        }

        if (!flag) {
            sum = -sum;
        }

        return sum;
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

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) {
                return -dividend;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        long a = Math.abs(dividend);
        long b = Math.abs(divisor);

        int num = 0;
        while (a >= b) {
            a = a - b;
            num++;
        }

        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            num = -num;
        }

        return num;
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
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10 ,9, 5, 4, 1};

        StringBuffer buffer = new StringBuffer();
        int i = 0;
        while(num > 0) {
            while (num >= values[i]) {
                buffer.append(symbols[i]);
                num -= values[i];
            }
            if(num == 0) {
                break;
            }
            i++;
        }

        return buffer.toString();
    }


}
