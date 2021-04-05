package hash;

import java.util.LinkedList;

public class MyHashSet {
    /** Initialize your data structure here. */
    private static final int BASE = 769;
    private LinkedList<Integer>[] set;
    public MyHashSet() {
        set = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            set[i] = new LinkedList<>();
        }
    }

    public void add(int key) {
        int h = hash(key);
        if(!set[h].contains(key)) {
            set[h].add(key);
        }
    }

    private int hash(int key) {
        return key % BASE;
    }

    public void remove(int key) {
        int h = hash(key);
        if(set[h].contains(key)) {
            set[h].remove(Integer.valueOf(key));
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h = hash(key);
        return set[h].contains(key);
    }

}
