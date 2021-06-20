package tree;

import org.junit.Test;

import java.util.List;

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

    @Test
    public void isValidBST() {
        TreeNode root = new TreeNode();
        root.val = 5;

        TreeNode r1 = new TreeNode();
        r1.val = 4;
        root.left = r1;
        r1.left = null;
        r1.right = null;

        TreeNode r2 = new TreeNode();
        r2.val = 6;
        root.right = r2;

        TreeNode r3 = new TreeNode();
        r3.val = 3;
        r3.right = null;
        r3.left = null;
        r2.left = r3;

        TreeNode r4 = new TreeNode();
        r4.val = 7;
        r4.left = null;
        r4.right = null;
        r2.right = r4;
        new Solution().isValidBST(root);
    }

    @Test
    public void test() {
        ThroneInheritance t = new ThroneInheritance("king");
        t.birth("king", "andy");
        t.birth("king", "bob");
        t.birth("andy", "matthew");
        t.birth("king", "catherine");
        t.birth("bob", "alex");
        t.birth("bob", "asha");
        List<String> list = t.getInheritanceOrder();

    }
}