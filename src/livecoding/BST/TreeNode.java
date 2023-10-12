package livecoding.BST;

public class TreeNode {
    int key;
    TreeNode parent;
    TreeNode left;
    TreeNode right;

    int height;
    public TreeNode(int key){
        this.key = key;
        this.height = 1;
    }
}
