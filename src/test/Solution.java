package test;

//import org.apache.commons.lang3.RandomStringUtils;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void bruteMethod(double[] a, double[] b, double[] c, int n) {
        // 两层遍历，先遍历数组a
        for (int i = 0; i < n; i++) {
            //若为零，则改位不用相乘
            if(a[i] == 0) {
                continue;
            }
            //遍历数组b
            for (int j = 0; j < n; j++) {
                if(b[j] == 0) {
                    continue;
                }
                //把相乘结果存储在数组c中
                if(a[i] != 0 && b[j] != 0) {
                    double val = a[i] * b[j];
                    c[i + j] += val;
                }

            }
        }


    }

    public static void sumRes(double[] c, int n) {
        double temp = 0;
        for(int i = 0; i < 2 * n + 1; i++) {
            c[i] += temp;
            temp = (int)(c[i]/10);
            c[i] = c[i] % 10;
        }
    }

    public static void mergeMethod(double[] a, double[] b,  double[] c, int n, int aStart, int bStart) {
        if(aStart > length/2 || bStart >length/2) {
            return;
        }
        if(n == 1) {
            if(a[aStart] !=0 && b[bStart] != 0) {
                c[aStart + bStart] += a[aStart] * b[bStart];

            }
            return;
        }

        mergeMethod(a, b, c, n/2, aStart + n/2, bStart + n/2);

        mergeMethod(a, b, c, n/2, aStart, bStart + n/2);

        mergeMethod(a, b, c, n/2, aStart + n/2, bStart);

        mergeMethod(a, b, c, n/2, aStart, bStart);




    }

//    private static double[] staA;
//    private static double[] staB;


    private static int length;

    public static void mergeMethodPro(double[] a, double[] b,  double[] c, int n, int cStart, int start) {
        if(start > length / 2) {
            return;
        }
        if(n == 1) {
            if(a[start] !=0 && b[start] != 0) {
                c[cStart] += a[start] * b[start];

            }
            return;
        }

        double[] d = new double[n];
        mergeMethodPro(a, b, d, n/2,0,  start + n/2);

        for (int i = 0; i < n; i++) {
            c[n + i] += d[i];
            c[n/2 + i] += d[i];
        }

        Arrays.fill(d, 0);

        mergeMethodPro(a, b, d, n/2, 0,  start);


        for (int i = 0; i < n; i++) {
            c[i] += d[i];
            c[n/2 + i] += d[i];
        }

        Arrays.fill(d, 0);

        double[] a_b = new double[n/2];
        double[] d_c = new double[n/2];

        for (int i = start; i < start + n/2; i++) {
            a_b[i - start] = a[i + n/2] - a[i];
            d_c[i - start] = b[i] - b[i + n/2];
        }

        mergeMethodPro(a_b, d_c, d, n/2,0, 0);

        for (int i = 0; i < n; i++) {
            c[n/2 + i] += d[i];
        }


    }

    public static void printRes(double[] c, int n) {
        for (int i =  2 * n ; i >= 0; i--) {
            System.out.print((int)c[i]);
        }
        System.out.println();
    }



}
