package array;

import java.util.*;

public class RandomizedSet {

    private Map<Integer, Integer> map;
    private List<Integer> list;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if(map.get(val) != null) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if(map.get(val) == null) {
            return false;
        }

        Integer index = map.get(val);
        map.remove(val);
        if(val != list.get(list.size() - 1)) {
            map.put(list.get(list.size() - 1), index);
            list.set(index, list.get(list.size() - 1));
        }

        list.remove(list.size() - 1);

        return true;

    }

    public int getRandom() {
        int n = list.size();
        Random r = new Random();
        int index = r.nextInt(n);
        System.out.println(index);
        return list.get(index);
    }
}
