package io;

import com.google.gson.*;

import model.*;

import java.io.*;
import java.util.ArrayList;

public class JsonReader {
    public static ArrayList<Graph> readGraphsFromJson(String filePath) {
        ArrayList<Graph> graphList = new ArrayList<>();

        try {
            JsonObject root = JsonParser.parseReader(new FileReader(filePath)).getAsJsonObject();

            JsonArray graphs = root.getAsJsonArray("graphs");

            for (JsonElement g : graphs) {
                JsonObject graphObj = g.getAsJsonObject();
                Graph graph = new Graph(graphObj.get("name").getAsString());

                JsonArray edges = graphObj.getAsJsonArray("edges");

                for (JsonElement e : edges) {
                    JsonObject edgeObj = e.getAsJsonObject();
                    String from = edgeObj.get("from").getAsString();
                    String to = edgeObj.get("to").getAsString();
                    int weight = edgeObj.get("weight").getAsInt();
                    graph.addEdge(from, to, weight);
                }

                graphList.add(graph);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return graphList;
    }
}
