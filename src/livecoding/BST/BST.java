package livecoding.BST;

import livecoding.avltree.AvlNode;

public class BST {
    TreeNode root;

    public void insert(int key){
        TreeNode parent = searchParent(key);

        // 찾았으면 새로운 노드로 key를 넣어준다.
        if(parent.key > key) {
            parent.left = new TreeNode(key);
            parent.left.parent = parent;
        }
        else if(parent.key < key) {
            parent.right = new TreeNode(key);
            parent.right.parent = parent;
        }
    }
    public void delete(int key){
        // key값을 탐색한다.
        TreeNode cur = search(key);

        if(cur.left == null && cur.right == null){
            // 1. 삭제하려는 노드가 단말 노드 일 경우
            cur = null;
        }else if(cur.left != null){
            // 2. 삭제하려는 노드가 한쪽만 자식을 가질 경우
            cur = cur.left;
        }else if(cur.right != null){
            cur = cur.right;
        }else{

        }

    }
    public TreeNode search(int key){
        TreeNode cur = root;
        while(cur != null){
            if(cur.key > key) cur = cur.left;
            else if(cur.key < key) cur = cur.right;
            else break;
        }
        return cur;
    }
    public TreeNode searchParent(int key){
        TreeNode parent = null;
        TreeNode cur = root;
        while(cur != null){
            if(cur.key > key){
                parent = cur;
                cur = cur.left;
            }
            else if(cur.key < key){
                parent = cur;
                cur = cur.right;
            }
            else break;
        }
        return parent;
    }
}
