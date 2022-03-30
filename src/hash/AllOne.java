package hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOne {
    class Node {
        Node pre;
        Node next;
        Set<String> keys;
        int count;
    }

    Node root;
    Map<String, Node> map;

    public AllOne() {
        root = new Node();
        map = new HashMap<>();
        root.next =root;
        root.pre = root;
    }

    public void inc(String key) {
        Node cur = map.get(key);
        if(cur == null) {
            if(root.next == root || root.next.count > 1) {
                Node first = new Node();
                first.next = root.next;
                first.pre = root;
                root.next.pre = first;
                root.next = first;
                first.keys = new HashSet<>();
                first.keys.add(key);
                first.count = 1;
                map.put(key, first);
            }else {
                root.next.keys.add(key);
                map.put(key, root.next);
            }
        }else {
            if(cur.next == root || cur.next.count > cur.count + 1) {
                Node nNode = new Node();
                nNode.next = cur.next;
                nNode.pre = cur;
                cur.next.pre = nNode;
                cur.next = nNode;
                nNode.keys = new HashSet<>();
                nNode.keys.add(key);
                nNode.count = cur.count + 1;
                map.put(key, nNode);
                cur.keys.remove(key);
                if(cur.keys.size() == 0) {
                    cur.pre.next = cur.next;
                    cur.next.pre = cur.pre;
                    cur = null;
                }

            }else {
                cur.next.keys.add(key);
                map.put(key, cur.next);
            }
        }
        System.out.println("!11");

    }

    public void dec(String key) {
        Node cur = map.get(key);
        if(cur.count - 1 == 0) {
            cur.keys.remove(key);
            if(cur.keys.size() == 0) {
                cur.pre.next = cur.next;
                cur.next.pre = cur.pre;
                cur = null;

            }
            map.remove(key);
        }else {
            if(cur.pre == root || cur.pre.count < cur.count - 1) {
                Node nNode = new Node();
                nNode.pre = cur.pre;
                nNode.next = cur.next;
                cur.pre.next = nNode;
                cur.pre = nNode;
                nNode.keys = new HashSet<>();
                nNode.keys.add(key);
                nNode.count = cur.count - 1;
                cur.keys.remove(key);
                if(cur.keys.size() == 0) {
                    cur.pre.next = cur.next;
                    cur.next.pre = cur.pre;
                    cur = null;
                }
                map.put(key, nNode);

            }else {
                cur.pre.keys.add(key);
                cur.keys.remove(key);
                map.put(key, cur.pre);
                if(cur.keys.size() == 0) {
                    cur.pre.next = cur.next;
                    cur.next.pre = cur.pre;
                    cur = null;
                }

            }
        }

    }

    public String getMaxKey() {
        if(root.pre == root) {
            return "";
        }else {
            return root.pre.keys.iterator().next();
        }
    }

    public String getMinKey() {
        if(root.next == root) {
            return "";
        }else {
            return root.next.keys.iterator().next();
        }
    }
}
