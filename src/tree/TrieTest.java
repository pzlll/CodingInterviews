package tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {
    @Test
    public void testTrie() {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("app"));
    }

}