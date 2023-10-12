package livecoding.avltree;

public class AvlNode {
    int key;
    AvlNode left;
    AvlNode right;
    int height;

    public AvlNode(int key){
        this.key = key;
        this.height = 1;
    }
}