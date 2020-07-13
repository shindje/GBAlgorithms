package hw6;

public class Main {
    public static void main(String[] args) {
        int cntBalanced = 0;
        for (int i = 0; i < 20; i++) {
            Tree tree = new Tree();
            while (tree.insert((int)((Math.random()*200)-100)) < 6){};
            tree.displayTree();
            System.out.println();
            if (tree.isBalanced())
                cntBalanced++;
        }
        System.out.println("% of unbalanced trees: " + (20 - cntBalanced)/20*100);
    }
}