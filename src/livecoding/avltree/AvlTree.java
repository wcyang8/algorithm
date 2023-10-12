package livecoding.avltree;

public class AvlTree {
    AvlNode root;

    public void insert(int key){
        AvlNode cur = root;
        // key를 넣을 곳을 탐색한다.
        while(cur != null){
            if(cur.key > key) cur = cur.left;
            else if(cur.key < key) cur = cur.right;
            else return;
        }
        // 찾았으면 새로운 노드로 key를 넣어준다.
        cur = new AvlNode(key);
    }
    public void delete(int key){
        AvlNode cur = root;
        // key값을 탐색한다.
        while(cur != null){
            if(cur.key > key) cur = cur.left;
            else if(cur.key < key) cur = cur.right;
            else break;
        }

    }
//    public AvlNode search(int key){
//
//    }
}
