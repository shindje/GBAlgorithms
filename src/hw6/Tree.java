package hw6;

public class Tree {
    // travers
    // delete

    private class TreeNode implements Comparable {
        private int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    value +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            if (!(o instanceof Integer))
                throw new ClassCastException("Not an int");
            return value - (Integer) o;
        }
    }

    TreeNode root;

    public void insert(int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode parent = null;
            while (true) {
                parent = current;
                if (val < current.getValue()) {
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        return;
                    }
                } else if (val > current.getValue()){
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public Integer find(int val) {
        TreeNode current = root;
        while (current.getValue() != val) {
            current = (val < current.getValue()) ? current.left : current.right;
            if (current == null) return null;
        }
        return current.getValue();
    }

    private void preOrderTraverse(TreeNode current) {
        if (current != null) {
            System.out.print(current.getValue() + " ");
            preOrderTraverse(current.left);
            preOrderTraverse(current.right);
        }
    }

    public void displayTree() {
        preOrderTraverse(root);
    }

    public boolean delete(int val) {
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;

        while (current.getValue() != val) {
            parent = current;
            if (val < current.getValue()) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
            if (current == null) {
                return false;
            }
        }

        // leaf
        if (current.left == null && current.right == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.left = null;
            else
                parent.right = null;
        }
        // one ancestor
        else if (current.right == null) {
            if (isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        } else if (current.left == null) {
            if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }
        // two ancestors
        else {
            TreeNode successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode parent = node;
        TreeNode s = node;
        TreeNode curr = node.right;
        while (curr != null) {
            parent = s;
            s = curr;
            curr = curr.left;
        }

        if (s != node.right) {
            parent.left = s.right;
            s.right = node.right;
        }
        return s;
    }

}
// 23 22 89 25 10 18 39 53 75 27 9 16 87 33 17 23 17

// 9 (4 (2 (1, 3), 8 (6 (5, 7), N)), 13 (11 (10, 12), 15 (14, 16)))
















