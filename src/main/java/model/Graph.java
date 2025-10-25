package model;

import java.util.ArrayList;

public class Graph {
    ArrayList<String> verticies;
    ArrayList<Edge> edges;

    public Graph() {
        verticies = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addVertex(String city){
        if(!verticies.contains(city)){
            verticies.add(city);
        }
    }

    public void addEdge(String from, String to, int weight){
        addVertex(from);
        addVertex(to);
        edges.add(new Edge(from, to, weight));
    }

    public void printGraph(){
        System.out.println("Cities: " + verticies);
        System.out.println("Roads: ");
        for(Edge e: edges){
            System.out.println("  " + e);
        }
    }
}
