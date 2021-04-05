package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
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
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        int thisCount = 1;
        int nextCount = 0;
        Queue<TreeNode> queue = new LinkedList<>();

        List<List<Integer>> nums = new ArrayList<>();
        if(root == null) {
            return nums;
        }
        queue.add(root);
        List<Integer> num = new ArrayList<>();
        while(!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if(top.left != null) {
                queue.add(top.left);
                nextCount++;
            }
            if(top.right != null) {
                queue.add(top.right);
                nextCount++;
            }
            num.add(top.val);
            if((--thisCount) == 0) {
                thisCount = nextCount;
                nextCount = 0;
                nums.add(num);
                num = new ArrayList<>();
            }
        }

        return nums;
    }


}
