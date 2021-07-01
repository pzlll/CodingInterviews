package graph;

import java.util.*;

public class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> vertexes = new HashMap<>();
        int index = 0;
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            if (!vertexes.containsKey(equation.get(0))) {
                vertexes.put(equation.get(0), index++);
            }
            if (!vertexes.containsKey(equation.get(1))) {
                vertexes.put(equation.get(1), index++);
            }
        }

        List<Point>[] matrix = new List[vertexes.size()];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new ArrayList<>();
        }

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            int ia = vertexes.get(equation.get(0));
            int ib = vertexes.get(equation.get(1));

            double value = values[i];
            matrix[ia].add(new Point(value, ib));
            matrix[ib].add(new Point(1.0 / value, ia));
        }

        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            double value = -1.0;

            List<String> query = queries.get(i);

            if (vertexes.containsKey(query.get(0)) && vertexes.containsKey(query.get(1))) {
                int ia = vertexes.get(query.get(0));
                int ib = vertexes.get(query.get(1));
                if (ia == ib) {
                    value = 1.0;
                } else {
                    Queue<Integer> points = new LinkedList<>();
                    points.offer(ia);

                    double[] ratios = new double[vertexes.size()];
                    ratios[ia] = 1.0;
                    Arrays.fill(ratios, -1.0);

                    while (!points.isEmpty() && ratios[ib] < 0) {
                        int x = points.poll();
                        for (int j = 0; j < matrix[x].size(); j++) {
                            Point p = matrix[x].get(j);
                            int y = p.index;
                            if (ratios[y] < 0) {
                                ratios[y] = ratios[x] * p.value;
                                points.offer(y);
                            }
                        }
                    }

                    value = ratios[ib];
                }
            }

            result[i] = value;
        }


        return result;
    }

    private class Point {
        private double value;
        private Integer index;

        public Point(double value, Integer index) {
            this.value = value;
            this.index = index;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                DFS(i, isConnected, visited);
                count++;
            }
        }

        return count;
    }

    private void DFS(int verdex, int[][] isConnected, boolean[] visited) {
        visited[verdex] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i] && isConnected[verdex][i] == 1) {
                DFS(i, isConnected, visited);
            }
        }
    }

    /**
     * 并查集
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length <= 1) {
            return new int[]{};
        }

        int n = edges.length;

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            if (find(parent, a) != find(parent, b)) {
                union(parent, a, b);
            } else {
                return edges[i];
            }
        }


        return new int[]{};
    }

    private void union(int[] parent, int a, int b) {
        parent[find(parent, a)] = find(parent, b);
    }

    private int find(int[] parent, int a) {
        if (parent[a] != a) {
            parent[a] = find(parent, parent[a]);
        }

        return parent[a];
    }

    /**
     * 使用并查集
     *
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {

        Union union = new Union();
        for (int[] stone :
                stones) {
            //根据传入的坐标判断
            //分三种情况
            //1.两个都是新坐标，进行合并，并把count加一
            //2.一老一新，老的坐标是其中一个连通分量中的元素，将新元素加入该连通分量，count不变
            //3.两个都是老坐标，不用处理，count不变
            union.union(stone[0], stone[1]);
        }
        return stones.length - union.getCount();
    }

    private class Union {
        private Map<Integer, Integer> parent;
        private int count;

        Union() {
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                count++;
            }

            while (parent.get(x) != x) {
                x = parent.get(x);
            }
            return parent.get(x);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent.put(rootX, rootY);
            count--;
        }

        public int getCount() {
            return count;
        }

    }

    /**
     * 并查集 逆向思维
     *
     * @param grid
     * @param hits
     * @return
     */
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int row = grid.length;
        int col = grid[0].length;

        int[][] copy = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                copy[i][j] = grid[i][j];
            }
        }

        //先把对应砖头去掉
        for (int i = 0; i < hits.length; i++) {
            copy[hits[i][0]][hits[i][1]] = 0;
        }

        int size = row * col + 1;

        //使用并查集，维护屋顶
        UnionNode unionNode = new UnionNode(size);
        size -= 1;
        //把第一行的元素加入屋顶（同一流通量）
        for (int i = 0; i < col; i++) {
            if (copy[0][i] == 1) {
                unionNode.union(i, size);
            }
        }

        //其余顶点若与上、左顶点相连，进行归并（同一连通量）
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (copy[i][j] == 0) {
                    continue;
                }

                int x = i - 1;
                int y = j - 1;
                if (isInside(i, y, row, col) && copy[i][y] == 1) {
                    unionNode.union(getIndex(i, j, col), getIndex(i, y, col));
                }

                if (isInside(x, j, row, col) && copy[x][j] == 1) {
                    unionNode.union(getIndex(x, j, col), getIndex(i, j, col));
                }

            }
        }

        //将打碎的砖头从后往前填充，若其与四周顶点相连，则进行归并
        //判断操作前后屋顶（这一连通量）包含的顶点数，确定该砖头消除的顶点数
        int[] res = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int a = hits[i][0];
            int b = hits[i][1];

            if (grid[a][b] == 0) {
                continue;
            }

            int beforeNum = unionNode.getSize(size);

            if (a == 0) {
                unionNode.union(b, size);
            }

            copy[a][b] = 1;


            int[][] sides = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
            for (int[] side :
                    sides) {
                int tempA = a + side[0];
                int tempB = b + side[1];
                if (isInside(tempA, tempB, row, col) && copy[tempA][tempB] == 1) {
                    unionNode.union(getIndex(a, b, col), getIndex(tempA, tempB, col));

                }
            }

            int afterNum = unionNode.getSize(size);

            res[i] = Math.max(0, afterNum - beforeNum - 1);
        }


        return res;
    }

    private int getIndex(int i, int j, int col) {
        return i * col + j;
    }

    private boolean isInside(int i, int j, int row, int col) {
        if (i < 0 || i >= row || j < 0 || j >= col) {
            return false;
        }

        return true;
    }

    private class UnionNode {
        private int[] parent;
        private int[] size;

        public UnionNode(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                return find(parent[x]);
            }
            return x;
        }


        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        }

        public int getSize(int x) {
            int rootX = find(x);
            return size[rootX];
        }
    }

    /**
     * 并查集 （思路：哈希存储邮箱，对所有账户的邮箱编号进行合并，通过祖先找到各自的邮箱群，最后输出）
     *
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        List<Set<String>> emails = new ArrayList<>();
        Union2 union2 = new Union2(n);
        List<String> names = new ArrayList<>();
        for (List<String> account :
                accounts) {
            Set<String> email = new HashSet<>();
            names.add(account.get(0));
            for (int i = 1; i < account.size(); i++) {
                email.add(account.get(i));
            }
            emails.add(email);
        }


        for (int i = 1; i < n; i++) {

            for (int j = 0; j < i; j++) {
                for (String email :
                        emails.get(i)) {
                    if (emails.get(j).contains(email)) {
                        union2.union(j, i);

                        break;
                    }
                }

            }
        }

        for (int i = 0; i < n; i++) {
            int parent = union2.find(i);
            if (parent != i) {
                emails.get(parent).addAll(emails.get(i));
            }
        }

        List<List<String>> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (union2.find(i) == i) {
                List<String> account = new ArrayList<>();
                account.add(names.get(i));
                for (String email :
                        emails.get(i)) {
                    account.add(email);
                }
                account.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
                res.add(account);
            }
        }

        return res;
    }

    private class Union2 {
        private int[] parent;

        public Union2(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                return find(parent[x]);
            }

            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootY] = rootX;
        }
    }

    class Edge {
        private int a;
        private int b;
        private int weight;


    }

    /**
     * 思路：通过边数判断是否能构成一个连通图，根据连通数量判断需要的边数
     *
     * @param n
     * @param connections
     * @return
     */
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        Union3 union = new Union3(n);
        for (int i = 0; i < connections.length; i++) {
            int x = connections[i][0];
            int y = connections[i][1];
            union.union(x, y);
        }

        return union.getCount() - 1;
    }

    class Union3 {
        private int[] parent;
        private int count;

        public Union3(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                count++;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                return find(parent[x]);
            }

            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            count--;
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * 拓扑排序（判断是否存在环）
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] edges = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int[] prerequisite :
                prerequisites) {
            edges[prerequisite[1]].add(prerequisite[0]);
        }

        int[] visited = new int[numCourses];
        boolean flag = true;
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                flag = dfs(i, visited, edges);
                if (!flag) {
                    return false;
                }
            } else if (visited[i] == 1) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int i, int[] visited, List<Integer>[] edges) {
        visited[i] = 1;
        for (Integer vertex :
                edges[i]) {
            if (visited[vertex] == 0) {
                dfs(vertex, visited, edges);
            } else if (visited[vertex] == 1) {
                return false;
            }
        }

        visited[i] = 2;
        return true;
    }

    /**
     * 拓扑排序（返回数组 栈存储）
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] edges = new List[numCourses];

        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }

        Stack<Integer> stack = new Stack<>();

        for (int[] prerequisite :
                prerequisites) {
            edges[prerequisite[1]].add(prerequisite[0]);
        }

        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                boolean flag = dfs2(i, visited, edges, stack);
                if (!flag) {
                    return new int[]{};
                }
            }
        }

        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses && !stack.isEmpty(); i++) {
            result[i] = stack.pop();
        }

        return result;
    }

    private boolean dfs2(int i, int[] visited, List<Integer>[] edges, Stack<Integer> stack) {
        visited[i] = 1;
        for (Integer vertex :
                edges[i]) {
            if (visited[vertex] == 0) {
                boolean b = dfs2(vertex, visited, edges, stack);
                if (!b) {
                    return false;
                }
            } else if (visited[vertex] == 1) {
                return false;
            }
        }
        visited[i] = 2;
        stack.push(i);
        return true;
    }


    /**
     * 连通个数问题 解题思路：把方格分成四个三角形并进行合并
     *
     * @param grid
     * @return
     */
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        Union4 union = new Union4(n * n * 4);

        for (int i = 0; i < grid.length; i++) {
            char[] chars = grid[i].toCharArray();
            if (chars.length < n) {
                return -1;
            }

            for (int j = 0; j < chars.length; j++) {
                int index = i * n * 4 + j * 4;
                if (chars[j] == '\\') {
                    union.union(index, index + 1);
                    union.union(index + 2, index + 3);
                } else if (chars[j] == '/') {
                    union.union(index, index + 3);
                    union.union(index + 1, index + 2);
                } else {
                    union.union(index, index + 1);
                    union.union(index + 2, index + 3);
                    union.union(index, index + 2);
                }

                if (j != 0) {
                    union.union(index - 3, index + 3);
                }
                if (i != 0) {
                    union.union(index, index - 4 * n + 2);
                }
            }
        }
        return union.getCount();
    }

    class Union4 {
        private int[] parent;
        private int count;

        public Union4(int n) {
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;

            count--;
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * 找到可删除的最多边数 使用并查集解决，若是同一连通量的边则可删除
     *
     * @param n
     * @param edges
     * @return
     */
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        Union5 union1 = new Union5(n);
        int count = 0;

        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] == 3) {
                if (union1.find(edges[i][1] - 1) == union1.find(edges[i][2] - 1)) {
                    count++;
                } else {
                    union1.union(edges[i][1] - 1, edges[i][2] - 1);
                }

            }
        }


        Union5 union2 = new Union5(union1);


        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] == 1) {
                if (union1.find(edges[i][1] - 1) == union1.find(edges[i][2] - 1)) {
                    count++;
                } else {
                    union1.union(edges[i][1] - 1, edges[i][2] - 1);
                }
            }
        }
        if (union1.getCount() > 1) {
            return -1;
        }

        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] == 2) {
                if (union2.find(edges[i][1] - 1) == union2.find(edges[i][2] - 1)) {
                    count++;
                } else {
                    union2.union(edges[i][1] - 1, edges[i][2] - 1);
                }
            }
        }
        if (union2.getCount() > 1) {
            return -1;
        }
        return count;
    }


    /**
     * 解题思路：把相邻的点之间的边存储起来，进行排序，从小到大加入每一条边，检查两个点是否连通
     *
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int index = i * m + j;
                if (i > 0) {
                    edges.add(new int[]{index - m, index, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    edges.add(new int[]{index - 1, index, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }

        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        Union5 union5 = new Union5(m * n);

        for (int[] edge :
                edges) {
            int x = edge[0];
            int y = edge[1];
            union5.union(x, y);
            if (union5.find(0) == union5.find(m * n - 1)) {
                return edge[2];
            }
        }

        return 0;


    }

    /**
     * 解题思路：与minimumEffortPath一样，把边的值从两点之差换成两点中的最大值
     *
     * @param grid
     * @return
     */
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Union5 union5 = new Union5(n * m);
        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int index = i * n + j;
                if (i > 0) {
                    edges.add(new int[]{index - n, index, Math.max(grid[i - 1][j], grid[i][j])});
                }
                if (j > 0) {
                    edges.add(new int[]{index - 1, index, Math.max(grid[i][j - 1], grid[i][j])});
                }
            }
        }

        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        for (int[] edge :
                edges) {
            int x = edge[0];
            int y = edge[1];
            union5.union(x, y);
            if (union5.find(0) == union5.find(n * m - 1)) {
                return edge[2];
            }

        }

        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        if (grid.length == 1 && grid[0].length == 0) {
            return 0;
        }
        return grid[0][0];
    }

    /**
     * 解题思路：使用并查集合并同一组的字符串，返回连通分量个数
     *
     * @param strs
     * @return
     */
    public int numSimilarGroups(String[] strs) {
        Union5 union5 = new Union5(strs.length);
        for (int i = 0; i < strs.length - 1; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (union5.find(i) == union5.find(j)) {
                    continue;
                }
                if (isSimilar(strs[i], strs[j])) {
                    union5.union(i, j);
                }
            }
        }

        return union5.getCount();
    }

    private boolean isSimilar(String a, String b) {
        if (a.equals(b)) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (list.size() >= 2) {
                    return false;
                }
                list.add(i);
            }
        }

        return true;
    }

    /**
     * 解题思路：情侣牵手，使用并查集或广度优先搜索，本该一对情侣的位置变成两个不同情侣，则建立边，交换次数等于连通分量的情侣对数减一
     *
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        int n = row.length / 2;
        Union5 union5 = new Union5(n);
        for (int i = 0; i < n; i++) {
            union5.union(row[2 * i] / 2, row[2 * i + 1] / 2);
        }

        return n - union5.getCount();
    }

    class Union5 {
        private int[] parent;
        private int count;

        public Union5(Union5 union5) {
            int n = union5.parent.length;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = union5.parent[i];
            }

            this.count = union5.count;
        }

        public Union5(int n) {
            parent = new int[n];

            count = n;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                return parent[x] = find(parent[x]);
            }

            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;

            count--;
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * 解题思路：获取最小生成树的值，判断每一条边，若减少这条边，权值和增加或连通分量增加，则为关键边
     * 若减少这条边，权值和不变，且这条边能与其他边构成最小生成树，则为伪关键边
     *
     * @param n
     * @param edges
     * @return
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        Map<int[], Integer> map2 = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            map2.put(edges[i], i);
        }
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                if (edges[i][2] > edges[j][2]) {
                    int[] temp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = temp;
                }
            }
        }
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            map.put(i, edges[i]);
        }
        int[] sumAndCount = getSum(n, map);
        int sum = sumAndCount[0];
        int count = sumAndCount[1];
        List<Integer> keyEdge = new ArrayList<>();
        List<Integer> otherEdge = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int[] remove = map.remove(i);
            int[] rest = getSum(n, map);
            map.put(i, edges[i]);
            if (rest[0] > sum || rest[1] > count) {
                keyEdge.add(map2.get(remove));
            } else if (getSum2(n, i, map) == sum) {
                otherEdge.add(map2.get(remove));
            }

        }

        List<List<Integer>> res = new ArrayList<>();
        res.add(keyEdge);
        res.add(otherEdge);
        return res;
    }

    private int getSum2(int n, int i, Map<Integer, int[]> map) {
        Union6 union = new Union6(n);
        Collection<int[]> values = map.values();
        int sum = 0;
        union.union(map.get(i)[0], map.get(i)[1]);
        sum += map.get(i)[2];
        for (int[] value :
                values) {
            if (union.union(value[0], value[1])) {
                sum += value[2];
            }
        }

        return sum;
    }


    private int[] getSum(int n, Map<Integer, int[]> map) {
        Union6 union = new Union6(n);
        Collection<int[]> values = map.values();
        int sum = 0;
        for (int[] value :
                values) {
            if (union.union(value[0], value[1])) {
                sum += value[2];
            }
        }

        return new int[]{sum, union.getCount()};
    }

    class Union6 {
        private int[] parent;
        private int count;

        public Union6(int n) {
            parent = new int[n];
            count = 0;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                count++;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                return parent[x] = find(parent[x]);
            }
            return x;
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            parent[rootX] = rootY;
            count--;

            return true;
        }

        public int getCount() {
            return count;
        }
    }

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> employeeMap = new HashMap<>();

        int n = employees.size();

        for (Employee e :
                employees) {
            employeeMap.put(e.id, e);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);

        int sum = 0;
        while (!queue.isEmpty()) {
            Integer pKey = queue.poll();
            Employee present = employeeMap.get(pKey);
            sum += present.importance;

            for (Integer eKey :
                    present.subordinates) {
                queue.offer(eKey);
            }
        }

        return sum;

    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[][] edges = new int[n * (n - 1) / 2][3];

        int k = 0;
        for (int i = 0; i < (n - 1); i++) {
            for (int j = (i + 1); j < n; j++) {
                int x = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges[k++] = new int[]{x, i, j};
            }
        }

        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int sum = 0;


        Union7 union7 = new Union7(n);
        for (int i = 0; i < (n * (n - 1) / 2); i++) {
            int x = edges[i][1];
            int y = edges[i][2];
            if (union7.union(x, y)) {
                sum += edges[i][0];
            }
            if (union7.getCount() == 1) {
                break;
            }
        }

        return sum;
    }

    class Union7 {
        private int[] parent;
        private int count;

        public Union7(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i + 1;
            }
            count = n;
        }

        public int getParent(int x) {
            if (parent[x] != x) {
                return parent[x] = getParent(parent[x]);
            }

            return x;
        }

        public boolean union(int x, int y) {
            int rootX = getParent(x);
            int rootY = getParent(y);
            if (rootX == rootY) {
                return false;
            }

            parent[rootY] = rootX;
            count--;

            return true;
        }

        public int getCount() {
            return count;
        }
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;

        boolean[][] edges = new boolean[n][n];

        Map<Integer, Set<Integer>> station2route = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int station = routes[i][j];
                Set<Integer> route = station2route.get(station);
                if (route == null) {
                    station2route.put(station, new HashSet<>());
                    station2route.get(station).add(i);
                } else {
                    if (!route.contains(i)) {
                        for (Integer r :
                                route) {
                            edges[i][r] = true;
                            edges[r][i] = true;
                        }
                        route.add(i);
                    }
                }
            }
        }

        int ret = 0;
        Queue<Integer> queue = new LinkedList<>();
        if (station2route.get(source) == null) {
            return -1;
        }

        if (source == target) {
            return 0;
        }

        int[] dist = new int[n];
        Arrays.fill(dist, -1);


        for (Integer r :
                station2route.get(source)) {
            queue.offer(r);
            dist[r] = 1;
        }

        while (!queue.isEmpty()) {
            Integer p = queue.poll();
            for (int i = 0; i < n; i++) {
                if (edges[i][p] && dist[i] == -1) {
                    dist[i] = dist[p] + 1;
                    queue.offer(i);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (Integer i :
                station2route.getOrDefault(target, new HashSet<>())) {
            if (dist[i] != -1) {
                min = Math.min(min, dist[i]);

            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;

    }

    public int numWays(int n, int[][] relation, int k) {
//        boolean[][] edge = new boolean[n][n];
//
//        for (int i = 0; i < relation.length; i++) {
//            int a = relation[i][0];
//            int b = relation[i][1];
//
//            edge[a][b] = true;
//        }
//
//        Queue<Integer> queue = new LinkedList<>();
//
//        queue.offer(0);
//
//        for (int i = 0; i < (k - 1) && !queue.isEmpty(); i++) {
//            int m = queue.size();
//            for (int j = 0; j < m; j++) {
//                Integer p = queue.poll();
//                for (int l = 0; l < n; l++) {
//                    if(edge[p][l]) {
//                        queue.offer(l);
//                    }
//                }
//            }
//        }
//
//        int count = 0;
//        int m = queue.size();
//        for (int j = 0; j < m; j++) {
//            Integer p = queue.poll();
//            if(edge[p][n-1]) {
//                count++;
//            }
//        }
//
//        return count;

        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < k; i++) {
            int[] next = new int[n];
            for (int[] edge :
                    relation) {
                int src = edge[0];
                int dest = edge[1];
                next[dest] += dp[src];
            }

            dp = next;
        }

        return dp[n - 1];

    }

    public int slidingPuzzle(int[][] board) {
        int[][] match = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                str.append(board[i][j]);
            }
        }

        String origin = str.toString();

        if (origin.equals("123450")) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();

        Set<String> seen = new HashSet<>();

        queue.offer(origin);
        seen.add(origin);

        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String p = queue.poll();
                int index = getIndex(p);
                int[] status = match[index];
                for (int u :
                        status) {
                    StringBuffer s = new StringBuffer(p);
                    char temp = s.charAt(index);
                    s.setCharAt(index, s.charAt(u));
                    s.setCharAt(s.charAt(u), temp);
                    if(s.toString().equals("123450")) {
                        return step;
                    }
                    if(seen.contains(s.toString())) {
                       continue;
                    }
                    queue.offer(s.toString());
                    seen.add(s.toString());
                }

            }
        }

        return -1;
    }

    private int getIndex(String p) {
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '0') {
                return i;
            }
        }
        return -1;
    }
}

