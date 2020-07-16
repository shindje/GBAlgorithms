package hw7;

public class Graph {
    private class Vertex {
        char label;
        boolean wasVisited;

        public Vertex(char label) {
            this.label = label;
            this.wasVisited = false;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "label=" + label +
                    '}';
        }
    }

    private class TreeNode {
        int value;
        TreeNode parent;

        TreeNode(int value, TreeNode parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    private final int MAX_VERTICES = 32;
    private Vertex[] vertexList;
    private int[][] adjMatrix;
    private int size;

    public Graph() {
        vertexList = new Vertex[MAX_VERTICES];
        adjMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        size = 0;
    }

    public void addVertex(char label) {
        vertexList[size++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    public void displayVertex(int vertex) {
        System.out.println(vertexList[vertex]);
    }

    private int getUnvisitedVertex(int ver) {
        for (int i = 0; i < size; i++) {
            if (adjMatrix[ver][i] == 1 && !vertexList[i].wasVisited)
                return i;
        }
        return -1;
    }

    public void depthTraverse() {
        Stack stack = new Stack(MAX_VERTICES);
        vertexList[0].wasVisited = true;
        displayVertex(0);
        stack.push(0);
        while (!stack.isEmpty()) {
            int v = getUnvisitedVertex(stack.peek());
            if (v == -1) {
                stack.pop();
            } else {
                vertexList[v].wasVisited = true;
                displayVertex(v);
                stack.push(v);
            }
        }
        resetFlags();
    }

    public void widthTraverse() {
        Queue<Integer> queue = new Queue(MAX_VERTICES);
        vertexList[0].wasVisited = true;
        displayVertex(0);
        queue.insert(0);
        while (!queue.isEmpty()) {
            int vCurrent = queue.remove();
            displayVertex(vCurrent);
            int vNext;
            while ((vNext = getUnvisitedVertex(vCurrent)) != -1) {
                vertexList[vNext].wasVisited = true;
                queue.insert(vNext);
            }
        }
    }

    public void way(int from, int to) {
        resetFlags();
        Queue<TreeNode> queue = new Queue(MAX_VERTICES);
        vertexList[to].wasVisited = true;
        TreeNode node = new TreeNode(to, null);
        queue.insert(node);

        while (!queue.isEmpty()) {
            TreeNode vCurrent = queue.remove();
            int vNext;
            while ((vNext = getUnvisitedVertex(vCurrent.value)) != -1) {
                if (vNext == from) {
                    System.out.print(vertexList[vNext].label);
                    while(vCurrent != null) {
                        System.out.print(" -> " + vertexList[vCurrent.value].label);
                        vCurrent = vCurrent.parent;
                    }
                    System.out.println();
                    return;
                } else {
                    vertexList[vNext].wasVisited = true;
                    queue.insert(new TreeNode(vNext, vCurrent));
                }
            }
        }
        System.out.println("The way wasn't found");
    }

    private void resetFlags() {
        for (int i = 0; i < size; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    public char get(int idx) {
        if (idx < 0 || idx >= size)
            throw new IndexOutOfBoundsException();
        else
            return vertexList[idx].label;
    }

    public void displayGraph() {
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(vertexList[i].label + "  ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(vertexList[i].label + "  ");
            for (int j = 0; j < size; j++) {
                System.out.print(i==j?".  ": adjMatrix[i][j]==0? "   ": "X"  + "  ");
            }
            System.out.println();
        }
    }
}
