package org.example;

import java.util.*;
import model.Graph;
import io.JsonReader;

import algorithm.Kruskal;
import algorithm.Prim;

public class Main {
    public static void main(String[] args) {
        String path = "src/main/resources/input.json";

        ArrayList<Graph> graphs = JsonReader.readGraphsFromJson(path);


        for (Graph g : graphs) {
            g.printGraph();
            System.out.println();

            Prim.runAlgorithm(g);
            System.out.println();
            Kruskal.runAlgorithm(g);
            System.out.println("-----------------------------");
        }
    }
}