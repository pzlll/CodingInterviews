package recursionandcirculation;

import java.io.File;

public class Solution {
    public int Fibonacci(int n) {
        if(n == 0) {
            return 0;
        }
        int num = Fib(n);

        return num;
    }

    private int Fib(int n) {

        if(n == 1 || n==2) {
            return 1;
        }

        return Fib(n-1) + Fib(n-2);
    }



    public int JumpFloor(int target) {
        int num = jump(target);
        return num;
    }

    private int jump(int target) {
        if(target == 1 || target == 2) {
            return target;
        }

        return jump(target-1) + jump(target-2);
    }

    public int JumpFloorII(int target) {
        int num = jumpII(target);
        return num;
    }

    private int jumpII(int target) {
        if(target == 1) {
            return 1;
        }

        return jumpII(target-1)*2;
    }

    public int RectCover(int target) {
        if(target <= 0) {
            return 0;
        }

        return cover(target);
    }

    private int cover(int target) {
        if(target == 1) {
            return 1;
        }
        if(target == 2) {
            return 4;
        }

        return cover(target-1) + 2 * cover(target-2);
    }
}
