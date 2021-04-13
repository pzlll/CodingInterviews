package tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void kthSmallest() {

    }

    @Test
    public void minDiffInBST() {
        TreeNode root = new TreeNode();
        root.val = 27;
        root.left = null;
        TreeNode r1 = new TreeNode();
        r1.val = 34;
        r1.left = null;
        root.right = r1;
        TreeNode r2 = new TreeNode();
        r2.val = 58;
        r1.right = r2;
        r2.right = null;
        TreeNode r3 = new TreeNode();
        r3.val = 50;
        r3.right = null;
        r2.left = r3;
        TreeNode r4 = new TreeNode();
        r4.val = 44;
        r3.left = r4;
        r4.left = null;
        r4.right = null;
        new Solution().minDiffInBST(root);
    }
}