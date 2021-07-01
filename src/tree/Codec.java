package tree;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuffer str = new StringBuffer();
        int num = 1;
        int nNum = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode p = queue.poll();
                if(p == null) {
                    str.append("none");
                }else {
                    str.append(Integer.toString(p.val));
                    queue.offer(p.left);
                    queue.offer(p.right);
                }

                str.append(' ');
            }
            if(nNum == n) {
                break;
            }
            nNum = 0;


        }

        return str.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArrays = data.split(" ");
        if(dataArrays.length == 1) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(dataArrays[0]));
        int i = 1;
        queue.offer(root);
        while (i < dataArrays.length) {
            int n = queue.size();
            for (int j = 0; j < n; j++) {
                TreeNode p = queue.poll();
                TreeNode left;
                TreeNode right;
                if(dataArrays[i].equals("none")) {
                    left = null;
                }else {
                    left = new TreeNode(Integer.parseInt(dataArrays[i]));
                    queue.offer(left);
                }
                i++;

                if(dataArrays[i].equals("none")) {
                    right = null;
                }else {
                    right = new TreeNode(Integer.parseInt(dataArrays[i]));
                    queue.offer(right);
                }
                i++;

                p.left = left;
                p.right = right;


            }
        }

        return root;
    }
}
