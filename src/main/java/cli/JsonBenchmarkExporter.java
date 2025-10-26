package cli;

import algorithm.Kruskal;
import algorithm.Prim;
import io.*;
import model.Graph;
import metrics.PerformanceTracker;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonBenchmarkExporter {

    public static void run(ArrayList<Graph> graphs) {
        ArrayList<HashMap<String, Object>> results = new ArrayList<>();

        for (Graph g : graphs) {
            HashMap<String, Object> record = new HashMap<>();

            PerformanceTracker primTracker = Prim.getTracker(g);
            PerformanceTracker kruskalTracker = Kruskal.getTracker(g);

            record.put("vertices", g.verticies.size());
            record.put("edges", g.edges.size());
            record.put("prim", primTracker);
            record.put("kruskal", kruskalTracker);

            results.add(record);
        }

        JsonWriter.writeResultsToJson(results, "src/main/resources/output.json");
        CsvWriter.writeSummary(results, "src/main/resources/performance_summary.csv");
    }

}
