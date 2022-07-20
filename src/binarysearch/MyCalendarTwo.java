package binarysearch;

import java.util.ArrayList;
import java.util.List;

public class MyCalendarTwo {

    List<int[]> bookedList;
    List<int[]> overlapList;
    public MyCalendarTwo() {
        bookedList = new ArrayList<>();
        overlapList = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] overlap :
                overlapList) {
            if(end > overlap[0] && overlap[1] > start) {
                return false;
            }
        }



        for (int[] booked:
             bookedList) {
            if(booked[1] > start && end > booked[0]) {
                overlapList.add(new int[]{Math.max(start, booked[0]), Math.min(end, booked[1])});
            }
        }

        bookedList.add(new int[]{start, end});

        return true;
    }
}
