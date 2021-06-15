package tree;

import org.omg.CORBA.INTERNAL;
import sun.reflect.generics.tree.Tree;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {


    public int kthSmallest(TreeNode root, int k) {
        TreeNode t = root;
        List<Integer> list = new ArrayList<>();
        TreeNode result = midTraverse(t, k, list);


        return result.val;
    }

    private TreeNode midTraverse(TreeNode t, int k, List<Integer> list) {
        if (t == null)
            return null;
        TreeNode result = midTraverse(t.left, k, list);
        if (result != null) {
            return result;
        }
        list.add(t.val);
        if (list.size() == k) {
            return t;
        }
        result = midTraverse(t.right, k, list);
        return result;
    }


    class Node {
        private int val;
        Node left;
        Node right;
        Node next;
    }

    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root == null) {
            return null;
        }
        queue.offer(root);
        int count = 0;
        int k = 0;
        Node pre = null;
        while (!queue.isEmpty() && queue.peek() != null) {
            Node p = queue.poll();
            queue.offer(p.left);
            queue.offer(p.right);
            if (pre != null) {
                pre.next = p;

            }

            pre = p;
            count++;
            if (count == (1 << k)) {
                count = 0;
                pre = null;
                k++;
            }

        }

        return root;
    }

    /**
     * 层次遍历
     * 如何确定层数？
     * 方法一：维护该层的节点数目和下一层的节点数目
     * 方法二：直接获取每一层队列总数，对其进行遍历操作
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        int thisCount = 1;
        int nextCount = 0;
        Queue<TreeNode> queue = new LinkedList<>();

        List<List<Integer>> nums = new ArrayList<>();
        if (root == null) {
            return nums;
        }
        queue.add(root);
        List<Integer> num = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top.left != null) {
                queue.add(top.left);
                nextCount++;
            }
            if (top.right != null) {
                queue.add(top.right);
                nextCount++;
            }
            num.add(top.val);
            if ((--thisCount) == 0) {
                thisCount = nextCount;
                nextCount = 0;
                nums.add(num);
                num = new ArrayList<>();
            }
        }

        return nums;
    }

    private int res = 500000;
    private TreeNode pre = null;

    /**
     * 解题思路：1. 二叉搜索树中序遍历为递增序列
     * 2.递增序列的最小值为相邻元素的最小值
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return -1;
        }

        DFS(root);

        return res;

    }

    private void DFS(TreeNode t) {
        if (t == null) {
            return;
        }

        DFS(t.left);

        if (pre != null) {
            res = Math.min((Math.abs(pre.val - t.val)), res);
        }
        pre = t;

        DFS(t.right);


    }

    /**
     * 解题思路：方法一：设置左右边界
     * 方法二：使用中序遍历
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
//        long min = Long.MIN_VALUE;
//        long max = Long.MAX_VALUE;
//        return isValidBST2(root, min, max);
        Stack<TreeNode> stack = new Stack<>();
        long preVal = Long.MIN_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val < preVal) {
                return false;
            }
            preVal = root.val;
            root = root.right;
        }

        return true;
    }

    private boolean isValidBST2(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.left != null && (root.left.val <= min || root.left.val >= root.val)) {
            return false;
        }
        if (!isValidBST2(root.left, min, root.val)) {
            return false;
        }
        if (root.right != null && (root.right.val <= root.val || root.right.val >= max)) {
            return false;
        }
        if (!isValidBST2(root.right, root.val, max)) {
            return false;
        }

        return true;

    }

    /**
     * 解题思路：
     * 方法一：使用数组存储中序遍历结果再建树
     * 方法二：在中序遍历修改原二叉树
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
//        List<Integer>  ret = new ArrayList<>();
//        BST2(root, ret);
//        TreeNode res = new TreeNode();
//        for (Integer val :
//             ret) {
//            TreeNode t = new TreeNode(val);
//            res.right = t;
//            res = t;
//        }
//
//        return res.right;
        TreeNode pre = new TreeNode(-1);
        TreeNode p = pre;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root.left = null;
            pre.right = root;
            pre = root;
            root = root.right;
        }

        return p.right;
    }

    private void BST2(TreeNode root, List<Integer> ret) {
        if (root == null) {
            return;
        }
        BST2(root.left, ret);
        ret.add(root.val);
        BST2(root.right, ret);
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val >= low && root.val <= high) {
                sum += root.val;
            } else if (root.val > high) {
                return sum;
            }
            root = root.right;
        }

        return sum;
    }

    /**
     * 使用深度优先搜索，将结果进行比较
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> q1 = new ArrayList<>();
        List<Integer> q2 = new ArrayList<>();
        findLeaf(root1, q1);
        findLeaf(root2, q2);

        if (q1.size() != q2.size()) {
            return false;
        }

        for (int i = 0; i < q1.size(); i++) {
            if (q1.get(i) != q2.get(i)) {
                return false;
            }
        }

        return true;
    }

    private void findLeaf(TreeNode root, List<Integer> q) {
        if (root == null) {
            return;
        }

        if (root.right == null && root.left == null) {
            q.add(root.val);
        } else {
            findLeaf(root.left, q);
            findLeaf(root.right, q);
        }


    }

    /**
     * 解题思路：使用层次遍历存储每个节点的深度，遍历时判断x和y是否属于同个父节点
     * 优化：遍历每个节点时，判断其是否为x或y节点，若是，则存储其深度和父节点，并设置找到的标志
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        Map<Integer, Integer> map = new HashMap<>();

        Queue<TreeNode> queue = new LinkedList<>();

        if(root == null) {
            return false;
        }

        int deep = 0;
        int count = 1;
        queue.offer(root);


        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if(top.left != null) {
                queue.offer(top.left);
            }
            if(top.right != null) {
                queue.offer(top.right);
            }

            map.put(top.val, deep);

            if(top.left != null && top.right != null) {
                if(top.left.val == x && top.right.val == y) {
                    return false;
                }
                if(top.left.val == y && top.right.val == x) {
                    return false;
                }
            }
            count--;
            if(count == 0) {
                count = queue.size();
                deep++;
            }
        }

        if(map.get(x) == map.get(y)) {
            return true;
        }


        return false;

    }

    public int maxDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        if(root != null) {
            queue.offer(root);

        }

        int count = 1;
        int ans = 0;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if(poll.left != null) {
                queue.offer(poll.left);
            }
            if(poll.right != null) {
                queue.offer(poll.right);
            }
            count--;
            if(count == 0) {
                count = queue.size();
                ans++;
            }
        }

        return ans;

//        if(root == null) {
//            return 0;
//        }
//
//        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            TreeNode q = queue.poll();
            if(q == null && p == null) {
                continue;
            }

            if(q == null || p == null || p.val != q.val) {
                return false;
            }

            queue.offer(p.left);
            queue.offer(q.right);

            queue.offer(p.right);
            queue.offer(q.left);
        }

        return true;


    }

    private boolean check(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }

        if(q == null || p == null) {
            return false;
        }

        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);

    }

}
