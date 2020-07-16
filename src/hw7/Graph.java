package hw7;

import hw3.Queue;
import hw3.Stack;

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
        Queue queue = new Queue(MAX_VERTICES);
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

    private void resetFlags() {
        for (int i = 0; i < size; i++) {
            vertexList[i].wasVisited = false;
        }
    }
}
