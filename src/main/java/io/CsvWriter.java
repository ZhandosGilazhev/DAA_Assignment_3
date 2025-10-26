package io;

import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CsvWriter {
    public static void writeSummary(ArrayList<HashMap<String, Object>> results, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Graph,Vertices,Edges,Prim Cost,Kruskal Cost,Prim Ops,Kruskal Ops,Prim Time (ms),Kruskal Time (ms)\n");

            for (int i = 0; i < results.size(); i++) {
                HashMap<String, Object> data = results.get(i);
                PerformanceTracker prim = (PerformanceTracker) data.get("prim");
                PerformanceTracker kruskal = (PerformanceTracker) data.get("kruskal");

                writer.write(String.format(
                        "%d,%d,%d,%d,%d,%d,%d,%.3f,%.3f\n",
                        i + 1,
                        data.get("vertices"),
                        data.get("edges"),
                        prim.getTotalCost(),
                        kruskal.getTotalCost(),
                        prim.getOperations(),
                        kruskal.getOperations(),
                        prim.getExecutionTime(),
                        kruskal.getExecutionTime()
                ));
            }

            System.out.println("CSV summary written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
