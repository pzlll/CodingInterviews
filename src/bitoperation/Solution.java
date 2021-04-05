package bitoperation;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int NumberOf1(int n) {
        int num = 0;
        while(n != 0) {
            if((n & 1) == 1) {
                num++;
            }
            n = n>>>1;

            System.out.println(n);
        }

        return num;
    }

    public static void main(String[] args) {
        int n = 16;
        int i = new Solution().NumberOf1(-1);
        System.out.println(i);

    }
}
