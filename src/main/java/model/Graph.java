package model;

import java.util.ArrayList;

public class Graph {
    private String name;
    public ArrayList<String> verticies;
    public ArrayList<Edge> edges;

    public Graph(String name) {
        this.name = name;
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
        System.out.println("Size: " + name);
        System.out.println("Cities: " + verticies);
        System.out.println("Roads: ");
        for(Edge e: edges){
            System.out.println("  " + e);
        }
    }
}


