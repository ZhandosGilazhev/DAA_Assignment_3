package org.example;

import java.util.*;

import cli.Benchmark;
import model.Graph;
import io.JsonReader;


public class Main {
    public static void main(String[] args) {
        String path = "src/main/resources/input.json";

        ArrayList<Graph> graphs = JsonReader.readGraphsFromJson(path);


        for (Graph g : graphs) {
            Benchmark.runBenchmark(g);
        }
    }
}

//Если вы хотите чтобы я добавил ... Ладно, шучу ахахахвхаввха
//Lorem Ipsum is simply dummy text of the printing and typesetting industry.
//Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
//when an unknown printer took a galley of type and scrambled it to make a type specimen book.