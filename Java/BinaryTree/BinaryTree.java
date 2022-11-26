class Node<T> {
    T key;
    Node<T> left, right;

    Node(T key) {
        this.key = key;
        left = right = null;
    }
}

class BinaryTree<T extends Comparable<T>> {
    Node<T> root;

    BinaryTree() {
        root = null;
    }

    BinaryTree(T key) {
        root = new Node<T>(key);
    }

    void insert(T key) {
        if(isEmpty()) {
            root = new Node<T>(key);
        } else {
            insert(root, key);
        }
    }

    void insert(Node<T> root, T key) {
        if(key.compareTo(root.key) < 0) {
            insert(root.left, key);
        } else if(key.compareTo(root.key) > 0) {
            insert(root.right, key);
        }
    }

    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node<T> root) {
        if(!isEmpty()) {
            inorderRec(root.left);
            System.out.println(root.key.toString());
            inorderRec(root.right);
        }
    }

    boolean isEmpty() {
        return root == null;
    }

    Node<T> search(Node<T> root, T key) {
        if(isEmpty() || root.key.compareTo(key) == 0) {
            return root;
        }

        if(root.key.compareTo(key) < 0) {
            return search(root.right, key);
        } else {
            return search(root.left, key);
        }
    }
}

class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> bt = new BinaryTree<Integer>();
        bt.insert(50);
        bt.insert(30);
        bt.insert(20);
        //bt.insert(40);
        //bt.insert(70);
        //bt.insert(60);
        //bt.insert(80);
        bt.inorder();
    }
}
