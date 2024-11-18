public class RedBlackBST <T extends Comparable<T>>{
    private final boolean RED = true;
    private final boolean BLACK = false;
    public class Node <T extends Comparable<T>>{
        T key;

        Node<T> left;
        Node<T> right;
        Node<T> parent;

        boolean color;

        public Node(T data){
            this.key = data;
            color = RED;
            left=null;
            right=null;
            parent=null;
        }
    }
    private Node<T> root;
    private Node<T> TNULL;
    public RedBlackBST(){
        TNULL = new Node<>(null);
        TNULL.color = BLACK;
        root=TNULL;
    }
    private void leftRotate(Node x) {
        Node<T> y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node<T> y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }
    private void fixInsert(Node<T> k) {
        Node<T> uncle;
        while (k.parent.color == RED) {
            if (k.parent == k.parent.parent.right) {
                uncle = k.parent.parent.left;
                if (uncle.color == RED) {
                    // Case 1
                    uncle.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        // Case 2
                        k = k.parent;
                        rightRotate(k);
                    }
                    // Case 3
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                uncle = k.parent.parent.right;
                if (uncle.color == RED) {
                    // Case 1
                    uncle.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        // Case 2
                        k = k.parent;
                        leftRotate(k);
                    }
                    // Case 3
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = BLACK;
    }
    public void insert(T key) {
        if (search(key) != null) {
            throw new IllegalArgumentException("Error: Product already exists with ID");
        }
        Node<T> node = new Node(key);
        node.parent = null;
        node.left = TNULL;
        node.right = TNULL;
        node.color = RED;

        Node<T> y = null;
        Node<T> x = root;

        while (x != TNULL) {
            y = x;
            if (node.key.compareTo(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.key.compareTo(y.key) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = BLACK;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }
    public T search(T key) {
        Node<T> result = search(root, key);
        return (result != null) ? result.key : null;
    }
    public Node<T> search(Node<T> node, T key) {
        if (node == TNULL || key.compareTo(node.key) ==0) {
            return node; // Return the node if found or TNULL if not found
        }
        if (key.compareTo(node.key) < 0) {
            return search(node.left, key); // Search in the left subtree
        } else {
            return search(node.right, key); // Search in the right subtree
        }
    }

    public Node getRoot() {
        return root;
    }

    public Node getTNULL(){
        return TNULL;
    }


}
