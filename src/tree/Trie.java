package tree;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private boolean isEnd;
    private Map<Character, Trie> next;
    /** Initialize your data structure here. */
    public Trie() {
        this.isEnd = false;
    }



    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie t = this;
        for (int i = 0; i < word.length(); i++) {
            if(t.next == null) {
                t.next = new HashMap<>();
            }
            if(t.next.get(word.charAt(i)) == null) {
                Trie trie = new Trie();
                t.next.put(word.charAt(i), trie);

            }
            if(i == word.length() -1) {
                Trie trie = t.next.get(word.charAt(i));
                trie.isEnd = true;
            }
            t = t.next.get(word.charAt(i));

        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie t = this;
        for (int i = 0; i < word.length(); i++) {
            if(t.next == null) {
                return false;
            }
            if(t.next.get(word.charAt(i)) == null) {
                return false;
            }
            if(i == word.length()-1 && t.next.get(word.charAt(i)).isEnd == false) {
                return false;
            }
            t = t.next.get(word.charAt(i));
        }

        return true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie t = this;
        for (int i = 0; i < prefix.length(); i++) {
            if(t.next == null) {
                return false;
            }
            if(t.next.get(prefix.charAt(i)) == null) {
                return false;
            }
            t = t.next.get(prefix.charAt(i));
        }
        return true;
    }
}
