package graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void calcEquation() {
        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        List<String> b = new ArrayList<>();
        b.add("b");
        b.add("c");
        List<List<String>> c = new ArrayList<>();
        c.add(a);
        c.add(b);
        double[] v = {2.0, 3.0};
        List<List<String>> d = new ArrayList<>();
        d.add(a);
        d.add(b);
        List<String> e = new ArrayList<>();
        e.add("a");
        e.add("e");
        d.add(e);
        new Solution().calcEquation(c, v, d);
    }

    @Test
    public void findCircleNum() {

        int[][] isConnected = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

        int circleNum = new Solution().findCircleNum(isConnected);
        System.out.println(circleNum);
    }

    @Test
    public void findRedundantConnection() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};

        int[] result = new Solution().findRedundantConnection(edges);
    }

    @Test
    public void removeStones() {
        int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        int i = new Solution().removeStones(stones);
        System.out.println(i);
    }

    @Test
    public void hitBricks() {
        //[[1,0,0,0],[1,1,1,0]]
        int[][] grid = {{1}, {1}, {1}, {1}, {1}};
        int[][] hits = {{3, 0},{4, 0},{1, 0},{2, 0},{0, 0}};
        new Solution().hitBricks(grid, hits);
    }

    @Test
    public void accountsMerge() {
        List<List<String>> accounts = new ArrayList<>();
        //accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]

        List<String> a = Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com");
        List<String> b = Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com");
        List<String> c = Arrays.asList("Mary", "mary@mail.com");

        accounts.add(a);
        accounts.add(b);
        accounts.add(c);

        new Solution().accountsMerge(accounts);
    }

    @Test
    public void canFinish() {
        int n = 2;
        int[][] edges = {{1,0}};
        new Solution().canFinish(n, edges);
    }

    @Test
    public void regionsBySlashes() {
        String[] s = {"\\/","\\", "/"};

    }

    @Test
    public void findCriticalAndPseudoCriticalEdges() {
        int n = 6;
        int[][] edges = {{0,1,1},{1,2,1},{0,2,1}, {2,3,4},{3,4,2}, {3,5,2}, {4,5,2}};
        new Solution().findCriticalAndPseudoCriticalEdges(n, edges);
    }
}