package io;

import com.google.gson.*;
import metrics.PerformanceTracker;
import model.Edge;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonWriter {

    public static void writeResultsToJson(ArrayList<HashMap<String, Object>> results, String filePath) {
        JsonObject root = new JsonObject();
        JsonArray resultsArray = new JsonArray();

        for (int i = 0; i < results.size(); i++) {
            HashMap<String, Object> data = results.get(i);

            JsonObject graphResult = new JsonObject();
            graphResult.addProperty("graph_id", i + 1);

            JsonObject inputStats = new JsonObject();
            inputStats.addProperty("vertices", (int) data.get("vertices"));
            inputStats.addProperty("edges", (int) data.get("edges"));
            graphResult.add("input_stats", inputStats);

            JsonObject primObj = buildAlgorithmJson((PerformanceTracker) data.get("prim"));
            graphResult.add("prim", primObj);

            JsonObject kruskalObj = buildAlgorithmJson((PerformanceTracker) data.get("kruskal"));
            graphResult.add("kruskal", kruskalObj);

            resultsArray.add(graphResult);
        }

        root.add("results", resultsArray);

        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(root, writer);
            System.out.println("Json written to " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JsonObject buildAlgorithmJson(PerformanceTracker tracker) {
        JsonObject obj = new JsonObject();

        JsonArray edgesArray = new JsonArray();
        for (Edge e : tracker.getMstEdges()) {
            JsonObject edgeObj = new JsonObject();
            edgeObj.addProperty("from", e.getFrom());
            edgeObj.addProperty("to", e.getTo());
            edgeObj.addProperty("weight", e.getWeight());
            edgesArray.add(edgeObj);
        }

        obj.add("mst_edges", edgesArray);
        obj.addProperty("total_cost", tracker.getTotalCost());
        obj.addProperty("operations_count", tracker.getOperations());
        obj.addProperty("execution_time_ms", tracker.getExecutionTime());

        return obj;
    }
}
