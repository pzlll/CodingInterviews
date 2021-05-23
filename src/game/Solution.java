package game;

import java.util.Stack;

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
        while(j < n) {
            if(s.charAt(j) == '0') {
                j++;
                i = j;
            }else {
                max1 = Math.max(max1, (j-i + 1));
                j++;
            }
        }

        i = 0;
        j = 0;
        while(j < n) {
            if(s.charAt(j) == '1') {
                j++;
                i = j;
            }else {
                max0 = Math.max(max0, (j-i + 1));
                j++;
            }
        }
        if(max1 > max0) {
            return true;
        }else {
            return false;
        }
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;
        if(hour <= (n-1)) {
            return -1;
        }
        int max = 0;
        for (int i = 0; i < (n - 1); i++) {
            max = Math.max(max, dist[i]);
        }
        max = Math.max(max, (int) Math.ceil(dist[n-1] / (hour-n+1)));

        int left = 1;
        int right = max;
        while (left < right) {
            int mid = (right + left) / 2;

            if(isOnTime(hour, mid, dist)) {
                right = mid;
            }else  {
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

        time += ((double) dist[dist.length-1] / mid);
        if(time > hour) {
            return false;
        }else {
            return true;
        }
    }

    public boolean canReach(String s, int minJump, int maxJump) {

        int n = s.length();
        int length = n;

        if(s.charAt(n-1) == '1') {
            return false;
        }


        Stack<Integer> stack = new Stack<>();
        stack.push(n -1);

        boolean[] res = new boolean[n];
        res[n-1] = true;
        while (!stack.isEmpty() && length > 1) {
            Integer pop = stack.pop();

            if(length >= (pop - maxJump)) {
                for (int i = pop-minJump; i >= (pop - maxJump) && i >= 0; i--) {
                    if(s.charAt(i) == '0' && (!res[i])) {
                        stack.push(i);
                        res[i] = true;
                        length = Math.min(length, (i + 1));
                    }
                }
            }

        }
        return (length > 1) ? false:true;
    }

}
