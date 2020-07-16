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

    public boolean insert(int val, int maxLevel) {
        int level = 1;
        TreeNode node = new TreeNode(val);
        if (root == null) {
            root = node;
            return true;
        } else {
            TreeNode current = root;
            TreeNode parent = null;
            while (true) {
                parent = current;
                if (val < current.getValue()) {
                    current = current.left;
                    level++;
                    if (current == null) {
                        if (level <= maxLevel) {
                            parent.left = node;
                            return true;
                        } else
                            return false;
                    }
                } else if (val > current.getValue()){
                    current = current.right;
                    level++;
                    if (current == null) {
                        if (level <= maxLevel) {
                        parent.right = node;
                            return true;
                        } else
                            return false;
                    }
                } else {
                    return true;
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
            System.out.print(current.getValue());
            if (current.left == null && current.right == null)
                return;
            System.out.print("(");
            preOrderTraverse(current.left);
            System.out.print(",");
            preOrderTraverse(current.right);
            System.out.print(")");
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

    //Возвращает максимальный уровень наследников с учетом самого элемента
    //или -1, если максимальные уровни наследников различаются более, чем на 1
    private int checkLevels(TreeNode n) {
        if (n == null)
            return 0;
        int cntLeft = checkLevels(n.left);
        int cntRight;
        if (cntLeft == -1)
            return -1;
        else {
            cntRight = checkLevels(n.right);
            if (cntRight == -1)
                return -1;
        }
        if (Math.abs(cntLeft - cntRight) > 1)
            return -1;
        else
            return Math.max(cntLeft + 1, cntRight +1);

    }

    public boolean isBalanced() {
        int check = checkLevels(root);
        return check != -1;
    }
}