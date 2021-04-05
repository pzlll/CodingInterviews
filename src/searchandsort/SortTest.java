package searchandsort;

import org.junit.Test;

import java.util.Arrays;

public class SortTest {
    @Test
    public void test() {
        int[] integers = new int[5];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = 10 - i;
        }

//        Sort.bubbleSort(integers);
//        Sort.selectSort(integers);
//        Sort.insertSort(integers);
//        Sort.hillSort(integers);
//        Sort.mergeSort(integers);
//        Sort.quickSort(integers);
//        Sort.heapSort(integers);
//        System.out.println(Arrays.toString(integers));


    }

    public static void main(String[] args) {
        int[] a = {1,2,3,3,4,7,12,1,7,2,5,12,16,2};
        Sort.countingSort(a);
        System.out.println(Arrays.toString(a));
    }
}
