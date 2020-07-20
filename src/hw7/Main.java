package hw7;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        for (int i = 65; i < 75; i++) { //from "A" to "J"
            graph.addVertex((char)i);
        }
        for (int i = 0; i < 15; i++) {
            int x = (int)(Math.random()*10);
            int y = (int)(Math.random()*10);
            if (x!=y)
                graph.addEdge(x, y);
        }
        graph.displayGraph();

        int from = (int)(Math.random()*10);
        int to = (int)(Math.random()*10);
        System.out.print("Way from " + graph.get(from) + " to " + graph.get(to) + ": ");
        graph.way(from, to);
    }
}
