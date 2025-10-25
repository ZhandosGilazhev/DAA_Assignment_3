package org.example;

import algorithm.Kruskal;
import algorithm.Prim;
import model.Graph;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();

        g.addEdge("Almaty", "Astana", 5);
        g.addEdge("Almaty", "Taraz", 3);
        g.addEdge("Taraz", "Shymkent", 2);
        g.addEdge("Astana", "Shymkent", 8);
        g.addEdge("Astana", "Taraz", 4);

        g.printGraph();

        System.out.println();
        Prim.runAlgorithm(g);
        System.out.println();
        Kruskal.runAlgorithm(g);
    }
}