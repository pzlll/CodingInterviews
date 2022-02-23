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
                    str.append("n");
                }else {
                    str.append(Integer.toString(p.val));
                    queue.offer(p.left);
                    queue.offer(p.right);
                }

            }



        }

        return str.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        StringBuffer dataBuffer = new StringBuffer(data);
        if(dataBuffer.charAt(0) == 'n') {
            return null;
        }
        int i = 0;
        TreeNode root = new TreeNode(dataBuffer.charAt(0) - '0');
        i++;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (i < dataBuffer.length()) {
            int n = queue.size();
            for (int j = 0; j < n; j++) {
                TreeNode poll = queue.poll();
                TreeNode left = dataBuffer.charAt(i) == 'n' ? null : new TreeNode(dataBuffer.charAt(i) - '0');
                i++;
                TreeNode right = dataBuffer.charAt(i) == 'n' ? null : new TreeNode(dataBuffer.charAt(i) - '0');
                i++;
                poll.left = left;
                poll.right = right;
                if(left != null) {
                    queue.add(left);
                }
                if(right != null) {
                    queue.add(right);
                }
            }
        }

        return root;
    }


}
