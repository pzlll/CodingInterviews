package cn;

import org.junit.Test;

public class No10 {

    public int fib(int n) {
        if(n <= 1) {
            return  n;
        }

        int a = 0;
        int b = 1;
        int k = n - 1;
        int sum = 0;
        while(k > 0) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
            k--;
        }

        return sum;



    }

    private int fib1(int n, int[] nums) {
        if(n <= 1) {
            return n;
        }

        if(nums[n] != 0) {
            return nums[n] ;
        }
        else {
            nums[n] = (fib1(n-1, nums) + fib1(n-2, nums))% (1000000007);
            return nums[n];
        }



    }

    public int numWays(int n) {
        if(n <= 2) {
            return n;
        }

        int a = 1;
        int b = 2;

        int num = n;
        int sum = 0;
        while(num > 2) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
            num--;
        }

        return sum;
    }
}
