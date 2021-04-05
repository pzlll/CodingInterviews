package cn;


import javax.xml.soap.Node;

class TreeNode {
     int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }

public class No7 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[0]);

        int top = 0;

        int a = building(node, top, preorder, inorder, 0, preorder.length - 1);

        return node;
    }

    private int building(TreeNode node,int top, int[] preorder, int[] inorder, int start, int end) {
        if(end == start) {
            node.val = preorder[top];
            top++;
            return top;
        }

        int index = 0;
        for (int i = start; i <= end; i++) {
            if(inorder[i] == preorder[top]) {
                index = i;
                break;
            }
        }

        node.val = preorder[top];
        top++;

        if(start < index) {
            node.left = new TreeNode(0);
            top = building(node.left, top, preorder, inorder, start, index-1);
        }
        if(index < end) {
            node.right = new TreeNode(0);
            top = building(node.right, top, preorder, inorder, index+1, end);
        }

        return top;
    }
}
