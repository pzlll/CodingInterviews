package tree;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private class TrieNode{
        TrieNode[] next;
        boolean isEnd;
    }
    private TrieNode root;
//    private boolean isEnd;
//    private Map<Character, Trie> next;
    /** Initialize your data structure here. */
    public Trie() {
//        this.isEnd = false;
        root = new TrieNode();
        root.next = new TrieNode[26];
        root.isEnd = false;

    }



    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode t = root;
        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);
            int j = w - 'a';
            if(t.next[j] == null) {
                t.next[j] = new TrieNode();
                t.next[j].next = new TrieNode[26];
                t.next[j].isEnd = false;
            }
            t = t.next[j];

        }

        t.isEnd = true;
//        Trie t = this;
//        for (int i = 0; i < word.length(); i++) {
//            if(t.next == null) {
//                t.next = new HashMap<>();
//            }
//            if(t.next.get(word.charAt(i)) == null) {
//                Trie trie = new Trie();
//                t.next.put(word.charAt(i), trie);
//
//            }
//            if(i == word.length() -1) {
//                Trie trie = t.next.get(word.charAt(i));
//                trie.isEnd = true;
//            }
//            t = t.next.get(word.charAt(i));
//
//        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode t = root;
        for (int i = 0; i < word.length(); i++) {
            int j = word.charAt(i) - 'a';
            if(t == null) {
                return false;
            }else{
                t = t.next[j];
            }
        }
        return t.isEnd;

//        Trie t = this;
//        for (int i = 0; i < word.length(); i++) {
//            if(t.next == null) {
//                return false;
//            }
//            if(t.next.get(word.charAt(i)) == null) {
//                return false;
//            }
//            if(i == word.length()-1 && t.next.get(word.charAt(i)).isEnd == false) {
//                return false;
//            }
//            t = t.next.get(word.charAt(i));
//        }
//
//        return true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode t = root;
        for (int i = 0; i < prefix.length(); i++) {
            int j = prefix.charAt(i) - 'a';
            if(t == null) {
                return false;
            }else{
                t = t.next[j];
            }
        }
        return true;
//        Trie t = this;
//        for (int i = 0; i < prefix.length(); i++) {
//            if(t.next == null) {
//                return false;
//            }
//            if(t.next.get(prefix.charAt(i)) == null) {
//                return false;
//            }
//            t = t.next.get(prefix.charAt(i));
//        }
//        return true;
    }
}
