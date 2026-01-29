public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private class Node {
        int key;
        Node left, right, parent;
        boolean color;
        
        public Node(int key) {
            this.key = key;
            this.color = RED;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
    
    private Node root;
    private Node NIL;
    
    public RedBlackTree() {
        NIL = new Node(0);
        NIL.color = BLACK;
        NIL.left = NIL.right = NIL.parent = NIL;
        root = NIL;
    }
    
    public void insert(int key) {
        Node node = new Node(key);
        node.left = NIL;
        node.right = NIL;
        
        Node parent = null;
        Node current = root;
        
        while (current != NIL) {
            parent = current;
            if (node.key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
        node.parent = parent;
        if (parent == null) {
            root = node;
        } else if (node.key < parent.key) {
            parent.left = node;
        } else {
            parent.right = node;
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
    
    private void fixInsert(Node node) {
        Node uncle;
        while (node.parent.color == RED) {
            if (node.parent == node.parent.parent.right) {
                uncle = node.parent.parent.left;
                if (uncle.color == RED) {
                    uncle.color = BLACK;
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    leftRotate(node.parent.parent);
                }
            } else {
                uncle = node.parent.parent.right;
                if (uncle.color == RED) {
                    uncle.color = BLACK;
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rightRotate(node.parent.parent);
                }
            }
            if (node == root) {
                break;
            }
        }
        root.color = BLACK;
    }
    
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
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
        Node y = x.left;
        x.left = y.right;
        if (y.right != NIL) {
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
    
    public void delete(int key) {
        Node node = search(key);
        if (node == NIL) {
            System.out.println("Вузол з ключем " + key + " не знайдено");
            return;
        }
        deleteNode(node);
    }
    
    private void deleteNode(Node node) {
        Node y = node;
        Node x;
        boolean yOriginalColor = y.color;
        
        if (node.left == NIL) {
            x = node.right;
            transplant(node, node.right);
        } else if (node.right == NIL) {
            x = node.left;
            transplant(node, node.left);
        } else {
            y = minimum(node.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == node) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = node.right;
                y.right.parent = y;
            }
            transplant(node, y);
            y.left = node.left;
            y.left.parent = y;
            y.color = node.color;
        }
        
        if (yOriginalColor == BLACK) {
            fixDelete(x);
        }
    }
    
    private void fixDelete(Node x) {
        Node s;
        while (x != root && x.color == BLACK) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == RED) {
                    s.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }
                
                if (s.left.color == BLACK && s.right.color == BLACK) {
                    s.color = RED;
                    x = x.parent;
                } else {
                    if (s.right.color == BLACK) {
                        s.left.color = BLACK;
                        s.color = RED;
                        rightRotate(s);
                        s = x.parent.right;
                    }
                    
                    s.color = x.parent.color;
                    x.parent.color = BLACK;
                    s.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == RED) {
                    s.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }
                
                if (s.right.color == BLACK && s.right.color == BLACK) {
                    s.color = RED;
                    x = x.parent;
                } else {
                    if (s.left.color == BLACK) {
                        s.right.color = BLACK;
                        s.color = RED;
                        leftRotate(s);
                        s = x.parent.left;
                    }
                    
                    s.color = x.parent.color;
                    x.parent.color = BLACK;
                    s.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }
    
    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }
    
    private Node minimum(Node node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }
    
    private Node search(int key) {
        Node current = root;
        while (current != NIL && key != current.key) {
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }
    
    public void inorderTraversal() {
        System.out.print("In-order обхід: ");
        inorderHelper(root);
        System.out.println();
    }
    
    private void inorderHelper(Node node) {
        if (node != NIL) {
            inorderHelper(node.left);
            System.out.print(node.key + "(" + (node.color == RED ? "R" : "B") + ") ");
            inorderHelper(node.right);
        }
    }
    
    public void visualize() {
        if (root == NIL) {
            System.out.println("Дерево порожнє");
            return;
        }
        System.out.println("\nВізуалізація червонно-чорного дерева:");
        System.out.println("R - червоний вузол, B - чорний вузол");
        printTree(root, "", true);
    }
    
    private void printTree(Node node, String indent, boolean last) {
        if (node != NIL) {
            System.out.print(indent);
            if (last) {
                System.out.print("└─");
                indent += "  ";
            } else {
                System.out.print("├─");
                indent += "│ ";
            }
            
            String colorStr = node.color == RED ? "R" : "B";
            System.out.println(node.key + "(" + colorStr + ")");
            
            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }
    
    public boolean isEmpty() {
        return root == NIL;
    }
}
