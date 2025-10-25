package org.example;

import model.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("Almaty", "Astana", 5);
        graph.addEdge("Almaty", "Taraz", 3);
        graph.addEdge("Taraz", "Shymkent", 2);
        graph.addEdge("Astana", "Shymkent", 8);
        graph.printGraph();
    }
}