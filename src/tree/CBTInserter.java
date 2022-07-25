package tree;

import java.util.LinkedList;
import java.util.Queue;

public class CBTInserter {
    private TreeNode root;
    private int index = 0;
    private Queue<TreeNode> first;
    private Queue<TreeNode> second;
    public CBTInserter(TreeNode root) {
        this.root = root;
        first = new LinkedList<>();
        second = new LinkedList<>();
        first.add(root);
        while (first.peek().left != null || first.peek().right != null) {
            TreeNode peek = first.peek();
            index++;
            if(index % 2 == 1) {
                second.add(peek.left);

            }else {
                second.add(peek.right);

            }
            if(index % 2 == 0) {
                first.poll();
            }
            if(first.isEmpty()) {
                while (!second.isEmpty()) {
                    first.add(second.poll());
                }
            }
            if(first.peek().left == null || (first.peek().right == null && index % 2 == 1)) {
                break;
            }
        }

    }

    public int insert(int val) {
        TreeNode treeNode = new TreeNode(val);

        if(index % 2 == 0) {
            first.peek().left = treeNode;
        }else {
            first.peek().right = treeNode;
        }

        second.add(treeNode);

        index++;

        int v = first.peek().val;

        if(index % 2 == 0) {
            first.poll();
        }

        if(first.isEmpty()) {
            while (!second.isEmpty()) {
                first.add(second.poll());
            }
        }

        return v;


    }

    public TreeNode get_root() {
        return root;
    }
}
