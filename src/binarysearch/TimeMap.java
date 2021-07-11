package binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeMap {

    private Map<String, List<String>> toKeys;
    private Map<String, List<Integer>> toTimes;

    /** Initialize your data structure here. */
    public TimeMap() {
        toKeys = new HashMap<>();
        toTimes = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<String> toKey = toKeys.getOrDefault(key, new ArrayList<>());
        toKey.add(value);
        List<Integer> toTime = toTimes.getOrDefault(key, new ArrayList<>());
        toTime.add(timestamp);
    }

    public String get(String key, int timestamp) {
        List<String> toKey = toKeys.getOrDefault(key, new ArrayList<>());

        List<Integer> toTime = toTimes.getOrDefault(key, new ArrayList<>());

        if(toKey.size() == 0 || toTime.size() == 0) {
            return "";
        }

        int left = 0;
        int right = toTime.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if(toTime.get(mid) <= timestamp) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }

        if(left == 0) {
            return "";
        }

        return toKey.get(left-1);
    }
}
